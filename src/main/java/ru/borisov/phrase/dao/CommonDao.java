package ru.borisov.phrase.dao;

import ru.borisov.phrase.domain.api.common.TagResp;

import java.util.List;

public interface CommonDao {

    List<TagResp> getTagsByPhraseId(long phraseId);

    long getUserIdByToken(String accessToken);
}
