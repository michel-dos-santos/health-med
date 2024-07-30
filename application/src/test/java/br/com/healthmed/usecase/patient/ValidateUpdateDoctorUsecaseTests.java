package br.com.healthmed.usecase.patient;


import br.com.healthmed.exception.patient.PatientInvalidException;
import br.com.healthmed.exception.patient.PatientNotInformedException;
import br.com.healthmed.model.LogCode;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.port.repository.LogRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ValidateUpdateDoctorUsecaseTests {

    @InjectMocks
    private ValidatePatientUsecase validateDoctor;
    @Mock
    private LogRepository logRepository;
    private static EasyRandom easyRandom;

    @BeforeAll
    public static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @Test
    public void validateSuccess() throws Exception {
        Patient patient = easyRandom.nextObject(Patient.class);
        patient.setCpf("16741677097");

        validateDoctor.validate(patient);

        verify(logRepository, atLeast(1)).info(ValidatePatientUsecase.class, LogCode.LogCodeInfo._0002);
    }

    @Test
    public void validateWithErrorNullDoctor() throws Exception {
        assertThatThrownBy(() -> validateDoctor.validate(null))
                .isInstanceOf(PatientNotInformedException.class)
                .hasMessage("Paciente não informado");
    }

    @Test
    public void validateWithErrorCPFInvalid() throws Exception {
        Patient patient = easyRandom.nextObject(Patient.class);

        assertThatThrownBy(() -> validateDoctor.validate(patient))
                .isInstanceOf(PatientInvalidException.class)
                .hasMessage(String.format("Paciente inválido com base no %s: %s", "cpf", patient.getCpf()));
    }

    @Test
    public void validateWithErrorNullName() throws Exception {
        Patient patient = easyRandom.nextObject(Patient.class);
        patient.setCpf("16741677097");
        patient.setName(null);

        assertThatThrownBy(() -> validateDoctor.validate(patient))
                .isInstanceOf(PatientInvalidException.class)
                .hasMessage(String.format("Paciente inválido com base no %s: %s", "name", patient.getName()));
    }

    @Test
    public void validateWithErrorEmptyName() throws Exception {
        Patient patient = easyRandom.nextObject(Patient.class);
        patient.setCpf("16741677097");
        patient.setName("");

        assertThatThrownBy(() -> validateDoctor.validate(patient))
                .isInstanceOf(PatientInvalidException.class)
                .hasMessage(String.format("Paciente inválido com base no %s: %s", "name", patient.getName()));
    }

    @Test
    public void validateWithErrorNullEmail() throws Exception {
        Patient patient = easyRandom.nextObject(Patient.class);
        patient.setCpf("16741677097");
        patient.setEmail(null);

        assertThatThrownBy(() -> validateDoctor.validate(patient))
                .isInstanceOf(PatientInvalidException.class)
                .hasMessage(String.format("Paciente inválido com base no %s: %s", "email", patient.getEmail()));
    }

    @Test
    public void validateWithErrorEmptyEmail() throws Exception {
        Patient patient = easyRandom.nextObject(Patient.class);
        patient.setCpf("16741677097");
        patient.setEmail("");

        assertThatThrownBy(() -> validateDoctor.validate(patient))
                .isInstanceOf(PatientInvalidException.class)
                .hasMessage(String.format("Paciente inválido com base no %s: %s", "email", patient.getEmail()));
    }
    
}
