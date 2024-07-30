package br.com.healthmed.exception.schedule;

public class ScheduleNotFoundException extends RuntimeException {

    public ScheduleNotFoundException(String field, String content) {
        super(String.format("Agenda não encontrado com base no %s: %s", field, content));
    }

}