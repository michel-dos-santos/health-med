package br.com.healthmed.exception.schedule;

public class UpdateNotAcceptException extends RuntimeException {

    public UpdateNotAcceptException(String statusA, String statusB) {
        super(String.format("Agenda com status %s não pode ser atualizada, pois não esta com status %s", statusA));
    }

}