package br.com.healthmed.rest.mappers.inputs.dtos.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class SchedulingInputDTO {

    @NotBlank(message = "Data e hora da consulta não pode ser vazio ou nulo")
    private String dateTime;

    @NotBlank(message = "CRM do médico não pode ser vazio ou nulo")
    @Size(min = 7, max = 7, message = "CRM do médico deve ter no mínimo {min} e no máximo {max} caracteres")
    private String crm;

}
