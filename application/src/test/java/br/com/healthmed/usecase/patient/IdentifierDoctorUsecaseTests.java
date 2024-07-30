package br.com.healthmed.usecase.patient;


import br.com.healthmed.exception.patient.PatientNotFoundException;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.LogRepository;
import br.com.healthmed.port.repository.patient.PatientRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class IdentifierDoctorUsecaseTests {

    @InjectMocks
    private IdentifierPatientUsecase identifierDoctor;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private LogRepository logRepository;
    private static EasyRandom easyRandom;

    @BeforeAll
    public static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @Test
    public void identifierDoctorByCPFFound() throws Exception {
        String cpf = easyRandom.nextObject(String.class);
        Patient patient = easyRandom.nextObject(Patient.class);
        when(patientRepository.identifierByCPF(cpf)).thenReturn(patient);

        Patient patientFound = identifierDoctor.identifierByCPF(cpf);

        assertThat(patientFound).isNotNull();
        assertThat(patientFound).isEqualTo(patient);
        verify(patientRepository, atLeast(1)).identifierByCPF(cpf);
    }

    @Test
    public void identifierDoctorByCPFNotFound() throws Exception {
        String cpf = easyRandom.nextObject(String.class);
        when(patientRepository.identifierByCPF(cpf)).thenThrow(new PatientNotFoundException("cpf", cpf));

        assertThatThrownBy(() -> identifierDoctor.identifierByCPF(cpf))
                .isInstanceOf(PatientNotFoundException.class)
                .hasMessage(String.format("Paciente não encontrado com base no %s: %s", "cpf", cpf));
    }

    @Test
    public void identifierDoctorByIDFound() throws Exception {
        UUID id = easyRandom.nextObject(UUID.class);
        Patient patient = easyRandom.nextObject(Patient.class);
        when(patientRepository.identifierById(id)).thenReturn(patient);

        Patient patientFound = identifierDoctor.identifierById(id);

        assertThat(patientFound).isNotNull();
        assertThat(patientFound).isEqualTo(patient);
        verify(patientRepository, atLeast(1)).identifierById(id);
    }

    @Test
    public void identifierDoctorByIDNotFound() throws Exception {
        UUID id = easyRandom.nextObject(UUID.class);
        when(patientRepository.identifierById(id)).thenThrow(new PatientNotFoundException("id", id.toString()));

        assertThatThrownBy(() -> identifierDoctor.identifierById(id))
                .isInstanceOf(PatientNotFoundException.class)
                .hasMessage(String.format("Paciente não encontrado com base no %s: %s", "id", id));
    }
}
