package br.com.fiap.soat4.grupo48.telemed.auth.application.port.in;

import com.amazonaws.services.cognitoidp.model.AdminCreateUserResult;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;

public interface IAuthService {
    AdminCreateUserResult registrarUsuario(String username, String password, String email, String cpf);
    AdminInitiateAuthResult loginUsuario(String email, String password);
    AdminInitiateAuthResult loginMedico(String crm, String password);
    AdminInitiateAuthResult loginPaciente(String email, String cpf, String password);
}
