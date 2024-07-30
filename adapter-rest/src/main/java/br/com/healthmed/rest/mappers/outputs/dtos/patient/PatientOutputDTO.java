package br.com.healthmed.rest.mappers.outputs.dtos.patient;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientOutputDTO {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private String token;

}
