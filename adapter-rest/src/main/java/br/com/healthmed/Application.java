package br.com.healthmed;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
        info = @Info(
                description = "Projeto criado com intuido de viabilizar uma forma facil, segura e rápida para que o paciente tenha o seu prontuário médico sempre a sua disposição e consiga fazer agendamentos de consultas com médicos de qualquer especialidade... e por outro lado os médicos tenham um lugar único onde conseguem ver suas agendas de consultas e conseguir acessar o prontuário médico do paciente que terá a sua consulta...",
                version = "1.0.0",
                title = "Health&Med"
        )
)
@SpringBootApplication
@EnableFeignClients
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}