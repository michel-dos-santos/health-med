package br.com.healthmed.exception.doctor;

public class DoctorInvalidException extends RuntimeException {

    public DoctorInvalidException(String field, String content) {
        super(String.format("Médico inválido com base no %s: %s", field, content));
    }

}