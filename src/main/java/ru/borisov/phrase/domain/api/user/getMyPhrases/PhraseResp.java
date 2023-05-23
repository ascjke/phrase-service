package ru.borisov.phrase.domain.api.user.getMyPhrases;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.borisov.phrase.domain.api.common.TagResp;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhraseResp {

    private long phraseId;
    private String text;
    private String timeInsert;
    private List<TagResp> tags;

//    private long id; запишется STANDARD
//    private long userId;
//    private String text; запишется STRICT
//    private String timeInsert; запишется STRICT
}
