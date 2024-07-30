package br.com.healthmed.postgres.repository;

import br.com.healthmed.postgres.entity.patient.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPostgresPatientRepository extends JpaRepository<PatientEntity, UUID> {
    boolean existsByCpf(String cpf);
    Optional<PatientEntity> findByCpf(String cpf);

}
