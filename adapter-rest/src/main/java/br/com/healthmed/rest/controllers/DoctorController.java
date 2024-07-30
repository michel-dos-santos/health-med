package br.com.healthmed.rest.controllers;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.model.schedule.Schedule;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.AuthDoctorProviderRepository;
import br.com.healthmed.port.usecase.doctor.FindAllDoctors;
import br.com.healthmed.rest.exception.APIException;
import br.com.healthmed.rest.mappers.inputs.DoctorInputMapper;
import br.com.healthmed.rest.mappers.inputs.dtos.doctor.IdentifierDoctorInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.doctor.DoctorInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.doctor.DoctorPatchInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.schedule.DateTimeScheduleInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.schedule.SchedulingInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.schedule.StatusScheduleInputDTO;
import br.com.healthmed.rest.mappers.outputs.DoctorOutputMapper;
import br.com.healthmed.rest.mappers.outputs.ScheduleOutputMapper;
import br.com.healthmed.rest.mappers.outputs.dtos.doctor.DoctorNameOutputDTO;
import br.com.healthmed.rest.mappers.outputs.dtos.doctor.DoctorOutputDTO;
import br.com.healthmed.rest.mappers.outputs.dtos.schedule.SchedulingOutputDTO;
import br.com.healthmed.usecase.doctor.IdentifierDoctorUsecase;
import br.com.healthmed.usecase.doctor.RequestDeleteDoctorUsecase;
import br.com.healthmed.usecase.doctor.SaveDoctorUsecase;
import br.com.healthmed.usecase.doctor.UpdateDoctorFieldsUsecase;
import br.com.healthmed.usecase.schedule.*;
import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static br.com.healthmed.rest.controllers.DoctorController.BASE_PATH;

