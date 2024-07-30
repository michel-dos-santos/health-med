package br.com.healthmed.usecase.patient;


import br.com.healthmed.exception.patient.PatientNotInformedException;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SaveDoctorUsecaseTests {

    @InjectMocks
    private SavePatientUsecase saveDoctor;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private ValidatePatientUsecase validateDoctor;
    @Mock
    private LogRepository logRepository;
    private static EasyRandom easyRandom;

    @BeforeAll
    public static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @Test
    public void saveDoctorSuccess() throws Exception {
        Patient patient = easyRandom.nextObject(Patient.class);
        when(patientRepository.save(patient)).thenReturn(patient);

        Patient patientSaved = saveDoctor.save(patient);

        assertThat(patientSaved).isNotNull();
        assertThat(patientSaved.getCpf()).isEqualTo(patient.getCpf());
        assertThat(patientSaved.getName()).isEqualTo(patient.getName());
        assertThat(patientSaved.getEmail()).isEqualTo(patient.getEmail());
        verify(validateDoctor, atLeast(1)).validate(patient);
        verify(patientRepository, atLeast(1)).save(patient);
    }

    @Test
    public void saveDoctorWithErrorNullDoctor() throws Exception {
        doThrow(new PatientNotInformedException()).when(validateDoctor).validate(null);

        assertThatThrownBy(() -> saveDoctor.save(null))
                .isInstanceOf(PatientNotInformedException.class)
                .hasMessage("Paciente não informado");
    }

}
