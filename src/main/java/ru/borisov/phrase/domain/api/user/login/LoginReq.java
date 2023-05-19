package ru.borisov.phrase.domain.api.user.login;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.borisov.phrase.domain.api.user.common.AuthorizationReq;

import jakarta.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginReq {

    @NotNull(message = "authorization должен быть заполнен")
    private AuthorizationReq authorization;
}
