package ru.borisov.phrase.dao.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.borisov.phrase.dao.CommonDao;
import ru.borisov.phrase.domain.api.common.TagResp;
import ru.borisov.phrase.domain.api.common.TagRespRowMapper;
import ru.borisov.phrase.domain.constant.Code;
import ru.borisov.phrase.domain.response.exception.CommonException;

import javax.sql.DataSource;
import java.util.List;

@Log4j2
@Repository
@Transactional
public class CommonDaoImpl extends JdbcDaoSupport implements CommonDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }

    @Override
    public List<TagResp> getTagsByPhraseId(long phraseId) {

        return jdbcTemplate.query("SELECT text, id FROM tag WHERE id IN (SELECT tag_id FROM phrase_tag WHERE phrase_id = ?);",
                new TagRespRowMapper(), phraseId);
    }

    @Override
    public long getUserIdByToken(String accessToken) {

        try {
            return jdbcTemplate.queryForObject("SELECT id FROM user WHERE access_token = ?;", Long.class, accessToken);
        } catch (EmptyResultDataAccessException ex) {
            log.error(ex.toString());
            throw CommonException.builder()
                    .code(Code.AUTHORIZATION_ERROR)
                    .userMessage("Ошибка авторизации")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
