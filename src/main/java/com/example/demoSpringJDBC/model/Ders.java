package com.example.demoSpringJDBC.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class Ders {

    private int ders_id;
    private int ogretmen_id;
    private int konu_id;

}
