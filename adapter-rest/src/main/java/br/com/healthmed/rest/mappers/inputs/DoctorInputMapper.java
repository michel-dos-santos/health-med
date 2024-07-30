package br.com.healthmed.rest.mappers.inputs;

import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.rest.mappers.inputs.dtos.doctor.DoctorInputDTO;
import br.com.healthmed.rest.mappers.inputs.dtos.doctor.DoctorPatchInputDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DoctorInputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Doctor mapDoctorFromDoctorInputDTO(DoctorInputDTO doctorInputDTO) {
        return modelMapper.map(doctorInputDTO, Doctor.class);
    }

    public Doctor mapDoctorFromDoctorPatchInputDTO(DoctorPatchInputDTO doctorPatchInputDTO, UUID id) {
        Doctor doctor = modelMapper.map(doctorPatchInputDTO, Doctor.class);
        doctor.setId(id);
        return doctor;
    }

}
