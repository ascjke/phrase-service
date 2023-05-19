package ru.borisov.phrase.filter;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.borisov.phrase.domain.constant.Code;
import ru.borisov.phrase.domain.response.error.Error;
import ru.borisov.phrase.domain.response.error.ErrorResponse;

import java.io.IOException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Log4j2
public class AuthorizationFilter extends OncePerRequestFilter {

    // ControllerAdvice отрабатывает после doFilterInternal(), поэтому исключения тут не ловим
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        String accessToken = request.getHeader("AccessToken");
        if (Strings.isEmpty(accessToken)) {
            ErrorResponse errorResponse = ErrorResponse.builder()
                    .error(Error.builder()
                            .code(Code.AUTHORIZATION_ERROR)
                            .userMessage("Ошибка авторизации, выброшенная из фильтра")
                            .build())
                    .build();
            log.error("Отсутствует header AccessToken. ErrorResponse: {}", errorResponse);
            response.getWriter().write(new ObjectMapper().writeValueAsString(errorResponse)); // write Error to response
            response.setStatus(BAD_REQUEST.value());
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            return;
        }
        filterChain.doFilter(request, response);
    }
}
