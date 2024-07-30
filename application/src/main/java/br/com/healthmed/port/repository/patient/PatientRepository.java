package br.com.healthmed.port.repository.patient;

import br.com.healthmed.model.patient.Patient;

import java.util.UUID;

public interface PatientRepository {

    Patient save(Patient patient);
    Patient update(Patient patient);
    boolean existsByCPF(String cpf);
    Patient identifierByCPF(String cpf);
    Patient identifierById(UUID id);

}
