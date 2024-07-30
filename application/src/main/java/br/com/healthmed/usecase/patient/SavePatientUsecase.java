package br.com.healthmed.usecase.patient;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.patient.PatientRepository;
import br.com.healthmed.port.usecase.patient.SavePatient;
import br.com.healthmed.port.usecase.patient.ValidatePatient;

public class SavePatientUsecase implements SavePatient {

    private final LogRepository logRepository;
    private final PatientRepository patientRepository;
    private final ValidatePatient validatePatient;

    public SavePatientUsecase(PatientRepository patientRepository, LogRepository logRepository, ValidatePatient validatePatient) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
        this.validatePatient = validatePatient;
    }

    @Override
    public Patient save(Patient patient) {
        logRepository.info(SavePatientUsecase.class, LogCode.LogCodeInfo._0001);
        validatePatient.validate(patient);
        Patient patientSaved = patientRepository.save(patient);
        logRepository.info(SavePatientUsecase.class, LogCode.LogCodeInfo._0003);

        return patientSaved;
    }
}
