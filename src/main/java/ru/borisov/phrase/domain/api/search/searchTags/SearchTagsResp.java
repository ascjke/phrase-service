package ru.borisov.phrase.domain.api.search.searchTags;

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
public class SearchTagsResp {

    private List<TagResp> tags;
}
