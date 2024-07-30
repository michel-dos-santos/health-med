package br.com.healthmed.model.schedule;

import br.com.healthmed.domain.schedule.Status;

import java.time.LocalDateTime;
import java.util.UUID;

public class Schedule {

    private UUID id;
    private LocalDateTime dateTime;
    private String link;
    private Status status;
    private PatientData patientData;
    private DoctorData doctorData;

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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public PatientData getPatientData() {
        return patientData;
    }

    public void setPatientData(PatientData patientData) {
        this.patientData = patientData;
    }

    public DoctorData getDoctorData() {
        return doctorData;
    }

    public void setDoctorData(DoctorData doctorData) {
        this.doctorData = doctorData;
    }
}
