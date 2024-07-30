package br.com.healthmed.usecase.schedule;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.exception.schedule.StatusNotAcceptedException;
import br.com.healthmed.exception.schedule.UpdateNotAcceptException;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.model.schedule.Schedule;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.repository.schedule.ScheduleRepository;
import br.com.healthmed.port.usecase.schedule.UpdateDateTimeScheduleByDoctor;
import br.com.healthmed.port.usecase.schedule.UpdateStatusScheduleByDoctor;

import java.time.LocalDateTime;
import java.util.*;

public class UpdateDateTimeScheduleByDoctorUsecase implements UpdateDateTimeScheduleByDoctor {

    private final LogRepository logRepository;
    private final DoctorRepository doctorRepository;
    private final ScheduleRepository scheduleRepository;

    public UpdateDateTimeScheduleByDoctorUsecase(LogRepository logRepository, DoctorRepository doctorRepository, ScheduleRepository scheduleRepository) {
        this.logRepository = logRepository;
        this.doctorRepository = doctorRepository;
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public Schedule updateDateTime(UUID doctorId, UUID scheduleId, LocalDateTime dateTime) {
        logRepository.info(UpdateDateTimeScheduleByDoctorUsecase.class, LogCode.LogCodeInfo._0018);
        Doctor doctorFound = doctorRepository.identifierById(doctorId);
        Schedule schedule = scheduleRepository.findByDoctorCPFAndID(doctorFound.getCpf(), scheduleId);

        if (Status.DISPONIVEL != schedule.getStatus()) {
            throw new UpdateNotAcceptException(schedule.getStatus().name(), Status.DISPONIVEL.name());
        }

        schedule.setDateTime(dateTime);

        Schedule scheduleSaved = scheduleRepository.save(schedule);
        logRepository.info(UpdateDateTimeScheduleByDoctorUsecase.class, LogCode.LogCodeInfo._0015);
        return scheduleSaved;
    }
}
