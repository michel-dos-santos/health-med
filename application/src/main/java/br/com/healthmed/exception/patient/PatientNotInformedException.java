package br.com.healthmed.exception.patient;

public class PatientNotInformedException extends RuntimeException {

    public PatientNotInformedException() {
        super(String.format("Paciente não informado"));
    }

}