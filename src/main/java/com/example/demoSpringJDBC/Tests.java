package com.example.demoSpringJDBC;

import com.example.demoSpringJDBC.model.Ogretmen;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest @AutoConfigureMockMvc
class Tests
{
    @Autowired MockMvc mock;

    // org.springframework.test.web.servlet.result.MockMvcResultMatchers

    @Test void getOgretmenById() throws Exception
    {
        RequestBuilder request = MockMvcRequestBuilders.get("/ogretmen/getById/1").accept(MediaType.APPLICATION_JSON);
        String response = mock.perform(request).andReturn().getResponse().getContentAsString();
        String ogretmen = new ObjectMapper().writeValueAsString(new Ogretmen(1, "Numan"));
        Assertions.assertEquals(ogretmen, response);
    }

    @Test public void getOgrenciById() throws Exception
    {
        this.mock.perform(MockMvcRequestBuilders.get("/ogrenci/getById/11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.number").value("242"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("11"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Hasan"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test public void insertKonu() throws Exception
    {
        String restponse = this.mock
                .perform(MockMvcRequestBuilders.post("/konu/insert").content("{\"konu\":\"docker\"}").contentType(MediaType.APPLICATION_JSON))
                .andReturn().getResponse().getContentAsString();
        System.out.println("-------> " + restponse);
        Assertions.assertEquals("good morning", restponse);
    }
}