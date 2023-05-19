package ru.borisov.phrase.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import ru.borisov.phrase.domain.constant.Code;
import ru.borisov.phrase.domain.response.exception.CommonException;

import java.util.Set;

@Log4j2
@Component
@RequiredArgsConstructor
public class ValidationUtils {

    private final Validator validator;


    public <T> void validationRequest(T req) {

        if (req != null) {
            Set<ConstraintViolation<T>> result = validator.validate(req);
            if (!result.isEmpty()) {
                String resultValidations = result.stream()
                        .map(ConstraintViolation::getMessage)
                        .reduce((s1, s2) -> s1 + ". " + s2).orElse("");
                log.error("Переданный в запросе json не валиден, ошибки валидации: {}", resultValidations);
                throw CommonException.builder().code(Code.REQUEST_VALIDATION_ERROR).techMessage(resultValidations).httpStatus(HttpStatus.BAD_REQUEST).build();
            }
        }
    }
}
