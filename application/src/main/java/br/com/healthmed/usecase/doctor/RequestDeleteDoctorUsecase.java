package br.com.healthmed.usecase.doctor;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.usecase.doctor.RequestDeleteDoctor;

import java.util.UUID;

public class RequestDeleteDoctorUsecase implements RequestDeleteDoctor {

    private final LogRepository logRepository;
    private final DoctorRepository doctorRepository;

    public RequestDeleteDoctorUsecase(LogRepository logRepository, DoctorRepository doctorRepository) {
        this.logRepository = logRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public void requestDelete(UUID id) {
        logRepository.info(RequestDeleteDoctorUsecase.class, LogCode.LogCodeInfo._0002);
        Doctor doctorFound = doctorRepository.identifierById(id);

        doctorFound.setDeleteRequested(true);

        doctorRepository.update(doctorFound);
        logRepository.info(UpdateDoctorFieldsUsecase.class, LogCode.LogCodeInfo._0015);
    }
}
