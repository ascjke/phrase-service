package ru.borisov.phrase.domain.api.search.searchTags;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.borisov.phrase.domain.constant.RegExp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchTagReq {

    @NotNull(message = "partTag должен быть заполнен")
    @Pattern(regexp = RegExp.TAG, message = "Некорректный partTag")
    private String partTag;
}
