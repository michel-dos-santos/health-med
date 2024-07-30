package br.com.healthmed.usecase.patient;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.patient.PatientRepository;
import br.com.healthmed.port.usecase.patient.RequestDeletePatient;

import java.util.UUID;

public class RequestDeletePatientUsecase implements RequestDeletePatient {

    private final LogRepository logRepository;
    private final PatientRepository patientRepository;

    public RequestDeletePatientUsecase(LogRepository logRepository, PatientRepository patientRepository) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void requestDelete(UUID id) {
        logRepository.info(RequestDeletePatientUsecase.class, LogCode.LogCodeInfo._0002);
        Patient patientFound = patientRepository.identifierById(id);

        patientFound.setDeleteRequested(true);

        patientRepository.update(patientFound);
        logRepository.info(UpdatePatientFieldsUsecase.class, LogCode.LogCodeInfo._0015);
    }
}
