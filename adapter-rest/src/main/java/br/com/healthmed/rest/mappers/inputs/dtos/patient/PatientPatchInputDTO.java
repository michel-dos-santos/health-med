package br.com.healthmed.rest.mappers.inputs.dtos.patient;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class PatientPatchInputDTO {

    private transient UUID id;

    private String name;
    private String email;
    private String phone;

}
