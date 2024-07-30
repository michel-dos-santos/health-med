package br.com.healthmed.rest.mappers.inputs.dtos.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DateTimeScheduleInputDTO {

    @NotBlank(message = "Data e hora da consulta não pode ser vazio ou nulo")
    private String dateTime;

}
