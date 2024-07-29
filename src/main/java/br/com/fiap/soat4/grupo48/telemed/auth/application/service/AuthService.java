package br.com.fiap.soat4.grupo48.telemed.auth.application.service;

import br.com.fiap.soat4.grupo48.telemed.commons.helpers.CPFValidator;
import br.com.fiap.soat4.grupo48.telemed.auth.application.port.in.IAuthService;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService implements IAuthService {

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    @Value("${aws.cognito.clientId}")
    private String clientId;

    @Autowired
    private AWSCognitoIdentityProvider cognitoClient;

    public AdminCreateUserResult registrarUsuario(String username, String password, String email, String cpf) {
        if (cpf != null && !cpf.isEmpty() && !CPFValidator.isValidCPF(cpf)) {
            throw new IllegalArgumentException("Invalid CPF");
        }

        List<AttributeType> userAttributes = new ArrayList<>();
        userAttributes.add(new AttributeType().withName("email").withValue(email));
        userAttributes.add(new AttributeType().withName("email_verified").withValue("true"));

        if (cpf != null && !cpf.isEmpty()) {
            userAttributes.add(new AttributeType().withName("custom:cpf").withValue(cpf));
        }

        AdminCreateUserRequest request = new AdminCreateUserRequest()
                .withUserPoolId(userPoolId)
                .withUsername(username)
                .withTemporaryPassword(password)
                .withUserAttributes(userAttributes);

        return cognitoClient.adminCreateUser(request);
    }

    public AdminInitiateAuthResult loginUsuario(String username, String password) {
        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withUserPoolId(userPoolId)
                .withClientId(clientId)
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .addAuthParametersEntry("USERNAME", username)
                .addAuthParametersEntry("PASSWORD", password);

        return cognitoClient.adminInitiateAuth(authRequest);
    }

    public AdminInitiateAuthResult loginMedico(String crm, String password) {
        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withUserPoolId(userPoolId)
                .withClientId(clientId)
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .addAuthParametersEntry("USERNAME", crm)
                .addAuthParametersEntry("PASSWORD", password);

        return cognitoClient.adminInitiateAuth(authRequest);
    }

    public AdminInitiateAuthResult loginPaciente(String email, String cpf, String password) {
        AdminInitiateAuthRequest authRequest = new AdminInitiateAuthRequest()
                .withUserPoolId(userPoolId)
                .withClientId(clientId)
                .withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH)
                .addAuthParametersEntry("USERNAME", email)
                .addAuthParametersEntry("PASSWORD", password);

        AdminInitiateAuthResult authResult = cognitoClient.adminInitiateAuth(authRequest);


        GetUserRequest getUserRequest = new GetUserRequest().withAccessToken(authResult.getAuthenticationResult().getAccessToken());
        GetUserResult getUserResult = cognitoClient.getUser(getUserRequest);

        boolean cpfMatches = getUserResult.getUserAttributes().stream()
                .anyMatch(attribute -> attribute.getName().equals("custom:cpf") && attribute.getValue().equals(cpf));

        if (!cpfMatches) {
            throw new RuntimeException("CPF não encontrado.");
        }

        return authResult;
    }
}