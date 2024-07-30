package br.com.healthmed.rest.mappers.inputs.dtos.doctor;

import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Data
@NoArgsConstructor
public class SpecialtyInputDTO {

    @NotBlank(message = "Nome não pode ser vazio ou nulo")
    @Size(min = 3, max = 50, message = "Nome da especialidade do médico deve ter no mínimo {min} e no máximo {max} caracteres")
    private String name;

    @NotBlank(message = "CRM da especialidade do médico não pode ser vazio ou nulo")
    @Size(min = 7, max = 7, message = "CRM da especialidade do médico deve ter no mínimo {min} e no máximo {max} caracteres")
    private String crm;

    @NotNull(message = "Valor da consulta não pode ser nulo")
    @Digits(integer = 14, fraction = 2)
    @DecimalMin(value = "0", inclusive = false, message = "Valor da consulta deve ser maior do que zero")
    private Double value;

}
