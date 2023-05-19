package ru.borisov.phrase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.borisov.phrase.dao.UserDao;
import ru.borisov.phrase.domain.api.user.getMyPhrases.GetMyPhrasesResp;
import ru.borisov.phrase.domain.api.user.getMyPhrases.PhraseResp;
import ru.borisov.phrase.domain.api.user.login.LoginReq;
import ru.borisov.phrase.domain.api.user.login.LoginResp;
import ru.borisov.phrase.domain.api.user.publicPhrase.PublicPhraseReq;
import ru.borisov.phrase.domain.api.user.registration.RegistrationReq;
import ru.borisov.phrase.domain.api.user.registration.RegistrationResp;
import ru.borisov.phrase.domain.constant.Code;
import ru.borisov.phrase.domain.dto.User;
import ru.borisov.phrase.domain.entity.Phrase;
import ru.borisov.phrase.domain.response.Response;
import ru.borisov.phrase.domain.response.SuccessResponse;
import ru.borisov.phrase.domain.response.exception.CommonException;
import ru.borisov.phrase.service.UserService;
import ru.borisov.phrase.util.EncryptUtils;
import ru.borisov.phrase.util.ValidationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log4j2
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final ValidationUtils validationUtils;
    private final EncryptUtils encryptUtils;
    private final UserDao userDao;


    @Override
    public ResponseEntity<Response> getMyPhrases(String accessToken) {

        long userId = userDao.getUserIdByToken(accessToken);
        List<Phrase> phraseList = userDao.getPhrasesByUserId(userId);

        List<PhraseResp> phraseRespList = new ArrayList<>();
        for (Phrase phrase : phraseList) {
            List<String> tags = userDao.getTagsByPhraseId(phrase.getId());
            phraseRespList.add(PhraseResp.builder()
                    .id(phrase.getId())
                    .text(phrase.getText())
                    .timeInsert(phrase.getTimeInsert())
                    .tags(tags).build());
        }
        return new ResponseEntity<>(SuccessResponse.builder().data(GetMyPhrasesResp.builder().phrases(phraseRespList).build()).build(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Response> publicPhrase(PublicPhraseReq req, String accessToken) {

        validationUtils.validationRequest(req);

        long userId = userDao.getUserIdByToken(accessToken);
        long phraseId = userDao.addPhrase(userId, req.getText());
        log.info("userId: {}, phraseId: {}", userId, phraseId);

        for (String tag : req.getTags()) {
            userDao.addTag(tag);
            userDao.addPhraseTag(phraseId, tag);
        }

        return new ResponseEntity<>(SuccessResponse.builder().build(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Response> login(LoginReq req) {

        validationUtils.validationRequest(req);

        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        String accessToken = userDao.getAccessToken(User.builder().nickname(req.getAuthorization().getNickname()).encryptPassword(encryptPassword).build());
        return new ResponseEntity<>(SuccessResponse.builder().data(LoginResp.builder().accessToken(accessToken).build()).build(), HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Response> registration(RegistrationReq req) {

        validationUtils.validationRequest(req);

        if (userDao.isExistsNickname(req.getAuthorization().getNickname()))
            throw CommonException.builder().code(Code.NICKNAME_BUSY).userMessage("Этот ник уже занят, придумайте другой").httpStatus(HttpStatus.BAD_REQUEST).build();

        String accessToken = UUID.randomUUID().toString().replace("-", "") + System.currentTimeMillis();
        String encryptPassword = encryptUtils.encryptPassword(req.getAuthorization().getPassword());
        userDao.insertNewUser(User.builder().nickname(req.getAuthorization().getNickname()).encryptPassword(encryptPassword).accessToken(accessToken).build());

        return new ResponseEntity<>(SuccessResponse.builder().data(RegistrationResp.builder().accessToken(accessToken).build()).build(), HttpStatus.OK);
    }


}
