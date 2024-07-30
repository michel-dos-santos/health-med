package br.com.healthmed.exception.doctor;

public class DoctorNotInformedException extends RuntimeException {

    public DoctorNotInformedException() {
        super(String.format("Médico não informado"));
    }

}