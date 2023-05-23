package ru.borisov.phrase.domain.api.communication;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.borisov.phrase.domain.api.common.UserResp;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetMySubscribersResp {

    private List<UserResp> subscribers;
}
