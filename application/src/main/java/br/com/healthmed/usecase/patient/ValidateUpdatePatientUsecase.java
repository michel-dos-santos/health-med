package br.com.healthmed.usecase.patient;

import br.com.healthmed.exception.patient.PatientInvalidException;
import br.com.healthmed.exception.patient.PatientNotInformedException;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.usecase.patient.ValidateUpdatePatient;

import java.util.Objects;

public class ValidateUpdatePatientUsecase implements ValidateUpdatePatient {

    private final LogRepository logRepository;

    public ValidateUpdatePatientUsecase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void validate(Patient patient) {
        logRepository.info(ValidateUpdatePatientUsecase.class, LogCode.LogCodeInfo._0008);

        if (Objects.isNull(patient)) {
            throw new PatientNotInformedException();
        }
        if (!Objects.isNull(patient.getName()) && patient.getName().trim().isEmpty()) {
            throw new PatientInvalidException("name", patient.getName());
        }
        if (!Objects.isNull(patient.getEmail()) && patient.getEmail().trim().isEmpty()) {
            throw new PatientInvalidException("email", patient.getEmail());
        }
        if (!Objects.isNull(patient.getPhone()) && patient.getPhone().trim().isEmpty()) {
            throw new PatientInvalidException("phone", patient.getPhone());
        }
    }
}
