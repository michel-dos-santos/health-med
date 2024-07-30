package br.com.healthmed.postgres.entity.patient;

import br.com.healthmed.postgres.entity.medicalrecord.MedicalRecordEntity;
import br.com.healthmed.postgres.entity.schedule.ScheduleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_patient")
@EntityListeners(AuditingEntityListener.class)
public class PatientEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 14, nullable = false, unique = true)
    private String cpf;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 13, nullable = false)
    private String phone;
    private Boolean isDeleteRequested;
}
