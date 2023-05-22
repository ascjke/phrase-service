package ru.borisov.phrase.domain.api.user.publicPhrase;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.borisov.phrase.domain.constant.RegExp;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PublicPhraseReq {

    @NotBlank(message = "text должен быть заполнен")
    @Pattern(regexp = RegExp.PHRASE, message = "Некорректный text")
    private String text;

    @Size(max = 5, message = "Количество тегов не должно превышать 5")
    private List<
            @NotBlank(message = "tag должен быть заполнен")
            @Pattern(regexp = RegExp.TAG, message = "Некорректный tag")
                    String> tags;
}
