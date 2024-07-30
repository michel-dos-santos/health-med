package br.com.healthmed.port.repository.patient;

public interface AuthPatientProviderRepository {

    void signUp(String username, String password, String email);

    String signIn(String username, String password);

}
