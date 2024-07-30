package br.com.healthmed.usecase.schedule;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.model.schedule.Schedule;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.repository.schedule.ScheduleRepository;
import br.com.healthmed.exception.schedule.StatusNotAcceptedException;
import br.com.healthmed.port.usecase.schedule.UpdateStatusScheduleByDoctor;

import java.util.*;

public class UpdateStatusScheduleByDoctorUsecase implements UpdateStatusScheduleByDoctor {

    private final LogRepository logRepository;
    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;

    public UpdateStatusScheduleByDoctorUsecase(LogRepository logRepository, DoctorRepository doctorRepository, ScheduleRepository scheduleRepository) {
        this.logRepository = logRepository;
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule updateStatus(UUID doctorId, UUID scheduleId, Status status) {
        logRepository.info(UpdateStatusScheduleByDoctorUsecase.class, LogCode.LogCodeInfo._0018);
        Doctor doctorFound = doctorRepository.identifierById(doctorId);
        Schedule schedule = scheduleRepository.findByDoctorCPFAndID(doctorFound.getCpf(), scheduleId);

        Map<Status, List<Status>> mapStatusAccepted = Map.ofEntries(
                new AbstractMap.SimpleEntry<>(Status.GERADA, List.of(Status.ACEITA, Status.RECUSADA))
        );

        List<Status> statusAccepted = mapStatusAccepted.get(schedule.getStatus());
        if (Objects.isNull(statusAccepted) || !statusAccepted.contains(status)) {
            throw new StatusNotAcceptedException(status.name(), schedule.getStatus().name());
        }

        schedule.setStatus(status);

        Schedule scheduleSaved = scheduleRepository.save(schedule);
        logRepository.info(UpdateStatusScheduleByDoctorUsecase.class, LogCode.LogCodeInfo._0015);
        return scheduleSaved;
    }
}
