package ru.borisov.phrase.service;

import org.springframework.http.ResponseEntity;
import ru.borisov.phrase.domain.api.search.searchPhrasesByTag.SearchPhrasesByTagReq;
import ru.borisov.phrase.domain.api.search.searchTags.SearchTagReq;
import ru.borisov.phrase.domain.response.Response;

public interface SearchService {
    ResponseEntity<Response> searchTags(SearchTagReq req, String accessToken);

    ResponseEntity<Response> searchPhrasesByTag(SearchPhrasesByTagReq req, String accessToken);
}
