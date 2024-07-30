package br.com.healthmed.exception.doctor;

public class DoctorFoundException extends RuntimeException {

    public DoctorFoundException(String field, String content) {
        super(String.format("Médico já existente com base no %s: %s", field, content));
    }

}