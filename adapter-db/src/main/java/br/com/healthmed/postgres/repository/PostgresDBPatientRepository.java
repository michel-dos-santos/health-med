package br.com.healthmed.postgres.repository;

import br.com.healthmed.exception.patient.PatientFoundException;
import br.com.healthmed.exception.patient.PatientNotFoundException;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.patient.PatientRepository;
import br.com.healthmed.postgres.entity.patient.PatientEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Component
public class PostgresDBPatientRepository implements PatientRepository {

    private final SpringDataPostgresPatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PostgresDBPatientRepository(SpringDataPostgresPatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    @Transactional
    public Patient save(Patient patient) {
        if (existsByCPF(patient.getCpf())) {
            throw new PatientFoundException("cpf", patient.getCpf());
        }

        PatientEntity patientEntity = modelMapper.map(patient, PatientEntity.class);
        patientRepository.save(patientEntity);
        return modelMapper.map(patientEntity, Patient.class);
    }

    @Override
    public Patient update(Patient patient) {
        PatientEntity patientEntity = modelMapper.map(identifierById(patient.getId()), PatientEntity.class);

        patientEntity.setName(patient.getName());
        patientEntity.setEmail(patient.getEmail());
        patientEntity.setPhone(patient.getPhone());
        patientEntity.setIsDeleteRequested(patient.getDeleteRequested());

        patientRepository.save(patientEntity);
        return modelMapper.map(patientEntity, Patient.class);
    }

    @Override
    @Transactional
    public boolean existsByCPF(String cpf) {
        return patientRepository.existsByCpf(cpf);
    }

    @Override
    public Patient identifierByCPF(String cpf) {
        Optional<PatientEntity> patientEntityOptional = patientRepository.findByCpf(cpf);
        if (patientEntityOptional.isEmpty()) {
            throw new PatientNotFoundException("cpf", cpf);
        }

        return modelMapper.map(patientEntityOptional.get(), Patient.class);
    }

    @Override
    public Patient identifierById(UUID id) {
        Optional<PatientEntity> patientEntityOptional = patientRepository.findById(id);
        if (patientEntityOptional.isEmpty()) {
            throw new PatientNotFoundException("id", id.toString());
        }

        return modelMapper.map(patientEntityOptional.get(), Patient.class);
    }

}
