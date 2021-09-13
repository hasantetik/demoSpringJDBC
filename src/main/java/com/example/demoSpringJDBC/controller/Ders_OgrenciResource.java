package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.Ders_Ogrenci;
import com.example.demoSpringJDBC.model.Ogrenci;
import com.example.demoSpringJDBC.repo.Ders_Ogrenci_Repository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @AllArgsConstructor @RequestMapping(path = "/ders_ogrenci") public class Ders_OgrenciResource
{
    Ders_Ogrenci_Repository ders_ogrenci_repository;

    @GetMapping(path = "/getAll") public ResponseEntity<List<Ders_Ogrenci>> getAll()
    {
        return new ResponseEntity<>(ders_ogrenci_repository.getAll(), HttpStatus.OK);

    }

    @GetMapping(path = "/getById/{id}") public ResponseEntity<Ders_Ogrenci> getById(@PathVariable(name = "id") int id)
    {
        return new ResponseEntity<>(ders_ogrenci_repository.getById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/deleteById/{id}") public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id)
    {
        ders_ogrenci_repository.deleteById(id);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Object> insert(@RequestBody Ders_Ogrenci ders_ogrenci)
    {
        ders_ogrenci_repository.insert(ders_ogrenci);
        return new ResponseEntity<>("Başarılı", HttpStatus.OK);
    }




    @PostMapping(path = "/insertV2/{dersId}", consumes = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Object> insertV2(@RequestBody Ogrenci ogrenci, @PathVariable int dersId)
    {
        try
        {
            ders_ogrenci_repository.insertDersOgrenci(dersId, ogrenci);
            return new ResponseEntity<>("Başarılı", HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.err.println(e.getClass().getName());
            e.printStackTrace();
            return new ResponseEntity<>("Başarısııııııızzzzzzz", HttpStatus.OK);
        }
    }
}