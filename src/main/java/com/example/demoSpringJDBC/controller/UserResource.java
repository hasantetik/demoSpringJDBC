package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.CustomUserModel;
import com.example.demoSpringJDBC.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.websocket.server.PathParam;
import java.net.URI;

@RestController @AllArgsConstructor @RequestMapping(path = "user")
public class UserResource
{
    UserService userService;

    @GetMapping(path = "/save/{username}/{password}")
    public ResponseEntity<String> saveUser(@PathVariable(value = "username") String username, @PathVariable(value = "password") String password)
    {
        CustomUserModel newUser = new CustomUserModel();
        newUser.setEnabled(true);
        newUser.setUsername(username);
        newUser.setPassword(password);
        newUser.setPasswordConfirm(password);
        userService.save(newUser);
        return new ResponseEntity<>("başarılı", HttpStatus.OK);
    }
}