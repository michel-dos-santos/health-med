package br.com.healthmed.model.medicalrecord;

import java.time.LocalDateTime;
import java.util.UUID;

public class DoctorsAppointment {

    private UUID id;
    private LocalDateTime dateTime;
    private String reason;
    private String relatedSymptoms;
    private String diagnosis;
    private String notes;
    private String treatment;
    private String crm;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getRelatedSymptoms() {
        return relatedSymptoms;
    }

    public void setRelatedSymptoms(String relatedSymptoms) {
        this.relatedSymptoms = relatedSymptoms;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }
}
