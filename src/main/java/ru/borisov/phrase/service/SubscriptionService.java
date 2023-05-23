package ru.borisov.phrase.service;

import org.springframework.http.ResponseEntity;
import ru.borisov.phrase.domain.api.communication.SubscriptionReq;
import ru.borisov.phrase.domain.api.communication.UnsubscriptionReq;
import ru.borisov.phrase.domain.response.Response;

public interface SubscriptionService {

    ResponseEntity<Response> getMySubscribers(String accessToken);

    ResponseEntity<Response> getMyPublishers(String accessToken);

    ResponseEntity<Response> unsubscription(UnsubscriptionReq req, String accessToken);

    ResponseEntity<Response> subscription(SubscriptionReq req, String accessToken);
}
