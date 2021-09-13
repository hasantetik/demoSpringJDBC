package com.example.demoSpringJDBC.controller;

import com.example.demoSpringJDBC.model.Ogrenci;
import com.example.demoSpringJDBC.repo.OgrenciRepository;
import com.example.demoSpringJDBC.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(OgrenciResource.class)
public class OgrenciTest
{
    @Autowired MockMvc mock;

    @MockBean OgrenciRepository repo;

    @MockBean UserService service;

    @Test public void getOgrenciByIdTest() throws Exception
    {
        // content 'i almam için davranışı mock 'lamam gerekiyormuşi modellemek
        Mockito.when(repo.getOgrenciById(11)).thenReturn(new Ogrenci(11,242,"Hasan"));
        MvcResult result = this.mock.perform(MockMvcRequestBuilders.get("/ogrenci/getById/11").contentType(MediaType.APPLICATION_JSON)).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        System.out.println("-------> " + result.getResponse().getContentAsString());
    }
}