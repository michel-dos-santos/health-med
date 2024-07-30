package br.com.healthmed.postgres.repository;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.exception.schedule.ScheduleNotFoundException;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.model.schedule.Schedule;
import br.com.healthmed.port.repository.schedule.ScheduleRepository;
import br.com.healthmed.postgres.entity.doctor.DoctorEntity;
import br.com.healthmed.postgres.entity.schedule.ScheduleEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PostgresDBScheduleRepository implements ScheduleRepository {

    private final SpringDataPostgresScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    public PostgresDBScheduleRepository(SpringDataPostgresScheduleRepository scheduleRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Schedule save(Schedule schedule) {
        ScheduleEntity scheduleEntity = modelMapper.map(schedule, ScheduleEntity.class);
        scheduleRepository.save(scheduleEntity);
        return modelMapper.map(scheduleEntity, Schedule.class);
    }

    @Override
    public Schedule identifierById(UUID id) {
        Optional<ScheduleEntity> scheduleEntityOptional = scheduleRepository.findById(id);
        if (scheduleEntityOptional.isEmpty()) {
            throw new ScheduleNotFoundException("id", id.toString());
        }

        return modelMapper.map(scheduleEntityOptional.get(), Schedule.class);
    }

    @Override
    public List<Schedule> findAllByDoctorCPFAndStatus(String cpf, Status status) {
        List<ScheduleEntity> allSchedules = scheduleRepository.findByDoctorCPFAndStatus(cpf, status);
        return modelMapper.map(allSchedules, new TypeToken<List<Schedule>>() {}.getType());
    }

    @Override
    public Schedule findByDoctorCPFAndID(String cpf, UUID id) {
        Optional<ScheduleEntity> scheduleEntityOptional = scheduleRepository.findByDoctorCPFAndID(cpf, id);
        if (scheduleEntityOptional.isEmpty()) {
            throw new ScheduleNotFoundException("id", id.toString());
        }

        return modelMapper.map(scheduleEntityOptional.get(), Schedule.class);
    }

    @Override
    public Schedule findByDoctorCRMAndDateTime(String crm, LocalDateTime dateTime) {
        Optional<ScheduleEntity> scheduleEntityOptional = scheduleRepository.findByDoctorCRMAndDateTime(crm, dateTime);
        if (scheduleEntityOptional.isEmpty()) {
            throw new ScheduleNotFoundException("crm", crm);
        }

        return modelMapper.map(scheduleEntityOptional.get(), Schedule.class);
    }
}
