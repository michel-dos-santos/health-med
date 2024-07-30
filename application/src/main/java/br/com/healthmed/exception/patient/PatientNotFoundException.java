package br.com.healthmed.exception.patient;

public class PatientNotFoundException extends RuntimeException {

    public PatientNotFoundException(String field, String content) {
        super(String.format("Paciente não encontrado com base no %s: %s", field, content));
    }

}