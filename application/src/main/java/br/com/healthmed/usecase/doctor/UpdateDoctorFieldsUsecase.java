package br.com.healthmed.usecase.doctor;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.usecase.doctor.UpdateDoctorFields;
import br.com.healthmed.port.usecase.doctor.ValidateUpdateDoctor;

import java.util.Objects;

public class UpdateDoctorFieldsUsecase implements UpdateDoctorFields {

    private final LogRepository logRepository;
    private final DoctorRepository doctorRepository;
    private final ValidateUpdateDoctor validateDoctor;

    public UpdateDoctorFieldsUsecase(LogRepository logRepository, DoctorRepository doctorRepository, ValidateUpdateDoctor validateDoctor) {
        this.logRepository = logRepository;
        this.doctorRepository = doctorRepository;
        this.validateDoctor = validateDoctor;
    }

    @Override
    public Doctor update(Doctor doctor) {
        logRepository.info(UpdateDoctorFieldsUsecase.class, LogCode.LogCodeInfo._0018);
        Doctor doctorFound = doctorRepository.identifierById(doctor.getId());

        doctorFound.setName(Objects.isNull(doctor.getName()) ? doctorFound.getName() : doctor.getName());
        doctorFound.setEmail(Objects.isNull(doctor.getEmail()) ? doctorFound.getEmail() : doctor.getEmail());
        doctorFound.setPhone(Objects.isNull(doctor.getPhone()) ? doctorFound.getPhone() : doctor.getPhone());

        validateDoctor.validate(doctorFound);
        Doctor doctorSaved = doctorRepository.update(doctorFound);
        logRepository.info(UpdateDoctorFieldsUsecase.class, LogCode.LogCodeInfo._0015);
        return doctorSaved;
    }
}
