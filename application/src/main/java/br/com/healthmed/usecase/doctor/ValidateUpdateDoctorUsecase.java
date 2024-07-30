package br.com.healthmed.usecase.doctor;

import br.com.healthmed.exception.doctor.DoctorInvalidException;
import br.com.healthmed.exception.doctor.DoctorNotInformedException;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.usecase.doctor.ValidateUpdateDoctor;

import java.util.Objects;

public class ValidateUpdateDoctorUsecase implements ValidateUpdateDoctor {

    private final LogRepository logRepository;

    public ValidateUpdateDoctorUsecase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void validate(Doctor doctor) {
        logRepository.info(ValidateUpdateDoctorUsecase.class, LogCode.LogCodeInfo._0008);

        if (Objects.isNull(doctor)) {
            throw new DoctorNotInformedException();
        }
        if (!Objects.isNull(doctor.getName()) && doctor.getName().trim().isEmpty()) {
            throw new DoctorInvalidException("name", doctor.getName());
        }
        if (!Objects.isNull(doctor.getEmail()) && doctor.getEmail().trim().isEmpty()) {
            throw new DoctorInvalidException("email", doctor.getEmail());
        }
        if (!Objects.isNull(doctor.getPhone()) && doctor.getPhone().trim().isEmpty()) {
            throw new DoctorInvalidException("phone", doctor.getPhone());
        }
    }
}
