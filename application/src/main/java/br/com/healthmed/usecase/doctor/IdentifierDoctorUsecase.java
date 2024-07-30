package br.com.healthmed.usecase.doctor;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.usecase.doctor.IdentifierDoctor;

import java.util.UUID;

public class IdentifierDoctorUsecase implements IdentifierDoctor {

    private final LogRepository logRepository;
    private final DoctorRepository doctorRepository;

    public IdentifierDoctorUsecase(DoctorRepository doctorRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor identifierByCPF(String cpf) {
        logRepository.info(IdentifierDoctorUsecase.class, LogCode.LogCodeInfo._0004);
        Doctor doctor = doctorRepository.identifierByCPF(cpf);
        logRepository.info(IdentifierDoctorUsecase.class, LogCode.LogCodeInfo._0006);
        return doctor;
    }
    @Override
    public Doctor identifierById(UUID id) {
        logRepository.info(IdentifierDoctorUsecase.class, LogCode.LogCodeInfo._0005);
        Doctor doctor = doctorRepository.identifierById(id);
        logRepository.info(IdentifierDoctorUsecase.class, LogCode.LogCodeInfo._0006);
        return doctor;
    }
}
