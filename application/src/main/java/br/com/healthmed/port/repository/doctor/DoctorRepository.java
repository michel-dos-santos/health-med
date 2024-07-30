package br.com.healthmed.port.repository.doctor;

import br.com.healthmed.model.doctor.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorRepository {

    Doctor save(Doctor doctor);
    Doctor update(Doctor doctor);
    Doctor identifierById(UUID id);
    Doctor identifierByCPF(String cpf);
    Doctor identifierByCRM(String crm);
    boolean existsByCPF(String cpf);
    List<Doctor> findAll();

}
