package br.com.healthmed.port.usecase.schedule;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.model.schedule.Schedule;

import java.util.UUID;

public interface UpdateStatusScheduleByDoctor {

    Schedule updateStatus(UUID doctorId, UUID scheduleId, Status status);

}
