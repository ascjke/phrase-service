package ru.borisov.phrase.domain.response.error;

import lombok.Builder;
import lombok.Data;
import ru.borisov.phrase.domain.response.Response;


@Data
@Builder
public class ErrorResponse implements Response {

    private Error error;
}
