package com.example.demoSpringJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Ogrenci
{
    // bu sadece id için geçerli
    private int id;

    @javax.validation.constraints.Min(555)
    private int number;
    private String name;

}