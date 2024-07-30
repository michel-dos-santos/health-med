package br.com.healthmed.rest.mappers.inputs.dtos.doctor;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class DoctorPatchInputDTO {

    private transient UUID id;

    private String name;
    private String email;
    private String phone;

}
