package br.com.healthmed.rest.mappers.outputs.dtos.schedule;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorDataOutputDTO {

    private String name;
    private String cpf;
    private String crm;
    private String specialty;
    private Double value;

}
