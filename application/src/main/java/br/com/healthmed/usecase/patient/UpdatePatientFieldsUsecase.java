package br.com.healthmed.usecase.patient;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.patient.PatientRepository;
import br.com.healthmed.port.usecase.patient.UpdatePatientFields;
import br.com.healthmed.port.usecase.patient.ValidateUpdatePatient;

import java.util.Objects;

public class UpdatePatientFieldsUsecase implements UpdatePatientFields {

    private final LogRepository logRepository;
    private final PatientRepository patientRepository;
    private final ValidateUpdatePatient validatePatient;

    public UpdatePatientFieldsUsecase(LogRepository logRepository, PatientRepository patientRepository, ValidateUpdatePatient validatePatient) {
        this.logRepository = logRepository;
        this.patientRepository = patientRepository;
        this.validatePatient = validatePatient;
    }

    @Override
    public Patient update(Patient patient) {
        logRepository.info(UpdatePatientFieldsUsecase.class, LogCode.LogCodeInfo._0018);
        Patient patientFound = patientRepository.identifierById(patient.getId());

        patientFound.setName(Objects.isNull(patient.getName()) ? patientFound.getName() : patient.getName());
        patientFound.setEmail(Objects.isNull(patient.getEmail()) ? patientFound.getEmail() : patient.getEmail());
        patientFound.setPhone(Objects.isNull(patient.getPhone()) ? patientFound.getPhone() : patient.getPhone());

        validatePatient.validate(patientFound);
        Patient patientSaved = patientRepository.update(patientFound);
        logRepository.info(UpdatePatientFieldsUsecase.class, LogCode.LogCodeInfo._0015);
        return patientSaved;
    }
}