@Tag(name = "Endpoint Doctor")
@Validated
@RestController
@RequestMapping(path = BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class DoctorController {

    public static final String BASE_PATH = "/v1/doctors";
    @Autowired
    private DoctorInputMapper doctorInputMapper;
    @Autowired
    private DoctorOutputMapper doctorOutputMapper;
    @Autowired
    private ScheduleOutputMapper scheduleOutputMapper;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private SaveDoctorUsecase saveDoctorUsecase;
    @Autowired
    private UpdateDoctorFieldsUsecase updateDoctorFieldsUsecase;
    @Autowired
    private IdentifierDoctorUsecase identifierDoctorUsecase;
    @Autowired
    private RequestDeleteDoctorUsecase requestDeleteDoctorUsecase;
    @Autowired
    private AuthDoctorProviderRepository authDoctorProviderRepository;
    @Autowired
    private CreateScheduleTimeUsecase createScheduleTimeUsecase;
    @Autowired
    private FindAllDoctors findAllDoctorsUsecase;
    @Autowired
    private FindAllScheduleByDoctorAndStatusUsecase findAllScheduleByDoctorAndStatusUsecase;
    @Autowired
    private UpdateStatusScheduleByDoctorUsecase updateStatusScheduleByDoctorUsecase;
    @Autowired
    private UpdateDateTimeScheduleByDoctorUsecase updateDateTimeScheduleByDoctorUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a criação do médico foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do médico")
    @Counted(value = "execution.count.saveDoctor")
    @Timed(value = "execution.time.saveDoctor", longTask = true)
    @PostMapping(value = "/sign-up")
    public DoctorOutputDTO saveDoctor(@RequestBody @Valid DoctorInputDTO doctorInputDTO) throws APIException {
        try {
            Doctor doctor = doctorInputMapper.mapDoctorFromDoctorInputDTO(doctorInputDTO);
            doctor = saveDoctorUsecase.save(doctor);
            //TODO APENAS PRA TESTE ESTA COMENTADO
            //authDoctorProviderRepository.signUp(doctorInputDTO.getCpf(), doctorInputDTO.getPassword(), doctorInputDTO.getEmail());
            return doctorOutputMapper.mapDoctorOutputDTOFromDoctor(doctor);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que o médico foi identificado com sucesso") })
    @Operation(summary = "Identifica o médico")
    @Counted(value = "execution.count.identifierDoctor")
    @Timed(value = "execution.time.identifierDoctor", longTask = true)
    @PostMapping(value = "/sign-in")
    public DoctorOutputDTO identifierDoctor(@RequestBody @Valid IdentifierDoctorInputDTO identifierDoctorInputDTO) throws APIException {
        try {
            //TODO APENAS PRA TESTE ESTA COMENTADO
            //String token = authDoctorProviderRepository.signIn(identifierDoctorInputDTO.getUsername(), identifierDoctorInputDTO.getPassword());
            Doctor doctor = identifierDoctorUsecase.identifierByCPF(identifierDoctorInputDTO.getUsername());
            return doctorOutputMapper.mapDoctorOutputDTOFromDoctor(doctor);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obtenção o médico pelo identificador com sucesso") })
    @Operation(summary = "Obtem o médico")
    @Counted(value = "execution.count.getDoctorById")
    @Timed(value = "execution.time.getDoctorById", longTask = true)
    @GetMapping(value = "/{id}")
    public DoctorOutputDTO getDoctorById(@PathVariable UUID id) throws APIException {
        try {
            Doctor doctor = identifierDoctorUsecase.identifierById(id);
            return doctorOutputMapper.mapDoctorOutputDTOFromDoctor(doctor);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a atualização do médico foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do médico")
    @Counted(value = "execution.count.patchDoctor")
    @Timed(value = "execution.time.patchDoctor", longTask = true)
    @PatchMapping(value = "/{id}")
    public void patchDoctor(
            @PathVariable UUID id,
            @RequestBody DoctorPatchInputDTO doctorPatchInputDTO) throws APIException {
        try {
            updateDoctorFieldsUsecase.update(doctorInputMapper.mapDoctorFromDoctorPatchInputDTO(doctorPatchInputDTO, id));
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a solicitação para exclusão dos dados do médico foi executada com sucesso") })
    @Operation(summary = "Persiste os dados da solicitação de exclusão dos dados do médico")
    @Counted(value = "execution.count.requestDeleteDoctorData")
    @Timed(value = "execution.time.requestDeleteDoctorData", longTask = true)
    @PostMapping(value = "/{id}/request-delete")
    public void requestDeleteDoctorData(
            @PathVariable UUID id) throws APIException {
        try {
            requestDeleteDoctorUsecase.requestDelete(id);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a agenda foi disponibilizada com sucesso") })
    @Operation(summary = "Agenda disponibilizada")
    @Counted(value = "execution.count.createdScheduleTime")
    @Timed(value = "execution.time.createdScheduleTime", longTask = true)
    @PostMapping(value = "/{id}/schedule")
    public SchedulingOutputDTO createdScheduleTime(@PathVariable UUID id, @RequestBody @Valid SchedulingInputDTO schedulingInputDTO) throws APIException {
        try {
            return scheduleOutputMapper.mapSchedulingOutputDTOFromSchedule(createScheduleTimeUsecase.createdScheduleTime(schedulingInputDTO.getCrm(), LocalDateTime.parse(schedulingInputDTO.getDateTime())));
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta os médicos com sucesso") })
    @Operation(summary = "Consulta os médicos")
    @Counted(value = "execution.count.getAllDoctors")
    @Timed(value = "execution.time.getAllDoctors", longTask = true)
    @GetMapping()
    public List<DoctorNameOutputDTO> getAllDoctors() throws APIException {
        try {
            List<Doctor> doctors = findAllDoctorsUsecase.findAll();
            return doctorOutputMapper.mapDoctorNameOutputDTOListFromDoctors(doctors);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Consulta agenda do médico realizada com sucesso") })
    @Operation(summary = "Consulta agenda do médico")
    @Counted(value = "execution.count.getAllScheduleByDoctorAndStatus")
    @Timed(value = "execution.time.getAllScheduleByDoctorAndStatus", longTask = true)
    @GetMapping(value = "/{id}/schedule/{status}")
    public List<SchedulingOutputDTO> getAllScheduleByDoctorAndStatus(@PathVariable UUID id, @PathVariable Status status) throws APIException {
        try {
            List<Schedule> schedules = findAllScheduleByDoctorAndStatusUsecase.findAll(id, status);
            return scheduleOutputMapper.mapSchedulingOutputDTOFromSchedules(schedules);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a agenda foi ACEITA ou RECUSADA pelo médico foi atualizada com sucesso") })
    @Operation(summary = "Persiste os dados da atualização da aprovação da agenda pelo médico")
    @Counted(value = "execution.count.patchScheduleStatus")
    @Timed(value = "execution.time.patchScheduleStatus", longTask = true)
    @PatchMapping(value = "/{id}/schedule/{scheduleId}")
    public void patchScheduleStatus(
            @PathVariable UUID id, @PathVariable UUID scheduleId,
            @RequestBody StatusScheduleInputDTO statusScheduleInputDTO) throws APIException {
        try {
            updateStatusScheduleByDoctorUsecase.updateStatus(id, scheduleId, statusScheduleInputDTO.getStatus());
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a agenda foi atualizada com sucesso") })
    @Operation(summary = "Persiste os dados da atualização da agenda pelo médico")
    @Counted(value = "execution.count.patchScheduleDateTime")
    @Timed(value = "execution.time.patchScheduleDateTime", longTask = true)
    @PatchMapping(value = "/{id}/schedule/{scheduleId}/edit")
    public void patchScheduleDateTime(
            @PathVariable UUID id, @PathVariable UUID scheduleId,
            @RequestBody DateTimeScheduleInputDTO dateTimeScheduleInputDTO) throws APIException {
        try {
            updateDateTimeScheduleByDoctorUsecase.updateDateTime(id, scheduleId, LocalDateTime.parse(dateTimeScheduleInputDTO.getDateTime()));
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

}
