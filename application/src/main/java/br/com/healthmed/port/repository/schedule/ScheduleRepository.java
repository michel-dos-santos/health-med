package br.com.healthmed.port.repository.schedule;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.model.schedule.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface ScheduleRepository {

    Schedule save(Schedule schedule);
    Schedule identifierById(UUID id);
    List<Schedule> findAllByDoctorCPFAndStatus(String cpf, Status status);
    Schedule findByDoctorCPFAndID(String cpf, UUID id);
    Schedule findByDoctorCRMAndDateTime(String crm, LocalDateTime dateTime);

}
