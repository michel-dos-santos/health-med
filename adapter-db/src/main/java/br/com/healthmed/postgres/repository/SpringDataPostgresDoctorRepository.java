package br.com.healthmed.postgres.repository;

import br.com.healthmed.postgres.entity.doctor.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SpringDataPostgresDoctorRepository extends JpaRepository<DoctorEntity, UUID> {

    boolean existsByCpf(String cpf);

    Optional<DoctorEntity> findByCpf(String cpf);

    @Query("select d from DoctorEntity d inner join d.specialties s where lower(s.crm) = :crm")
    Optional<DoctorEntity> findByCrm(String crm);

}
