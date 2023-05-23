package ru.borisov.phrase.domain.api.communication;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UnsubscriptionReq {

    @DecimalMin(value = "1", message = "Значение pubUserId должно быть больше 0")
    private long pubUserId;
}
