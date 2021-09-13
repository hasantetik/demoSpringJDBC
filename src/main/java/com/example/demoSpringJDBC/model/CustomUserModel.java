package com.example.demoSpringJDBC.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

// benim kendi bussiness user 'ım olacak bu class
// maksat veritabanı ile eşleşsin
@Data @NoArgsConstructor public class CustomUserModel
{
    private String username;
    private String password;
    private String passwordConfirm;
    private boolean enabled = true;
    private List<Role> roles = new ArrayList<>();

    public CustomUserModel(String username, String password, boolean enabled)
    {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }
}