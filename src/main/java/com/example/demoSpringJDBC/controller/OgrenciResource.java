package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.Ogrenci;
import com.example.demoSpringJDBC.repo.OgrenciRepository;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;


@RestController @AllArgsConstructor @RequestMapping(path = "/ogrenci")
@io.swagger.annotations.Api(value = "Ogrenci Resource", tags = "Ogrenci")
@Validated
public class OgrenciResource
{
    OgrenciRepository ogrenciRepository;

    // http://localhost:8080/ogrenci/helloSpringBoot
    //artık proje adı yazmaya gerek yok.
    @springfox.documentation.annotations.ApiIgnore
    @GetMapping(path = "/helloSpringBoot") public String helloSpringBoot()
    {
        return "hello SpringBoot";
    }


    // http://localhost:8080/ogrenci/getAll
    @GetMapping(path = "/getAll") public ResponseEntity<List<Ogrenci>> getAll()
    {
        return new ResponseEntity<>(ogrenciRepository.getAll(), HttpStatus.OK);
    }

    // http://localhost:8080/ogrenci/getById/2
    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<Ogrenci> getOgrenciById(@Min(2) @PathVariable(name = "id") int id)
    {
        return new ResponseEntity<>(ogrenciRepository.getOgrenciById(id), HttpStatus.OK);
    }

    // http://localhost:8080/ogrenci/insert
    // öğrenci class 'ı validate edilir
    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> insert(@RequestBody Ogrenci ogrenci)
    {
        ogrenciRepository.insert(ogrenci);
        return new ResponseEntity<>("Başarılı", HttpStatus.OK);
    }

    // http://localhost:8080/ogrenci/deleteById/{id}
    @PostMapping(path = "/deleteById/{id}") public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id)
    {
        ogrenciRepository.deleteById(id);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }


}