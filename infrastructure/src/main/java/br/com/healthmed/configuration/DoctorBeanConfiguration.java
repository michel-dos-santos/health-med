package br.com.healthmed.configuration;

import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.usecase.doctor.ValidateDoctor;
import br.com.healthmed.port.usecase.doctor.ValidateUpdateDoctor;
import br.com.healthmed.usecase.doctor.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DoctorBeanConfiguration {

    @Bean
    SaveDoctorUsecase saveDoctor(DoctorRepository doctorRepository, LogRepository logRepository, ValidateDoctor validateDoctor) {
        return new SaveDoctorUsecase(doctorRepository, logRepository, validateDoctor);
    }
    @Bean
    ValidateDoctorUsecase validateDoctor(LogRepository logRepository) {
        return new ValidateDoctorUsecase(logRepository);
    }
    @Bean
    IdentifierDoctorUsecase identifierDoctor(DoctorRepository doctorRepository, LogRepository logRepository) {
        return new IdentifierDoctorUsecase(doctorRepository, logRepository);
    }
    @Bean
    UpdateDoctorFieldsUsecase updateDoctor(DoctorRepository doctorRepository, LogRepository logRepository, ValidateUpdateDoctor validateUpdateDoctor) {
        return new UpdateDoctorFieldsUsecase(logRepository, doctorRepository, validateUpdateDoctor);
    }
    @Bean
    ValidateUpdateDoctorUsecase validateUpdateDoctor(LogRepository logRepository) {
        return new ValidateUpdateDoctorUsecase(logRepository);
    }
    @Bean
    RequestDeleteDoctorUsecase requestDeleteDoctor(LogRepository logRepository, DoctorRepository doctorRepository) {
        return new RequestDeleteDoctorUsecase(logRepository, doctorRepository);
    }
    @Bean
    FindAllDoctorsUsecase findAllDoctors(DoctorRepository doctorRepository, LogRepository logRepository) {
        return new FindAllDoctorsUsecase(doctorRepository, logRepository);
    }

}
