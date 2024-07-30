package br.com.healthmed.model.medicalrecord;

import br.com.healthmed.domain.medicalrecord.TypeExam;

import java.time.LocalDateTime;
import java.util.UUID;

public class Exam {

    private UUID id;
    private TypeExam type;
    private LocalDateTime dateTime;
    private String local;
    private String result;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TypeExam getType() {
        return type;
    }

    public void setType(TypeExam type) {
        this.type = type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
