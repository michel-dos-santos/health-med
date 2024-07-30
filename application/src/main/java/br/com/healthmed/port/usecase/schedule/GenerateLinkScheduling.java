package br.com.healthmed.port.usecase.schedule;

import java.time.LocalDateTime;

public interface GenerateLinkScheduling {

    default String generate() {
        return String.format("https://consultaonline.com.br/%s", LocalDateTime.now().getNano());
    }

}
