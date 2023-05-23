package ru.borisov.phrase.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.borisov.phrase.domain.api.search.searchPhrasesByPartWord.SearchPhrasesByPartWordReq;
import ru.borisov.phrase.domain.api.search.searchPhrasesByTag.SearchPhrasesByTagReq;
import ru.borisov.phrase.domain.api.search.searchTags.SearchTagReq;
import ru.borisov.phrase.domain.api.search.searchUsersByPartNickname.SearchUsersByPartNicknameReq;
import ru.borisov.phrase.domain.response.Response;
import ru.borisov.phrase.service.SearchService;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/search")
public class SearchController {

    private final SearchService searchService;


    @PostMapping("/searchUsersByPartNickname")
    public ResponseEntity<Response> searchUsersByPartNickname(
            @RequestHeader String accessToken,
            @RequestBody final SearchUsersByPartNicknameReq req) {

        log.info("START endpoint searchUsersByPartNickname  accessToken: {}, request: {}", accessToken, req);
        ResponseEntity<Response> resp = searchService.searchUsersByPartNickname(req, accessToken);
        log.info("END endpoint searchUsersByPartNickname, response: {}", resp);
        return resp;
    }


    @PostMapping("/searchPhrasesByPartWord")
    public ResponseEntity<Response> searchPhrasesByPartWord(
            @RequestHeader String accessToken,
            @RequestBody final SearchPhrasesByPartWordReq req) {

        log.info("START endpoint searchPhrasesByPartWord, accessToken: {}, request: {}", () -> accessToken, () -> req);
        ResponseEntity<Response> response = searchService.searchPhrasesByPartWord(req, accessToken);
        log.info("END endpoint searchPhrasesByPartWord, response: {}", response);
        return response;
    }


    @PostMapping("/searchPhrasesByTag")
    public ResponseEntity<Response> searchPhrasesByTag(
            @RequestHeader String accessToken,
            @RequestBody final SearchPhrasesByTagReq req) {

        log.info("START endpoint searchPhrasesByTag, accessToken: {}, request: {}", () -> accessToken, () -> req);
        ResponseEntity<Response> response = searchService.searchPhrasesByTag(req, accessToken);
        log.info("END endpoint searchPhrasesByTag, response: {}", response);
        return response;
    }


    @PostMapping("/searchTags")
    public ResponseEntity<Response> searchTags(
            @RequestHeader String accessToken,
            @RequestBody final SearchTagReq req) {

        log.info("START endpoint searchTags, accessToken: {}, request: {}", () -> accessToken, () -> req);
        ResponseEntity<Response> response = searchService.searchTags(req, accessToken);
        log.info("END endpoint searchTags, response: {}", response);
        return response;
    }
}
