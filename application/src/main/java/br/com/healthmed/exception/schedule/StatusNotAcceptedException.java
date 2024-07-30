package br.com.healthmed.exception.schedule;

public class StatusNotAcceptedException extends RuntimeException {

    public StatusNotAcceptedException(String statusA, String statusB) {
        super(String.format("Status %s não pode ser usado em um agendamento com status %s", statusA, statusB));
    }

}