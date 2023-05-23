package ru.borisov.phrase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.borisov.phrase.dao.CommonDao;
import ru.borisov.phrase.dao.SubscriptionDao;
import ru.borisov.phrase.domain.api.communication.GetMyPublishersResp;
import ru.borisov.phrase.domain.api.communication.GetMySubscribersResp;
import ru.borisov.phrase.domain.api.communication.SubscriptionReq;
import ru.borisov.phrase.domain.api.communication.UnsubscriptionReq;
import ru.borisov.phrase.domain.constant.Code;
import ru.borisov.phrase.domain.response.Response;
import ru.borisov.phrase.domain.response.SuccessResponse;
import ru.borisov.phrase.domain.response.exception.CommonException;
import ru.borisov.phrase.service.SubscriptionService;
import ru.borisov.phrase.util.ValidationUtils;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Log4j2
@Service
@RequiredArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {

    private final ValidationUtils validationUtils;
    private final SubscriptionDao subscriptionDao;
    private final CommonDao commonDao;


    @Override
    public ResponseEntity<Response> getMySubscribers(String accessToken) {

        long userId = commonDao.getUserIdByToken(accessToken);
        log.info("userId: {}", userId);

        return new ResponseEntity<>(
                SuccessResponse.builder()
                        .data(GetMySubscribersResp.builder()
                                .subscribers(subscriptionDao.getMySubscribers(userId)).build())
                        .build(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Response> getMyPublishers(String accessToken) {

        long userId = commonDao.getUserIdByToken(accessToken);
        log.info("userId: {}", userId);

        return new ResponseEntity<>(
                SuccessResponse.builder()
                        .data(GetMyPublishersResp.builder()
                                .publishers(subscriptionDao.getMyPublishers(userId)).build())
                        .build(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Response> unsubscription(UnsubscriptionReq req, String accessToken) {

        validationUtils.validationRequest(req);
        long subUserId = commonDao.getUserIdByToken(accessToken);

        long pubUserId = req.getPubUserId();
        log.info("subUserId: {}, pubUserId: {},", subUserId, pubUserId);

        subscriptionDao.unsubscription(subUserId, pubUserId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Response> subscription(SubscriptionReq req, String accessToken) {

        validationUtils.validationRequest(req);
        long subUserId = commonDao.getUserIdByToken(accessToken);

        long pubUserId = req.getPubUserId();
        log.info("subUserId: {}, subscriptionUserId: {},", subUserId, pubUserId);

        if (subUserId == pubUserId) {
            throw CommonException.builder()
                    .code(Code.SUBSCRIPTION_LOGIC_ERROR)
                    .userMessage("Вы не можете подписаться на себя")
                    .httpStatus(BAD_REQUEST)
                    .build();
        }

        subscriptionDao.subscription(subUserId, pubUserId);
        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }
}
