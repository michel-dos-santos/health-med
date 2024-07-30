package br.com.healthmed.port.usecase.schedule;

import br.com.healthmed.model.schedule.Schedule;

import java.time.LocalDateTime;
import java.util.UUID;

public interface UpdateDateTimeScheduleByDoctor {

    Schedule updateDateTime(UUID doctorId, UUID scheduleId, LocalDateTime dateTime);

}
