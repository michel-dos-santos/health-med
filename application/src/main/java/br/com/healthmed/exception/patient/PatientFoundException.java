package br.com.healthmed.exception.patient;

public class PatientFoundException extends RuntimeException {

    public PatientFoundException(String field, String content) {
        super(String.format("Paciente já existente com base no %s: %s", field, content));
    }

}