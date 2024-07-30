package br.com.healthmed.port.usecase.schedule;

import br.com.healthmed.model.schedule.Schedule;

import java.time.LocalDateTime;
import java.util.UUID;

public interface CreateScheduling {

    Schedule scheduling(UUID patientId, String crm, LocalDateTime dateTime);

}
