package br.com.healthmed.model.medicalrecord;

import java.util.List;
import java.util.UUID;

public class MedicalRecord {

    private UUID id;
    private String cpf;
    private Integer height;
    private Double weight;
    private String bloodType;
    private String allergy;
    private List<Exam> exams;
    private List<VaccineCard> vaccineCards;
    private List<DoctorsAppointment> doctorAppointments;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getAllergy() {
        return allergy;
    }

    public void setAllergy(String allergy) {
        this.allergy = allergy;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public List<VaccineCard> getVaccineCards() {
        return vaccineCards;
    }

    public void setVaccineCards(List<VaccineCard> vaccineCards) {
        this.vaccineCards = vaccineCards;
    }

    public List<DoctorsAppointment> getDoctorAppointments() {
        return doctorAppointments;
    }

    public void setDoctorAppointments(List<DoctorsAppointment> doctorAppointments) {
        this.doctorAppointments = doctorAppointments;
    }
}
