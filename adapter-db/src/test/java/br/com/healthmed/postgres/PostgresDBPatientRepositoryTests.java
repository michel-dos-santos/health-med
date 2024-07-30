package br.com.healthmed.postgres;

import br.com.healthmed.exception.patient.PatientFoundException;
import br.com.healthmed.exception.patient.PatientNotFoundException;
import br.com.healthmed.model.patient.Patient;
import br.com.healthmed.postgres.repository.PostgresDBPatientRepository;
import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes={br.com.healthmed.postgres.TestApplication.class})
public class PostgresDBPatientRepositoryTests {

    @Autowired
    private PostgresDBPatientRepository patientRepository;

    private static EasyRandom easyRandom;

    @BeforeAll
    public static void beforeTests() {
        easyRandom = new EasyRandom();
    }

    @Test
    public void saveTest() throws Exception {
        Patient patientInput = easyRandom.nextObject(Patient.class);
        patientInput.setId(null);
        patientInput.setCpf("10902661035");
        patientInput.setPhone("5511987654321");

        Patient patientOutput = patientRepository.save(patientInput);
        Optional<Patient> optionalPatientByCPF = Optional.of(patientRepository.identifierByCPF(patientOutput.getCpf()));
        assertThat(optionalPatientByCPF).isPresent();

        Optional<Patient> optionalPatientByID = Optional.of(patientRepository.identifierById(patientOutput.getId()));
        assertThat(optionalPatientByID).isPresent();
    }

    @Test
    public void saveExistsPatientTest() throws Exception {
        Patient patientInput = easyRandom.nextObject(Patient.class);
        patientInput.setId(null);
        patientInput.setCpf("10902661036");
        patientInput.setPhone("5511987654321");

        Patient patientOutput = patientRepository.save(patientInput);
        Optional<Patient> optionalPatientByCPF = Optional.of(patientRepository.identifierByCPF(patientOutput.getCpf()));
        assertThat(optionalPatientByCPF).isPresent();

        Patient patientExists = easyRandom.nextObject(Patient.class);
        patientExists.setId(null);
        patientExists.setCpf(patientOutput.getCpf());

        assertThatThrownBy(() -> patientRepository.save(patientExists))
                .isInstanceOf(PatientFoundException.class)
                .hasMessage(String.format("Paciente já existente com base no %s: %s", "cpf", patientExists.getCpf()));

    }

    @Test
    public void identifierByCPFNotExistsPatientTest() throws Exception {
        Patient patientInput = easyRandom.nextObject(Patient.class);
        patientInput.setId(null);
        patientInput.setCpf("10902661037");

        assertThatThrownBy(() -> patientRepository.identifierByCPF(patientInput.getCpf()))
                .isInstanceOf(PatientNotFoundException.class)
                .hasMessage(String.format("Paciente não encontrado com base no %s: %s", "cpf", patientInput.getCpf()));

    }

    @Test
    public void identifierByIDNotExistsPatientTest() throws Exception {
        Patient patientInput = easyRandom.nextObject(Patient.class);
        patientInput.setId(UUID.randomUUID());
        patientInput.setCpf("10902661038");

        assertThatThrownBy(() -> patientRepository.identifierById(patientInput.getId()))
                .isInstanceOf(PatientNotFoundException.class)
                .hasMessage(String.format("Paciente não encontrado com base no %s: %s", "id", patientInput.getId()));

    }

}
