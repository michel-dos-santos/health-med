package br.com.healthmed.usecase.schedule;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.exception.schedule.ScheduleNotFoundException;
import br.com.healthmed.exception.schedule.ScheduleFoundException;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.model.doctor.Specialty;
import br.com.healthmed.model.schedule.DoctorData;
import br.com.healthmed.model.schedule.Schedule;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.repository.schedule.ScheduleRepository;
import br.com.healthmed.port.usecase.schedule.CreateScheduleTime;

import java.time.LocalDateTime;
import java.util.Objects;

public class CreateScheduleTimeUsecase implements CreateScheduleTime {

    private final LogRepository logRepository;
    private final ScheduleRepository scheduleRepository;
    private final DoctorRepository doctorRepository;

    public CreateScheduleTimeUsecase(ScheduleRepository scheduleRepository, LogRepository logRepository, DoctorRepository doctorRepository) {
        this.logRepository = logRepository;
        this.scheduleRepository = scheduleRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Schedule createdScheduleTime(String crm, LocalDateTime dateTime) {
        logRepository.info(CreateScheduleTimeUsecase.class, LogCode.LogCodeInfo._0001);
        Doctor doctor = doctorRepository.identifierByCRM(crm);
        DoctorData doctorData = new DoctorData();
        doctorData.setName(doctor.getName());
        doctorData.setCpf(doctor.getCpf());
        Specialty specialty = doctor.getSpecialties().stream().filter(s -> s.getCrm().equals(crm)).findFirst().get();
        doctorData.setCrm(crm);
        doctorData.setSpecialty(specialty.getName());
        doctorData.setValue(specialty.getValue());

        Schedule scheduling = new Schedule();
        scheduling.setDateTime(dateTime);
        scheduling.setStatus(Status.DISPONIVEL);
        scheduling.setDoctorData(doctorData);

        try {
            scheduleRepository.findByDoctorCRMAndDateTime(crm, dateTime);
        } catch (ScheduleNotFoundException ex) {
            Schedule scheduleSaved = scheduleRepository.save(scheduling);
            logRepository.info(CreateScheduleTimeUsecase.class, LogCode.LogCodeInfo._0003);

            return scheduleSaved;
        }

        throw new ScheduleFoundException("dateTime", dateTime.toString());
    }

}
