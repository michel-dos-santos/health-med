package br.com.healthmed.postgres.entity.medicalrecord;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_doctor_appointment")
@EntityListeners(AuditingEntityListener.class)
public class DoctorAppointmentEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(nullable = false)
    private LocalDateTime dateTime;
    @Column(length = 10000, nullable = false)
    private String reason;
    @Column(length = 10000, nullable = false)
    private String relatedSymptoms;
    @Column(length = 10000, nullable = false)
    private String diagnosis;
    @Column(length = 10000, nullable = false)
    private String notes;
    @Column(length = 10000, nullable = false)
    private String treatment;
    @Column(length = 7, nullable = false)
    private String crm;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_medical_record_id")
    private MedicalRecordEntity medicalRecord;

}
