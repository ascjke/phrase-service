package ru.borisov.phrase.dao;

import org.springframework.stereotype.Service;
import ru.borisov.phrase.domain.dto.User;
import ru.borisov.phrase.domain.entity.Phrase;

import java.util.List;

@Service
public interface UserDao {

    List<String> getTagsByPhraseId(long phraseId);

    List<Phrase> getPhrasesByUserId(long userId);

    void addPhraseTag(long phraseId, String tag);

    void addTag(String tag);

    long addPhrase(long userId, String text);

    long getUserIdByToken(String accessToken);

    String getAccessToken(User user);

    boolean isExistsNickname(String nickname);

    void insertNewUser(User user);


}
