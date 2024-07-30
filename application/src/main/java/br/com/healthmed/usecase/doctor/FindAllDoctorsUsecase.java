package br.com.healthmed.usecase.doctor;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.usecase.doctor.FindAllDoctors;

import java.util.List;

public class FindAllDoctorsUsecase implements FindAllDoctors {

    private final LogRepository logRepository;
    private final DoctorRepository doctorRepository;

    public FindAllDoctorsUsecase(DoctorRepository doctorRepository, LogRepository logRepository) {
        this.logRepository = logRepository;
        this.doctorRepository = doctorRepository;
    }

    @Override
    public List<Doctor> findAll() {
        logRepository.info(FindAllDoctorsUsecase.class, LogCode.LogCodeInfo._0005);
        List<Doctor> doctors = doctorRepository.findAll();
        logRepository.info(FindAllDoctorsUsecase.class, LogCode.LogCodeInfo._0006);
        return doctors;
    }
}
