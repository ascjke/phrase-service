package ru.borisov.phrase.domain.api.common;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TagRespRowMapper implements RowMapper<TagResp> {

    @Override
    public TagResp mapRow(ResultSet rs, int rowNum) throws SQLException {
        return TagResp.builder()
                .id(rs.getLong("id"))
                .text(rs.getString("text"))
                .build();
    }
}
