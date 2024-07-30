package br.com.healthmed.port.usecase.schedule;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.model.schedule.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface FindAllScheduleByDoctorAndStatus {

    List<Schedule> findAll(UUID doctorId, Status status);

}
