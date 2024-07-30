package br.com.healthmed.rest.mappers.outputs;

import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.rest.mappers.outputs.dtos.doctor.DoctorNameOutputDTO;
import br.com.healthmed.rest.mappers.outputs.dtos.doctor.DoctorOutputDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DoctorOutputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DoctorOutputDTO mapDoctorOutputDTOFromDoctor(Doctor doctor) {
        return modelMapper.map(doctor, DoctorOutputDTO.class);
    }

    public List<DoctorNameOutputDTO> mapDoctorNameOutputDTOListFromDoctors(List<Doctor> doctors) {
        return modelMapper.map(doctors, new TypeToken<List<DoctorNameOutputDTO>>() {}.getType());
    }

}
