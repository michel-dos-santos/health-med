package br.com.healthmed.postgres.repository;

import br.com.healthmed.postgres.entity.schedule.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import br.com.healthmed.domain.schedule.Status;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SpringDataPostgresScheduleRepository extends JpaRepository<ScheduleEntity, UUID> {

    @Query("select s from ScheduleEntity s inner join s.doctorData d where lower(d.cpf) = :cpf and s.status = :status")
    List<ScheduleEntity> findByDoctorCPFAndStatus(String cpf, Status status);

    @Query("select s from ScheduleEntity s inner join s.doctorData d where lower(d.cpf) = :cpf and s.id = :id")
    Optional<ScheduleEntity> findByDoctorCPFAndID(String cpf, UUID id);

    @Query("select s from ScheduleEntity s inner join s.doctorData d where lower(d.crm) = :crm and s.dateTime = :dateTime")
    Optional<ScheduleEntity> findByDoctorCRMAndDateTime(String crm, LocalDateTime dateTime);

}
