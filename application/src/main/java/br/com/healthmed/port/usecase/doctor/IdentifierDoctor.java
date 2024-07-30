package br.com.healthmed.port.usecase.doctor;

import br.com.healthmed.model.doctor.Doctor;

import java.util.UUID;

public interface IdentifierDoctor {

    Doctor identifierByCPF(String cpf);

    Doctor identifierById(UUID id);

}
