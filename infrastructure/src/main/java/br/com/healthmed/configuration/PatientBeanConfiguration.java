package br.com.healthmed.configuration;

import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.patient.PatientRepository;
import br.com.healthmed.port.usecase.patient.ValidatePatient;
import br.com.healthmed.port.usecase.patient.ValidateUpdatePatient;
import br.com.healthmed.usecase.patient.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PatientBeanConfiguration {

    @Bean
    SavePatientUsecase savePatient(PatientRepository patientRepository, LogRepository logRepository, ValidatePatient validatePatient) {
        return new SavePatientUsecase(patientRepository, logRepository, validatePatient);
    }
    @Bean
    ValidatePatientUsecase validatePatient(LogRepository logRepository) {
        return new ValidatePatientUsecase(logRepository);
    }
    @Bean
    IdentifierPatientUsecase identifierPatient(PatientRepository patientRepository, LogRepository logRepository) {
        return new IdentifierPatientUsecase(patientRepository, logRepository);
    }
    @Bean
    UpdatePatientFieldsUsecase updatePatient(PatientRepository patientRepository, LogRepository logRepository, ValidateUpdatePatient validateUpdatePatient) {
        return new UpdatePatientFieldsUsecase(logRepository, patientRepository, validateUpdatePatient);
    }
    @Bean
    ValidateUpdatePatientUsecase validateUpdatePatient(LogRepository logRepository) {
        return new ValidateUpdatePatientUsecase(logRepository);
    }
    @Bean
    RequestDeletePatientUsecase requestDeletePatient(LogRepository logRepository, PatientRepository patientRepository) {
        return new RequestDeletePatientUsecase(logRepository, patientRepository);
    }
}
