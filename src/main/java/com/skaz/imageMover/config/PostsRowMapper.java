package com.skaz.imageMover.config;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.blog.model.posts;

public class PostsRowMapper implements RowMapper<posts> {
    @Override
    public posts mapRow(ResultSet rs, int rowNum) throws SQLException {
        posts post = new posts();
       // rs.
//        post.setId(rs.getInt("ID"));
//        post.get
//        post.setFirstName(rs.getString("FIRST_NAME"));
//        post.setLastName(rs.getString("LAST_NAME"));
//        post.setAddress(rs.getString("ADDRESS"));

        return post;
    }
}