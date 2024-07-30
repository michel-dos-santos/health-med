package br.com.healthmed.model.medicalrecord;

import java.time.LocalDateTime;
import java.util.UUID;

public class VaccineCard {

    private UUID id;
    private String name;
    private LocalDateTime dateTime;
    private String local;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
