package ru.borisov.phrase.util;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

@Log4j2
@Component
@RequiredArgsConstructor
public class EncryptUtils {


    public String encryptPassword(String password) {

        return DigestUtils.md5DigestAsHex(password.getBytes());
    }
}
