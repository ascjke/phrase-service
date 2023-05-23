package ru.borisov.phrase.controller.communication;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.borisov.phrase.domain.api.communication.SubscriptionReq;
import ru.borisov.phrase.domain.api.communication.UnsubscriptionReq;
import ru.borisov.phrase.domain.response.Response;
import ru.borisov.phrase.service.SubscriptionService;

@RestController
@RequestMapping("/api/communication")
@RequiredArgsConstructor
@Log4j2
public class SubscriptionController {

    private final SubscriptionService subscriptionService;


    @GetMapping("/getMySubscribers")
    public ResponseEntity<Response> getMySubscribers(@RequestHeader String accessToken) {

        log.info("START endpoint getMySubscribers  accessToken: {}", accessToken);
        ResponseEntity<Response> resp = subscriptionService.getMySubscribers(accessToken);
        log.info("END endpoint getMySubscribers, response: {}", resp);
        return resp;
    }


    @GetMapping("/getMyPublishers")
    public ResponseEntity<Response> getMyPublishers(@RequestHeader String accessToken) {

        log.info("START endpoint getMyPublishers  accessToken: {}", accessToken);
        ResponseEntity<Response> resp = subscriptionService.getMyPublishers(accessToken);
        log.info("END endpoint getMyPublishers, response: {}", resp);
        return resp;
    }


    @PostMapping("/unsubscription")
    public ResponseEntity<Response> unsubscription(@RequestHeader String accessToken, @RequestBody final UnsubscriptionReq req) {

        log.info("START endpoint unsubscription  accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = subscriptionService.unsubscription(req, accessToken);
        log.info("END endpoint unsubscription, response: {}", resp);
        return resp;
    }


    @PostMapping("/subscription")
    public ResponseEntity<Response> subscription(@RequestHeader String accessToken, @RequestBody final SubscriptionReq req) {

        log.info("START endpoint subscription  accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = subscriptionService.subscription(req, accessToken);
        log.info("END endpoint subscription, response: {}", resp);
        return resp;
    }
}
