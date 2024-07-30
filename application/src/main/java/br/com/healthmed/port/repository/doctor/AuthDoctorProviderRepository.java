package br.com.healthmed.port.repository.doctor;

public interface AuthDoctorProviderRepository {

    void signUp(String username, String password, String email);

    String signIn(String username, String password);

}
