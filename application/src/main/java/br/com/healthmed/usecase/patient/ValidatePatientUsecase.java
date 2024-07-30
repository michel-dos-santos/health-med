package br.com.healthmed.usecase.patient;

import br.com.healthmed.exception.patient.PatientInvalidException;
import br.com.healthmed.exception.patient.PatientNotInformedException;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.usecase.patient.ValidatePatient;
import br.com.healthmed.utils.CpfValidator;

import java.util.Objects;

public class ValidatePatientUsecase implements ValidatePatient {

    private final LogRepository logRepository;

    public ValidatePatientUsecase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void validate(Patient patient) {
        logRepository.info(ValidatePatientUsecase.class, LogCode.LogCodeInfo._0002);

        if (Objects.isNull(patient)) {
            throw new PatientNotInformedException();
        }

        if (!CpfValidator.isValid(patient.getCpf())) {
            throw new PatientInvalidException("cpf", patient.getCpf());
        }

        if (Objects.isNull(patient.getName()) || patient.getName().trim().isEmpty()) {
            throw new PatientInvalidException("name", patient.getName());
        }

        if (Objects.isNull(patient.getEmail()) || patient.getEmail().trim().isEmpty()) {
            throw new PatientInvalidException("email", patient.getEmail());
        }
    }
}
