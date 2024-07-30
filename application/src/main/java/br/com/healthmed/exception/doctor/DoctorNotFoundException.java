package br.com.healthmed.exception.doctor;

public class DoctorNotFoundException extends RuntimeException {

    public DoctorNotFoundException(String field, String content) {
        super(String.format("Médico não encontrado com base no %s: %s", field, content));
    }

}