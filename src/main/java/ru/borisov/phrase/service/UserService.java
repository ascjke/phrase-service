package ru.borisov.phrase.service;

import org.springframework.http.ResponseEntity;
import ru.borisov.phrase.domain.api.user.login.LoginReq;
import ru.borisov.phrase.domain.api.user.publicPhrase.PublicPhraseReq;
import ru.borisov.phrase.domain.api.user.registration.RegistrationReq;
import ru.borisov.phrase.domain.response.Response;

public interface UserService {

    ResponseEntity<Response> getMyPhrases(String accessToken);

    ResponseEntity<Response> publicPhrase(PublicPhraseReq req, String accessToken);

    ResponseEntity<Response> login(LoginReq req);

    ResponseEntity<Response> registration(RegistrationReq req);
}
