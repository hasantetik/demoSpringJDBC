package com.example.demoSpringJDBC.repo;

import com.example.demoSpringJDBC.model.Konu;
import com.example.demoSpringJDBC.model.Ogrenci;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
@AllArgsConstructor
public class Konu_Repository {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Konu> getAll()
    {
        List<Konu> konular = new ArrayList<>();
        String sql = "SELECT * FROM \"OBS\".\"KONU\"";
        RowMapper<Konu> rowMapper = new RowMapper<Konu>()
        {
            @Override public Konu mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                // strategy pattern
                return new Konu(rs.getInt(1),rs.getString(2));
            }
        };
        konular = jdbcTemplate.query(sql, rowMapper);
        return konular;
    }

    public Konu getById(int id)
    {
        // prepstat
        String sql = "SELECT * FROM \"OBS\".\"KONU\" WHERE \"KONU_ID\" = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Konu>()
        {
            @Override public Konu mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                return new Konu(rs.getInt(1), rs.getString(2));
            }
        }, id);
    }

    public boolean deleteById(int id)
    {
        // prepstat
        String sql = "DELETE FROM \"OBS\".\"KONU\" WHERE \"KONU_ID\" = :KONU_ID";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("KONU_ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }

    public boolean insert(Konu konu)
    {
        // prepstat
        String sql = "INSERT INTO \"OBS\".\"KONU\"(\"KONU\") VALUES (:KONU)";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("KONU",konu.getKonu());
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }





}
