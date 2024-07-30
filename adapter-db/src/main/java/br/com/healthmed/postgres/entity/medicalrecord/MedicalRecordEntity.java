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
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_medical_record")
@EntityListeners(AuditingEntityListener.class)
public class MedicalRecordEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
    @Column(length = 11, nullable = false)
    private String cpf;
    @Column(length = 3, nullable = false)
    private Integer height;
    @Column(length = 14, scale = 2, nullable = false)
    private Double weight;
    @Column(length = 10, nullable = false)
    private String bloodType;
    @Column(nullable = false)
    private String allergy;
    @OneToMany(mappedBy="medicalRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ExamEntity> exams;
    @OneToMany(mappedBy="medicalRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VaccineCardEntity> vaccineCards;
    @OneToMany(mappedBy="medicalRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DoctorAppointmentEntity> doctorAppointments;

}
