package br.com.healthmed.usecase.schedule;

import br.com.healthmed.domain.schedule.Status;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.model.doctor.Specialty;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.model.schedule.DoctorData;
import br.com.healthmed.model.schedule.PatientData;
import br.com.healthmed.model.schedule.Schedule;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.repository.patient.PatientRepository;
import br.com.healthmed.port.repository.schedule.ScheduleRepository;
import br.com.healthmed.port.usecase.schedule.GenerateLinkScheduling;
import br.com.healthmed.port.usecase.schedule.CreateScheduling;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateSchedulingUsecase implements CreateScheduling {

    private final LogRepository logRepository;
    private final ScheduleRepository scheduleRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final GenerateLinkScheduling generateLinkScheduling;

    public CreateSchedulingUsecase(ScheduleRepository scheduleRepository, LogRepository logRepository, PatientRepository patientRepository, DoctorRepository doctorRepository, GenerateLinkScheduling generateLinkScheduling) {
        this.logRepository = logRepository;
        this.scheduleRepository = scheduleRepository;
        this.patientRepository = patientRepository;
        this.doctorRepository = doctorRepository;
        this.generateLinkScheduling = generateLinkScheduling;
    }

    @Override
    public Schedule scheduling(UUID patientId, String crm, LocalDateTime dateTime) {
        logRepository.info(CreateSchedulingUsecase.class, LogCode.LogCodeInfo._0001);
        Patient patient = patientRepository.identifierById(patientId);
        PatientData patientData = new PatientData();
        patientData.setName(patient.getName());
        patientData.setCpf(patient.getCpf());

        Schedule scheduling = scheduleRepository.findByDoctorCRMAndDateTime(crm, dateTime);

        scheduling.setStatus(Status.GERADA);
        scheduling.setLink(generateLinkScheduling.generate());
        scheduling.setPatientData(patientData);

        Schedule scheduleSaved = scheduleRepository.save(scheduling);
        logRepository.info(CreateSchedulingUsecase.class, LogCode.LogCodeInfo._0003);

        return scheduleSaved;
    }

}
