package br.com.healthmed.rest.mappers.inputs.dtos.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.List;

@Data
@NoArgsConstructor
public class DoctorInputDTO {

    @NotBlank(message = "Nome não pode ser vazio ou nulo")
    @Size(min = 3, max = 50, message = "Nome do paciente deve ter no mínimo {min} e no máximo {max} caracteres")
    private String name;

    @NotBlank(message = "CPF não pode ser vazio ou nulo")
    @CPF(message = "CPF inválido")
    private String cpf;

    @NotBlank(message = "Email não pode ser vazio ou nulo")
    @Email(message = "Email inválido")
    private String email;

    @NotBlank(message = "Senha não pode ser vazio ou nulo")
    @Size(min = 8, max = 99, message = "Senha deve ter no mínimo {min} e no máximo {max} caracteres")
    private String password;

    @NotBlank(message = "Telefone não pode ser vazio ou nulo")
    @Size(min = 12, max = 13, message = "Telefone deve ter no mínimo {min} e no máximo {max} caracteres, contendo DDI+DDD+Telefone")
    private String phone;

    @NotEmpty(message = "Especialidade do médico não pode ser vazio ou nulo")
    private List<@Valid SpecialtyInputDTO> specialties;

}
