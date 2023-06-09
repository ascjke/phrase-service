package ru.borisov.phrase.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.borisov.phrase.domain.api.user.login.LoginReq;
import ru.borisov.phrase.domain.api.user.publicPhrase.PublicPhraseReq;
import ru.borisov.phrase.domain.api.user.registration.RegistrationReq;
import ru.borisov.phrase.domain.response.Response;
import ru.borisov.phrase.service.UserService;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;


    @GetMapping("/getMyPhrases")
    public ResponseEntity<Response> getMyPhrases(@RequestHeader final String accessToken) {

        log.info("START endpoint getMyPhrases, accessToken: {}", accessToken);
        ResponseEntity<Response> resp = userService.getMyPhrases(accessToken);
        log.info("END endpoint getMyPhrases, response: {}", resp);
        return resp;
    }


    @PostMapping("/publicPhrase")
    public ResponseEntity<Response> publicPhrase(
            @RequestHeader final String accessToken,
            @RequestBody final PublicPhraseReq req) {

        log.info("START endpoint publicPhrase, accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = userService.publicPhrase(req, accessToken);
        log.info("END endpoint publicPhrase, response: {}", resp);
        return resp;
    }


    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody final LoginReq req) {

        log.info("START endpoint login, request: {}", req);
        ResponseEntity<Response> resp = userService.login(req);
        log.info("END endpoint login, response: {}", resp);
        return resp;
    }


    @PostMapping("/registration")
    public ResponseEntity<Response> registration(@RequestBody final RegistrationReq req) {

        log.info("START endpoint registration, request: {}", req);
        ResponseEntity<Response> resp = userService.registration(req);
        log.info("END endpoint registration, response: {}", resp);
        return resp;
    }
}
