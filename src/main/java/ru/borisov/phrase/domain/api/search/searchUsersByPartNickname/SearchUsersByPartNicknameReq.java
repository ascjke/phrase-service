package ru.borisov.phrase.domain.api.search.searchUsersByPartNickname;

import jakarta.validation.constraints.NotBlank;
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
public class SearchUsersByPartNicknameReq {

    @NotBlank(message = "partNickname должен быть заполнен")
    @Pattern(regexp = RegExp.PART_NICKNAME, message = "Некорректный partNickname")
    private String partNickname;
}
