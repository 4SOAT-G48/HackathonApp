package br.com.fiap.soat4.grupo48.telemed.auth.application.port.out;

import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;

public interface IAuthRepository {
    AdminCreateUserResult registerUser(String username, String password, String email, String cpf);
    AdminInitiateAuthResult loginUsuario(String email, String password);
    AdminInitiateAuthResult loginMedico(String crm, String password);
    AdminInitiateAuthResult loginPaciente(String email, String cpf, String password);
}
