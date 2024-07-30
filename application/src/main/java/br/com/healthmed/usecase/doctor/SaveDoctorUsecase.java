package br.com.healthmed.usecase.doctor;

import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.port.usecase.doctor.SaveDoctor;
import br.com.healthmed.port.usecase.doctor.ValidateDoctor;

public class SaveDoctorUsecase implements SaveDoctor {

    private final LogRepository logRepository;
    private final DoctorRepository doctorRepository;
    private final ValidateDoctor validateDoctor;

    public SaveDoctorUsecase(DoctorRepository doctorRepository, LogRepository logRepository, ValidateDoctor validateDoctor) {
        this.logRepository = logRepository;
        this.doctorRepository = doctorRepository;
        this.validateDoctor = validateDoctor;
    }

    @Override
    public Doctor save(Doctor doctor) {
        logRepository.info(SaveDoctorUsecase.class, LogCode.LogCodeInfo._0001);
        validateDoctor.validate(doctor);
        Doctor doctorSaved = doctorRepository.save(doctor);
        logRepository.info(SaveDoctorUsecase.class, LogCode.LogCodeInfo._0003);

        return doctorSaved;
    }
}
