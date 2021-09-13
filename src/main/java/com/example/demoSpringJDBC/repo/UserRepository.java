package com.example.demoSpringJDBC.repo;

import com.example.demoSpringJDBC.model.CustomUserModel;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

@Repository @AllArgsConstructor public class UserRepository
{
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public CustomUserModel findByUsername(String username)
    {
        String sql = "SELECT username, password, enabled FROM \"OBS\".users where username = :username";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", username);
        return namedParameterJdbcTemplate.queryForObject(sql, paramMap, new RowMapper<CustomUserModel>()
        {
            @Override public CustomUserModel mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                return new CustomUserModel(rs.getString(1), rs.getString(2), true);
            }
        });
    }

    public boolean save(CustomUserModel userModel)
    {
        String sql = "INSERT INTO \"OBS\".users(username, password, enabled) VALUES (:username, :password, :enabled)";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", userModel.getUsername());
        paramMap.put("password", userModel.getPassword());
        paramMap.put("enabled", userModel.isEnabled());
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }

}