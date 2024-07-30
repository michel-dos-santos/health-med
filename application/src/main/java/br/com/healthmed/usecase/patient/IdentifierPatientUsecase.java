package br.com.healthmed.usecase.patient;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.patient.PatientRepository;
import br.com.healthmed.port.usecase.patient.IdentifierPatient;

import java.util.UUID;

public class IdentifierPatientUsecase implements IdentifierPatient {

    private final LogRepository logRepository;
    private final PatientRepository patientRepository;

    public IdentifierPatientUsecase(PatientRepository patientRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public Patient identifierByCPF(String cpf) {
        logRepository.info(IdentifierPatientUsecase.class, LogCode.LogCodeInfo._0004);
        Patient patient = patientRepository.identifierByCPF(cpf);
        logRepository.info(IdentifierPatientUsecase.class, LogCode.LogCodeInfo._0006);
        return patient;
    }
    @Override
    public Patient identifierById(UUID id) {
        logRepository.info(IdentifierPatientUsecase.class, LogCode.LogCodeInfo._0005);
        Patient patient = patientRepository.identifierById(id);
        logRepository.info(IdentifierPatientUsecase.class, LogCode.LogCodeInfo._0006);
        return patient;
    }
}
