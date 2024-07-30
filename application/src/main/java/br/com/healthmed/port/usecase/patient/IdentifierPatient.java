package br.com.healthmed.port.usecase.patient;

import br.com.healthmed.model.patient.Patient;

import java.util.UUID;

public interface IdentifierPatient {

    Patient identifierByCPF(String cpf);

    Patient identifierById(UUID id);

}
