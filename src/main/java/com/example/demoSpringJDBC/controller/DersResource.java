package com.example.demoSpringJDBC.controller;


import com.example.demoSpringJDBC.model.Ders;
import com.example.demoSpringJDBC.model.Ogrenci;
import com.example.demoSpringJDBC.repo.DersRepository;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.Lombok;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController @AllArgsConstructor @RequestMapping(path = "ders") @io.swagger.annotations.Api(value = "Derslerle ilgili endpointler", tags = "Ders")
// bu topic sayesinde bu class 'ta yapılan bütün loglar bu topic ile bulunabilir
//@Slf4j(topic = "DersResouceTopic")
public class DersResource
{

    DersRepository dersRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // http://localhost:8080/ders/getAll
    @GetMapping(path = "/getAll") public ResponseEntity<List<Ders>> getAll(HttpServletRequest request)
    {
        //log.info("Get all ders yapılıyor");
        logger.info("Get all ders yapılıyor 2 " + request.getRequestURI());
        return new ResponseEntity<>(dersRepository.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/getById/{id}") public ResponseEntity<Ders> getById(@PathVariable(name = "id") int id)
    {
        return new ResponseEntity<>(dersRepository.getById(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteById/{id}") public ResponseEntity<Object> deleteById(@PathVariable(name = "id") int id)
    {
        dersRepository.deleteById(id);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }

    @PostMapping(path = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE) public ResponseEntity<Object> insert(@RequestBody Ders ders)
    {
        dersRepository.insert(ders);
        return new ResponseEntity<>("Başarılı", HttpStatus.OK);
    }

}