package br.com.healthmed.model.doctor;

import java.util.List;
import java.util.UUID;

public class Doctor {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String phone;
    private List<Specialty> specialties;
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

    public List<Specialty> getSpecialties() {
        return specialties;
    }

    public void setSpecialties(List<Specialty> specialties) {
        this.specialties = specialties;
    }

    public Boolean getDeleteRequested() {
        return isDeleteRequested;
    }

    public void setDeleteRequested(Boolean deleteRequested) {
        isDeleteRequested = deleteRequested;
    }
}
