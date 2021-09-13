package com.example.demoSpringJDBC.repo;


import com.example.demoSpringJDBC.model.Ogrenci;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@org.springframework.stereotype.Repository
@AllArgsConstructor
public class OgrenciRepository {

    // jdbc template inject edilir
    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;


    public List<Ogrenci> getAll()
    {
        List<Ogrenci> ogrenciler = new ArrayList<>();
        String sql = "SELECT * FROM \"OBS\".\"OGRENCI\"";
        RowMapper<Ogrenci> rowMapper = new RowMapper<Ogrenci>()
        {
            @Override public Ogrenci mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                // strategy pattern
                return new Ogrenci(rs.getInt(1),rs.getInt(2), rs.getString(3));
            }
        };
        ogrenciler = jdbcTemplate.query(sql, rowMapper);
        return ogrenciler;
    }

    public Ogrenci getOgrenciById(int id)
    {
        // prepstat
        String sql = "SELECT * FROM \"OBS\".\"OGRENCI\" WHERE \"OGR_ID\" = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Ogrenci>()
        {
            @Override public Ogrenci mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                return new Ogrenci(rs.getInt(1), rs.getInt(2), rs.getString(3));
            }
        }, id);
    }

    public boolean insert(Ogrenci ogrenci)
    {
        // prepstat
        String sql = "INSERT INTO \"OBS\".\"OGRENCI\"(\"OGR_NUM\", \"OGR_NAME\") VALUES (:OGR_NUMBER, :OGR_NAME)";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("OGR_NUMBER", ogrenci.getNumber());
        paramMap.put("OGR_NAME", ogrenci.getName());
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }

    public boolean deleteById(int id)
    {
        // prepstat
        String sql = "DELETE FROM \"OBS\".\"OGRENCI\" WHERE \"OGR_ID\" = :OGRENCI_ID";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("OGRENCI_ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }




}
