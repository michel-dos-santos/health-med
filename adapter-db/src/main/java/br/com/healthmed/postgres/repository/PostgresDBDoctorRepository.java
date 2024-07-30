package br.com.healthmed.postgres.repository;

import br.com.healthmed.exception.doctor.DoctorFoundException;
import br.com.healthmed.exception.doctor.DoctorNotFoundException;
import br.com.healthmed.model.doctor.Doctor;
import br.com.healthmed.port.repository.doctor.DoctorRepository;
import br.com.healthmed.postgres.entity.doctor.DoctorEntity;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Component
public class PostgresDBDoctorRepository implements DoctorRepository {

    private final SpringDataPostgresDoctorRepository doctorRepository;
    private final ModelMapper modelMapper;

    public PostgresDBDoctorRepository(SpringDataPostgresDoctorRepository doctorRepository, ModelMapper modelMapper) {
        this.doctorRepository = doctorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Doctor save(Doctor doctor) {
        if (existsByCPF(doctor.getCpf())) {
            throw new DoctorFoundException("cpf", doctor.getCpf());
        }

        DoctorEntity doctorEntity = modelMapper.map(doctor, DoctorEntity.class);
        if (!Objects.isNull(doctorEntity.getSpecialties()) && !doctorEntity.getSpecialties().isEmpty()) {
            doctorEntity.getSpecialties().forEach(s -> s.setDoctor(doctorEntity));
        }
        doctorRepository.save(doctorEntity);
        return modelMapper.map(doctorEntity, Doctor.class);
    }

    @Override
    public Doctor update(Doctor doctor) {
        DoctorEntity doctorEntity = modelMapper.map(identifierById(doctor.getId()), DoctorEntity.class);

        doctorEntity.setName(doctor.getName());
        doctorEntity.setEmail(doctor.getEmail());
        doctorEntity.setPhone(doctor.getPhone());
        doctorEntity.setIsDeleteRequested(doctor.getDeleteRequested());
        if (!Objects.isNull(doctorEntity.getSpecialties()) && !doctorEntity.getSpecialties().isEmpty()) {
            doctorEntity.getSpecialties().forEach(s -> s.setDoctor(doctorEntity));
        }

        doctorRepository.save(doctorEntity);
        return modelMapper.map(doctorEntity, Doctor.class);
    }

    @Override
    public Doctor identifierByCPF(String cpf) {
        Optional<DoctorEntity> doctorEntityOptional = doctorRepository.findByCpf(cpf);
        if (doctorEntityOptional.isEmpty()) {
            throw new DoctorNotFoundException("cpf", cpf);
        }

        return modelMapper.map(doctorEntityOptional.get(), Doctor.class);
    }

    @Override
    public Doctor identifierByCRM(String crm) {
        Optional<DoctorEntity> doctorEntityOptional = doctorRepository.findByCrm(crm);
        if (doctorEntityOptional.isEmpty()) {
            throw new DoctorNotFoundException("crm", crm);
        }

        return modelMapper.map(doctorEntityOptional.get(), Doctor.class);
    }

    @Override
    @Transactional
    public boolean existsByCPF(String cpf) {
        return doctorRepository.existsByCpf(cpf);
    }

    @Override
    public List<Doctor> findAll() {
        List<DoctorEntity> allDoctors = doctorRepository.findAll();
        return modelMapper.map(allDoctors, new TypeToken<List<Doctor>>() {}.getType());
    }

    @Override
    public Doctor identifierById(UUID id) {
        Optional<DoctorEntity> doctorEntityOptional = doctorRepository.findById(id);
        if (doctorEntityOptional.isEmpty()) {
            throw new DoctorNotFoundException("id", id.toString());
        }

        return modelMapper.map(doctorEntityOptional.get(), Doctor.class);
    }

}
