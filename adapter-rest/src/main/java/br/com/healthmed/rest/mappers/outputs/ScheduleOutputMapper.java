package br.com.healthmed.rest.mappers.outputs;

import br.com.healthmed.model.schedule.Schedule;
import br.com.healthmed.rest.mappers.outputs.dtos.doctor.DoctorNameOutputDTO;
import br.com.healthmed.rest.mappers.outputs.dtos.schedule.SchedulingOutputDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ScheduleOutputMapper {

    @Autowired
    private ModelMapper modelMapper;

    public SchedulingOutputDTO mapSchedulingOutputDTOFromSchedule(Schedule schedule) {
        return modelMapper.map(schedule, SchedulingOutputDTO.class);
    }

    public List<SchedulingOutputDTO> mapSchedulingOutputDTOFromSchedules(List<Schedule> schedules) {
        return modelMapper.map(schedules, new TypeToken<List<SchedulingOutputDTO>>() {}.getType());
    }
}
