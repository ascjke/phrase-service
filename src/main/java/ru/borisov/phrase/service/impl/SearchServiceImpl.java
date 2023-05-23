package ru.borisov.phrase.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.borisov.phrase.dao.CommonDao;
import ru.borisov.phrase.dao.SearchDao;
import ru.borisov.phrase.domain.api.common.TagResp;
import ru.borisov.phrase.domain.api.search.searchPhrasesByPartWord.SearchPhrasesByPartWordReq;
import ru.borisov.phrase.domain.api.search.searchPhrasesByTag.PhraseResp;
import ru.borisov.phrase.domain.api.search.searchPhrasesByTag.SearchPhrasesByTagReq;
import ru.borisov.phrase.domain.api.search.searchPhrasesByTag.SearchPhrasesByTagResp;
import ru.borisov.phrase.domain.api.search.searchTags.SearchTagReq;
import ru.borisov.phrase.domain.api.search.searchTags.SearchTagsResp;
import ru.borisov.phrase.domain.api.search.searchUsersByPartNickname.SearchUsersByPartNicknameReq;
import ru.borisov.phrase.domain.response.Response;
import ru.borisov.phrase.domain.response.SuccessResponse;
import ru.borisov.phrase.service.SearchService;
import ru.borisov.phrase.util.ValidationUtils;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final SearchDao searchDao;
    private final ValidationUtils validationUtils;
    private final CommonDao commonDao;

    @Override
    public ResponseEntity<Response> searchTags(SearchTagReq req, String accessToken) {

        validationUtils.validationRequest(req);
        commonDao.getUserIdByToken(accessToken); // проверяет авторизацию

        List<TagResp> tagRespList = searchDao.searchTags(req.getPartTag());
        return new ResponseEntity<>(SuccessResponse.builder()
                .data(SearchTagsResp.builder()
                        .tags(tagRespList)
                        .build())
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> searchPhrasesByTag(SearchPhrasesByTagReq req, String accessToken) {

        validationUtils.validationRequest(req);
        commonDao.getUserIdByToken(accessToken);

        List<PhraseResp> phrases = searchDao.searchPhrasesByTag(req);
        for (PhraseResp phraseResp : phrases) {
            List<TagResp> tags = commonDao.getTagsByPhraseId(phraseResp.getPhraseId());
            phraseResp.setTags(tags);
        }
        return new ResponseEntity<>(SuccessResponse.builder()
                .data(SearchPhrasesByTagResp.builder()
                        .phrases(phrases)
                        .build())
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> searchPhrasesByPartWord(SearchPhrasesByPartWordReq req, String accessToken) {

        validationUtils.validationRequest(req);
        commonDao.getUserIdByToken(accessToken);

        List<PhraseResp> phrases = searchDao.searchPhrasesByPartWord(req);
        for (PhraseResp phraseResp : phrases) {
            List<TagResp> tags = commonDao.getTagsByPhraseId(phraseResp.getPhraseId());
            phraseResp.setTags(tags);
        }
        return new ResponseEntity<>(SuccessResponse.builder()
                .data(SearchPhrasesByTagResp.builder()
                        .phrases(phrases)
                        .build())
                .build(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Response> searchUsersByPartNickname(SearchUsersByPartNicknameReq req, String accessToken) {

        validationUtils.validationRequest(req);
        commonDao.getUserIdByToken(accessToken);

        return new ResponseEntity<>(SuccessResponse.builder()
                .data(searchDao.searchUsersByPartNickname(req.getPartNickname()))
                .build(), HttpStatus.OK);
    }
}