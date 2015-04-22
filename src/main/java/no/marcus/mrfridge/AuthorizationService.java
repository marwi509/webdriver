package no.marcus.mrfridge;

public interface AuthorizationService {

    public boolean userIsAuthorized(UserCredentials credentials);

}
