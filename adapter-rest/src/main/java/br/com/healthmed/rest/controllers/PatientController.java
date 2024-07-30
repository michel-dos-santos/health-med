package br.com.healthmed.rest.controllers;

import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.patient.AuthPatientProviderRepository;
import br.com.healthmed.rest.exception.APIException;
import br.com.healthmed.rest.mappers.inputs.PatientInputMapper;
import br.com.healthmed.rest.mappers.inputs.dtos.patient.IdentifierPatientInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.patient.PatientInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.patient.PatientPatchInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.schedule.SchedulingInputDTO;
import br.com.healthmed.rest.mappers.outputs.ExamOutputMapper;
import br.com.healthmed.rest.mappers.outputs.PatientOutputMapper;
import br.com.healthmed.rest.mappers.outputs.ScheduleOutputMapper;
import br.com.healthmed.rest.mappers.outputs.dtos.patient.PatientOutputDTO;
import br.com.healthmed.rest.mappers.outputs.dtos.schedule.SchedulingOutputDTO;
import br.com.healthmed.usecase.patient.IdentifierPatientUsecase;
import br.com.healthmed.usecase.patient.RequestDeletePatientUsecase;
import br.com.healthmed.usecase.patient.SavePatientUsecase;
import br.com.healthmed.usecase.patient.UpdatePatientFieldsUsecase;
import br.com.healthmed.usecase.schedule.CreateSchedulingUsecase;
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
import java.util.UUID;

import static br.com.healthmed.rest.controllers.PatientController.BASE_PATH;

@Tag(name = "Endpoint Patient")
@Validated
@RestController
@RequestMapping(path = BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class PatientController {

    public static final String BASE_PATH = "/v1/patients";
    @Autowired
    private PatientInputMapper patientInputMapper;
    @Autowired
    private PatientOutputMapper patientOutputMapper;
    @Autowired
    private ScheduleOutputMapper scheduleOutputMapper;
    @Autowired
    private ExamOutputMapper examOutputMapper;
    @Autowired
    private LogRepository logRepository;
    @Autowired
    private SavePatientUsecase savePatientUsecase;
    @Autowired
    private UpdatePatientFieldsUsecase updatePatientFieldsUsecase;
    @Autowired
    private IdentifierPatientUsecase identifierPatientUsecase;
    @Autowired
    private RequestDeletePatientUsecase requestDeletePatientUsecase;
    @Autowired
    private AuthPatientProviderRepository authPatientProviderRepository;
    @Autowired
    private CreateSchedulingUsecase createSchedulingUsecase;

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a criação do paciente foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do paciente")
    @Counted(value = "execution.count.savePatient")
    @Timed(value = "execution.time.savePatient", longTask = true)
    @PostMapping(value = "/sign-up")
    public PatientOutputDTO savePatient(@RequestBody @Valid PatientInputDTO patientInputDTO) throws APIException {
        try {
            Patient patient = patientInputMapper.mapPatientFromPatientInputDTO(patientInputDTO);
            patient = savePatientUsecase.save(patient);
            //TODO APENAS PRA TESTE ESTA COMENTADO
            //authClientProviderRepository.signUp(clientInputDTO.getCpf(), clientInputDTO.getPassword(), clientInputDTO.getEmail());
            return patientOutputMapper.mapPatientOutputDTOFromPatient(patient);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que o paciente foi identificado com sucesso") })
    @Operation(summary = "Identifica o paciente")
    @Counted(value = "execution.count.identifierPatient")
    @Timed(value = "execution.time.identifierPatient", longTask = true)
    @PostMapping(value = "/sign-in")
    public PatientOutputDTO identifierPatient(@RequestBody @Valid IdentifierPatientInputDTO identifierPatientInputDTO) throws APIException {
        try {
            //TODO APENAS PRA TESTE ESTA COMENTADO
            //String token = authClientProviderRepository.signIn(identifierClientInputDTO.getUsername(), identifierClientInputDTO.getPassword());
            Patient patient = identifierPatientUsecase.identifierByCPF(identifierPatientInputDTO.getUsername());
            return patientOutputMapper.mapPatientOutputDTOFromPatient(patient, null);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Obtenção o paciente pelo identificador com sucesso") })
    @Operation(summary = "Obtem o paciente")
    @Counted(value = "execution.count.getPatientById")
    @Timed(value = "execution.time.getPatientById", longTask = true)
    @GetMapping(value = "/{id}")
    public PatientOutputDTO getPatientById(@PathVariable UUID id) throws APIException {
        try {
            Patient patient = identifierPatientUsecase.identifierById(id);
            return patientOutputMapper.mapPatientOutputDTOFromPatient(patient);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a atualização do paciente foi executada com sucesso") })
    @Operation(summary = "Persiste os dados do paciente")
    @Counted(value = "execution.count.patchPatient")
    @Timed(value = "execution.time.patchPatient", longTask = true)
    @PatchMapping(value = "/{id}")
    public void patchPatient(
            @PathVariable UUID id,
            @RequestBody PatientPatchInputDTO patientPatchInputDTO) throws APIException {
        try {
            updatePatientFieldsUsecase.update(patientInputMapper.mapPatientFromPatientPatchInputDTO(patientPatchInputDTO, id));
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que a solicitação para exclusão dos dados do paciente foi executada com sucesso") })
    @Operation(summary = "Persiste os dados da solicitação de exclusão dos dados do paciente")
    @Counted(value = "execution.count.requestDeletePatientData")
    @Timed(value = "execution.time.requestDeletePatientData", longTask = true)
    @PostMapping(value = "/{id}/request-delete")
    public void requestDeletePatientData(
            @PathVariable UUID id) throws APIException {
        try {
            requestDeletePatientUsecase.requestDelete(id);
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Indica que o agendamento foi gerado com sucesso") })
    @Operation(summary = "Agendamento gerado")
    @Counted(value = "execution.count.createdSchedule")
    @Timed(value = "execution.time.createdSchedule", longTask = true)
    @PostMapping(value = "/{id}/scheduling")
    public SchedulingOutputDTO createdScheduling(@PathVariable UUID id, @RequestBody @Valid SchedulingInputDTO schedulingInputDTO) throws APIException {
        try {
            return scheduleOutputMapper.mapSchedulingOutputDTOFromSchedule(createSchedulingUsecase.scheduling(id, schedulingInputDTO.getCrm(), LocalDateTime.parse(schedulingInputDTO.getDateTime())));
        } catch (Exception e) {
            throw APIException.internalError("Erro interno", Collections.singletonList(e.getMessage()));
        }
    }

}
