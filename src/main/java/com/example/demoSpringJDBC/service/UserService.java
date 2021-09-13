package com.example.demoSpringJDBC.service;

import com.example.demoSpringJDBC.model.CustomUserModel;
import com.example.demoSpringJDBC.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
// UserDetailsService interface inden implement yapıp spring boot a veriyoruz
public class UserService implements UserDetailsService
{
    UserRepository userRepository;

    PasswordEncoder bCryptPasswordEncoder;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
    {
        // spring security diyor ki:
        // bana bir userdetailsservice implementasyonu ver
        // bunun loadUsserByName metodunu implement et
        // org.springframework.security.core.userdetails.UserDetails isimli sınıf ile döndür
        // bu sayede ben bu kodu kullanıcı adı şifre eşleştirmesi için kullanacağım
        CustomUserModel user = userRepository.findByUsername(username);
        User.UserBuilder builder = org.springframework.security.core.userdetails.User.withUsername(user.getUsername());
        builder.password(user.getPassword());
        builder.authorities(user.getRoles());
        return builder.build();
    }

    public boolean save(CustomUserModel user)
    {
        // veritabanına yani repository 'ye gitmeden önce şifre şifrelemek için ideal katman burası
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }
}