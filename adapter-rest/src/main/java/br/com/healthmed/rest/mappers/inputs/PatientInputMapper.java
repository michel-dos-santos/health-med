package br.com.healthmed.rest.mappers.inputs;

import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.rest.mappers.inputs.dtos.patient.PatientInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.patient.PatientPatchInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class PatientInputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Patient mapPatientFromPatientInputDTO(PatientInputDTO patientInputDTO) {
        return modelMapper.map(patientInputDTO, Patient.class);
    }

    public Patient mapPatientFromPatientPatchInputDTO(PatientPatchInputDTO patientPatchInputDTO, UUID id) {
        Patient patient = modelMapper.map(patientPatchInputDTO, Patient.class);
        patient.setId(id);
        return patient;
    }

}
