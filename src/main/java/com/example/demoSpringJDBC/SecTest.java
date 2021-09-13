package com.example.demoSpringJDBC;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest @AutoConfigureMockMvc public class SecTest
{
    @Autowired MockMvc mock;

    @Test public void testJWT() throws Exception
    {
        // {"username":"havvanur","password":"asdasdasd"}
        MvcResult res = this.mock.perform(MockMvcRequestBuilders.post("http://localhost:8080/login").contentType(MediaType.APPLICATION_JSON).content("{\"username\":\"havvanur\",\"password\":\"asdasdasd\"}")).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        String bearer = res.getResponse().getContentAsString();
        System.out.println("-------> " + bearer);
        String token = bearer.substring(bearer.indexOf(" ") + 1);
        System.out.println("-------> " + token);
    }
}