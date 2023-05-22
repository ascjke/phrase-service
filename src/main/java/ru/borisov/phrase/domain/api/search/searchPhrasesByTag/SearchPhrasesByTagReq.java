package ru.borisov.phrase.domain.api.search.searchPhrasesByTag;


import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.borisov.phrase.domain.constant.Sort;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SearchPhrasesByTagReq {

    @DecimalMin(value = "1", message = "Значение tagId должго быть больше 0")
    private long tagId;

    @NotNull(message = "sort должен быть заполнен")
    private Sort sort;

}
