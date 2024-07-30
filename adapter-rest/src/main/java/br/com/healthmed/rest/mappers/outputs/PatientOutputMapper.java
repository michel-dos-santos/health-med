package br.com.healthmed.rest.mappers.outputs;

import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.rest.mappers.outputs.dtos.patient.PatientOutputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PatientOutputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public PatientOutputDTO mapPatientOutputDTOFromPatient(Patient patient) {
        return mapPatientOutputDTOFromPatient(patient, null);
    }

    public PatientOutputDTO mapPatientOutputDTOFromPatient(Patient patient, String token) {
        PatientOutputDTO patientOutputDTO = modelMapper.map(patient, PatientOutputDTO.class);
        patientOutputDTO.setToken(token);
        return patientOutputDTO;
    }
}
