package ru.borisov.phrase.dao;

import ru.borisov.phrase.domain.api.common.TagResp;
import ru.borisov.phrase.domain.api.common.UserResp;
import ru.borisov.phrase.domain.api.search.searchPhrasesByPartWord.SearchPhrasesByPartWordReq;
import ru.borisov.phrase.domain.api.search.searchPhrasesByTag.PhraseResp;
import ru.borisov.phrase.domain.api.search.searchPhrasesByTag.SearchPhrasesByTagReq;

import java.util.List;

public interface SearchDao {
    List<TagResp> searchTags(String partTag);

    List<PhraseResp> searchPhrasesByTag(SearchPhrasesByTagReq req);

    List<PhraseResp> searchPhrasesByPartWord(SearchPhrasesByPartWordReq req);

    List<UserResp> searchUsersByPartNickname(String partNickname);
}
