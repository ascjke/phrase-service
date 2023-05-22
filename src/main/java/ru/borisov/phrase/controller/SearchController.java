package ru.borisov.phrase.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.borisov.phrase.domain.api.search.searchPhrasesByTag.SearchPhrasesByTagReq;
import ru.borisov.phrase.domain.api.search.searchTags.SearchTagReq;
import ru.borisov.phrase.domain.response.Response;
import ru.borisov.phrase.service.SearchService;


@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("phrase-service-public/search")
public class SearchController {

    private final SearchService searchService;

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
