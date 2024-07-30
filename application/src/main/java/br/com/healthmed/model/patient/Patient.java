package br.com.healthmed.model.patient;

import br.com.healthmed.model.medicalrecord.MedicalRecord;
import br.com.healthmed.model.schedule.Schedule;

import java.util.List;
import java.util.UUID;

public class Patient {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private MedicalRecord medicalRecord;
    private List<Schedule> schedules;
    private Boolean isDeleteRequested;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public Boolean getDeleteRequested() {
        return isDeleteRequested;
    }

    public void setDeleteRequested(Boolean deleteRequested) {
        isDeleteRequested = deleteRequested;
    }
}
