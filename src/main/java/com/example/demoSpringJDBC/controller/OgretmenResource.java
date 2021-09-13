package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.Ogrenci;
import com.example.demoSpringJDBC.model.Ogretmen;
import com.example.demoSpringJDBC.repo.OgretmenRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.UnsatisfiedDependencyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/ogretmen") public class OgretmenResource
{

    OgretmenRepository ogretmenRepository;

    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Ogretmen>> getAll()
    {
        /*try
        {
            int k = 7 / 0;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
        return new ResponseEntity<>(ogretmenRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/getById/{id}") public ResponseEntity<Ogretmen> getOgrenciById(@PathVariable(name = "id") int id)
    {
        return new ResponseEntity<>(ogretmenRepository.getById(id), HttpStatus.OK);
    }

    @PostMapping(path = "/deleteById/{id}") public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id)
    {
        ogretmenRepository.deleteById(id);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Object> insert(@RequestBody Ogretmen ogretmen)
    {
        ogretmenRepository.insert(ogretmen);
        return new ResponseEntity<>("Başarılı", HttpStatus.OK);
    }
}