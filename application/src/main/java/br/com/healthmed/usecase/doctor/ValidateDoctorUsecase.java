package br.com.healthmed.usecase.doctor;

import br.com.healthmed.exception.doctor.DoctorInvalidException;
import br.com.healthmed.exception.doctor.DoctorNotInformedException;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.usecase.doctor.ValidateDoctor;
import br.com.healthmed.utils.CpfValidator;

import java.util.Objects;

public class ValidateDoctorUsecase implements ValidateDoctor {

    private final LogRepository logRepository;

    public ValidateDoctorUsecase(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    @Override
    public void validate(Doctor doctor) {
        logRepository.info(ValidateDoctorUsecase.class, LogCode.LogCodeInfo._0002);

        if (Objects.isNull(doctor)) {
            throw new DoctorNotInformedException();
        }

        if (!CpfValidator.isValid(doctor.getCpf())) {
            throw new DoctorInvalidException("cpf", doctor.getCpf());
        }

        if (Objects.isNull(doctor.getName()) || doctor.getName().trim().isEmpty()) {
            throw new DoctorInvalidException("name", doctor.getName());
        }

        if (Objects.isNull(doctor.getEmail()) || doctor.getEmail().trim().isEmpty()) {
            throw new DoctorInvalidException("email", doctor.getEmail());
        }
    }
}
