package br.com.healthmed.configuration;

import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.repository.patient.PatientRepository;
import br.com.healthmed.port.repository.schedule.ScheduleRepository;
import br.com.healthmed.port.usecase.schedule.GenerateLinkScheduling;
import br.com.healthmed.usecase.schedule.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleBeanConfiguration {

    @Bean
    public CreateSchedulingUsecase createScheduling(ScheduleRepository scheduleRepository, LogRepository logRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, GenerateLinkScheduling generateLinkScheduling) {
        return new CreateSchedulingUsecase(scheduleRepository, logRepository, patientRepository, doctorRepository, generateLinkScheduling);
    }

    @Bean
    public GenerateLinkSchedulingUsecase generateLinkScheduling(LogRepository logRepository) {
        return new GenerateLinkSchedulingUsecase(logRepository);
    }

    @Bean
    public FindAllScheduleByDoctorAndStatusUsecase findAllSchedulesByDoctorAndStatus(ScheduleRepository scheduleRepository, LogRepository logRepository, DoctorRepository doctorRepository) {
        return new FindAllScheduleByDoctorAndStatusUsecase(scheduleRepository, logRepository, doctorRepository);
    }

    @Bean
    public UpdateStatusScheduleByDoctorUsecase updateStatusScheduleByDoctor(ScheduleRepository scheduleRepository, LogRepository logRepository, DoctorRepository doctorRepository) {
        return new UpdateStatusScheduleByDoctorUsecase(logRepository, doctorRepository, scheduleRepository);
    }

    @Bean
    public CreateScheduleTimeUsecase createScheduleTime(ScheduleRepository scheduleRepository, LogRepository logRepository, DoctorRepository doctorRepository) {
        return new CreateScheduleTimeUsecase(scheduleRepository, logRepository, doctorRepository);
    }

    @Bean
    public UpdateDateTimeScheduleByDoctorUsecase updateDateTimeScheduleByDoctor(ScheduleRepository scheduleRepository, LogRepository logRepository, DoctorRepository doctorRepository) {
        return new UpdateDateTimeScheduleByDoctorUsecase(logRepository, doctorRepository, scheduleRepository);
    }

}
