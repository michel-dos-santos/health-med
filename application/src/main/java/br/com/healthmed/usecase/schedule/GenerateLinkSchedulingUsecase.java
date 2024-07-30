package br.com.healthmed.usecase.schedule;

import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.usecase.schedule.GenerateLinkScheduling;

public class GenerateLinkSchedulingUsecase implements GenerateLinkScheduling {

    private final LogRepository logRepository;

    public GenerateLinkSchedulingUsecase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }


}
