package com.example.demoSpringJDBC.repo;

import com.example.demoSpringJDBC.model.Ders;
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

@Repository @AllArgsConstructor public class DersRepository
{

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Ders> getAll()
    {

        List<Ders> dersler = new ArrayList<>();
        String sql = "SELECT * FROM \"OBS\".\"DERS\"";
        RowMapper<Ders> rowMapper = new RowMapper<Ders>()
        {
            @Override public Ders mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                // strategy pattern
                return new Ders(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        };

        dersler = jdbcTemplate.query(sql, rowMapper);
        return dersler;

    }

    public Ders getById(int id)
    {
        // prepstat
        String sql = "SELECT * FROM \"OBS\".\"DERS\" WHERE \"DERS_ID\" = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Ders>()
        {
            @Override public Ders mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                return new Ders(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        }, id);
    }

    public boolean deleteById(int id)
    {
        // prepstat
        String sql = "DELETE FROM \"OBS\".\"DERS\" WHERE \"DERS_ID\" = :DERS_ID";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("DERS_ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }

    public boolean insert(Ders ders)
    {
        // prepstat
        String sql = "INSERT INTO \"OBS\".\"DERS\"(\"OGRETMEN_ID\", \"KONU_ID\") VALUES (:OGRETMEN_ID, :KONU_ID)";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("OGRETMEN_ID", ders.getOgretmen_id());
        paramMap.put("KONU_ID", ders.getKonu_id());
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }


}