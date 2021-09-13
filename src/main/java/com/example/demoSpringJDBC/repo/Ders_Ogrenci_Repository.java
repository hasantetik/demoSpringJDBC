package com.example.demoSpringJDBC.repo;

import com.example.demoSpringJDBC.model.Ders_Ogrenci;
import com.example.demoSpringJDBC.model.Ogrenci;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor @Repository public class Ders_Ogrenci_Repository
{

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public List<Ders_Ogrenci> getAll()
    {
        /*try
        {
            int k = 7 / 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
        List<Ders_Ogrenci> ders_ogrenciler = new ArrayList<>();
        String sql = "SELECT * FROM \"OBS\".\"DERS_OGRENCI\"";
        RowMapper<Ders_Ogrenci> rowMapper = new RowMapper<Ders_Ogrenci>()
        {
            @Override public Ders_Ogrenci mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                // strategy pattern
                return new Ders_Ogrenci(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        };
        ders_ogrenciler = jdbcTemplate.query(sql, rowMapper);
        return ders_ogrenciler;
    }

    public Ders_Ogrenci getById(int id)
    {
        // prepstat
        String sql = "SELECT * FROM \"OBS\".\"DERS_OGRENCI\" WHERE \"KAYIT_ID\" = ?";
        return jdbcTemplate.queryForObject(sql, new RowMapper<Ders_Ogrenci>()
        {
            @Override public Ders_Ogrenci mapRow(ResultSet rs, int rowNum) throws SQLException
            {
                return new Ders_Ogrenci(rs.getInt(1), rs.getInt(2), rs.getInt(3));
            }
        }, id);
    }

    public boolean deleteById(int id)
    {
        // prepstat
        String sql = "DELETE FROM \"OBS\".\"DERS_OGRENCI\" WHERE \"KAYIT_ID\" = :KAYIT_ID";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("KAYIT_ID", id);
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }

    public boolean insert(Ders_Ogrenci ders_ogrenci)
    {
        // prepstat
        String sql = "INSERT INTO \"OBS\".\"DERS_OGRENCI\"(\"OGRENCI_ID\",\"DERS_ID\") VALUES (:OGRENCI_ID, :DERS_ID)";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("DERS_ID", ders_ogrenci.getDers_id());
        paramMap.put("OGRENCI_ID", ders_ogrenci.getOgrenci_id());
        return namedParameterJdbcTemplate.update(sql, paramMap) != 0;
    }
    @org.springframework.transaction.annotation.Transactional
    public boolean insertDersOgrenci(int dersId, Ogrenci ogrenci)
    {
        // exception yakalamayın ve fırlatmayın
        boolean res = false;
        String sql = "INSERT INTO \"OBS\".\"OGRENCI\"(\"OGR_ID\", \"OGR_NUM\", \"OGR_NAME\") VALUES (:OGR_ID, :OGR_NUM, :OGR_NAME)";
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("OGR_ID", ogrenci.getId());
        paramMap.put("OGR_NUM", ogrenci.getNumber());
        paramMap.put("OGR_NAME", ogrenci.getName());
        namedParameterJdbcTemplate.update(sql, paramMap);
        //***********************************************************
        sql = "INSERT INTO \"OBS\".\"DERS_OGRENCI\"(\"OGRENCI_ID\", \"DERS_ID\") VALUES (:OGRENCI_ID, :DERS_ID)";
        paramMap = new HashMap<>();
        paramMap.put("OGRENCI_ID", ogrenci.getId());
        //paramMap.put("DERS_ID", dersId);
        res = namedParameterJdbcTemplate.update(sql, paramMap) > 0;
        return res;
    }


}