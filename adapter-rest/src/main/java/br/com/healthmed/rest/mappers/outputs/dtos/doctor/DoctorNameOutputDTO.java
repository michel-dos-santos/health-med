package br.com.healthmed.rest.mappers.outputs.dtos.doctor;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DoctorNameOutputDTO {

    private UUID id;
    private String name;
    private List<SpecialtyOutputDTO> specialties;

}
