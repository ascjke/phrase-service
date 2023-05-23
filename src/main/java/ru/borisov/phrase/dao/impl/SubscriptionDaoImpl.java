package ru.borisov.phrase.dao.impl;

import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.borisov.phrase.dao.SubscriptionDao;
import ru.borisov.phrase.domain.api.common.UserResp;
import ru.borisov.phrase.domain.api.common.UserRespRowMapper;
import ru.borisov.phrase.domain.constant.Code;
import ru.borisov.phrase.domain.response.exception.CommonException;

import javax.sql.DataSource;
import java.util.List;

@Log4j2
@Repository
@Transactional
public class SubscriptionDaoImpl extends JdbcDaoSupport implements SubscriptionDao {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    @PostConstruct
    private void initialize() {
        setDataSource(dataSource);
    }


    @Override
    public List<UserResp> getMySubscribers(long userId) {

        return jdbcTemplate.query("SELECT id, nickname FROM user WHERE id IN (SELECT sub_user_id FROM subscription WHERE pub_user_id = ?);", new UserRespRowMapper(), userId);
    }


    @Override
    public List<UserResp> getMyPublishers(long userId) {

        return jdbcTemplate.query("SELECT id, nickname FROM user WHERE id IN (SELECT pub_user_id FROM subscription WHERE sub_user_id = ?);", new UserRespRowMapper(), userId);
    }


    @Override
    public void unsubscription(long subUserId, long pubUserId) {

        jdbcTemplate.update("DELETE FROM subscription WHERE sub_user_id = ? AND pub_user_id = ?;", subUserId, pubUserId);
    }


    @Override
    public void subscription(long subUserId, long pubUserId) {

        try {
            jdbcTemplate.update("INSERT INTO subscription(sub_user_id, pub_user_id) VALUES (?,?);", subUserId, pubUserId);
        } catch (DuplicateKeyException ex) {
            log.error(ex.toString());
            throw CommonException.builder()
                    .code(Code.ALREADY_SUBSCRIBED)
                    .userMessage("Вы уже подписаны на этого пользователя")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (DataIntegrityViolationException ex) {
            log.error(ex.toString());
            throw CommonException.builder()
                    .code(Code.PUBLISHER_NOT_FOUND)
                    .userMessage("Не найден пользователь для подписки")
                    .httpStatus(HttpStatus.BAD_REQUEST)
                    .build();
        }
    }
}
