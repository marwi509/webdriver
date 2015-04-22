package no.marcus.mrfridge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class UserService implements AuthorizationService, RegistrationService{

    private final Map<String, String> registeredUsers = new HashMap<>();

    @Override
    public boolean userIsAuthorized(UserCredentials credentials) {
        String storedPassword = registeredUsers.get(credentials.getUsername());
        if(storedPassword == null) return false;

        return Objects.equals(storedPassword, credentials.getPassword());
    }

    @Override
    public void registerUser(UserCredentials credentials) {
        registeredUsers.put(credentials.getUsername(), credentials.getPassword());
    }
}
