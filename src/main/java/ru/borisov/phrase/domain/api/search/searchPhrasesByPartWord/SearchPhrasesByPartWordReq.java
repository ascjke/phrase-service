package ru.borisov.phrase.domain.api.search.searchPhrasesByPartWord;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.borisov.phrase.domain.constant.RegExp;
import ru.borisov.phrase.domain.constant.Sort;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPhrasesByPartWordReq {

    @NotBlank(message = "partWord должен быть заполнен")
    @Pattern(regexp = RegExp.PART_WORD, message = "Некорректный partWord")
    private String partWord;

    @NotNull(message = "sort должен быть заполнен")
    private Sort sort;
}
