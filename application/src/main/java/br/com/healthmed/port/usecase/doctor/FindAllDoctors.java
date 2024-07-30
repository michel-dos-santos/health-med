package br.com.healthmed.port.usecase.doctor;

import br.com.healthmed.model.doctor.Doctor;

import java.util.List;

public interface FindAllDoctors {

    List<Doctor> findAll();

}
