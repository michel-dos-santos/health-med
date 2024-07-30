package br.com.healthmed.rest.mappers.outputs.dtos.medicalrecord;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExamOutputDTO {

    private UUID id;
    private String tipo;
    private LocalDateTime dateTime;
    private String local;
    private String result;

}
