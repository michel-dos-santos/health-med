package br.com.healthmed.rest.mappers.outputs.dtos.doctor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SpecialtyOutputDTO {

    private UUID id;
    private String name;
    private String crm;
    private Double value;

}
