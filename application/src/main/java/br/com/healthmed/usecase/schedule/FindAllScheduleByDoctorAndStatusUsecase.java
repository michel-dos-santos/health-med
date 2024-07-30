package br.com.healthmed.usecase.schedule;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.model.schedule.Schedule;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.repository.schedule.ScheduleRepository;
import br.com.healthmed.port.usecase.schedule.FindAllScheduleByDoctorAndStatus;

import java.util.List;
import java.util.UUID;

public class FindAllScheduleByDoctorAndStatusUsecase implements FindAllScheduleByDoctorAndStatus {

    private final LogRepository logRepository;
    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;

    public FindAllScheduleByDoctorAndStatusUsecase(ScheduleRepository scheduleRepository, LogRepository logRepository, DoctorRepository doctorRepository) {
        this.logRepository = logRepository;
        this.scheduleRepository = scheduleRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Schedule> findAll(UUID doctorId, Status status) {
        logRepository.info(FindAllScheduleByDoctorAndStatusUsecase.class, LogCode.LogCodeInfo._0001);
        Doctor doctor = doctorRepository.identifierById(doctorId);
        List<Schedule> allSchedules = scheduleRepository.findAllByDoctorCPFAndStatus(doctor.getCpf(), status);

        logRepository.info(FindAllScheduleByDoctorAndStatusUsecase.class, LogCode.LogCodeInfo._0003);
        return allSchedules;
    }

}
