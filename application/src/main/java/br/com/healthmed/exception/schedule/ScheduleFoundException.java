package br.com.healthmed.exception.schedule;

public class ScheduleFoundException extends RuntimeException {

    public ScheduleFoundException(String field, String content) {
        super(String.format("Agenda já existente com base no %s: %s", field, content));
    }

}