package br.com.healthmed.exception.patient;

public class PatientInvalidException extends RuntimeException {

    public PatientInvalidException(String field, String content) {
        super(String.format("Paciente inválido com base no %s: %s", field, content));
    }

}