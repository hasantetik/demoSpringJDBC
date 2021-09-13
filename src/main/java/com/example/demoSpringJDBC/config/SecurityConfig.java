package com.example.demoSpringJDBC.config;

import com.example.demoSpringJDBC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter
{
    @Autowired UserService userDetailsService;
    @Autowired PasswordEncoder passwordEncoder;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception
    {
        // AuthenticationManagerBuilder benim user 'larımı şifrelerini şifreleyip kaydedebilecek
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        // spring security diyor ki WebSecurityConfigurerAdapter class ını extend edersen
        // ve congigure metodunu doldurursan ona göre çalıştırırım
        // burası kapının kurallarını belirlediğimiz yer
        http.csrf().disable();
        http.cors();
        // /ogrenci/getAll endpotini secure ettik
        // ****************************
      //  http.authorizeRequests().antMatchers("/ogrenci/getAll").authenticated();
        http.authorizeRequests().anyRequest().permitAll();
        // ****************************
        http.addFilter(new JWTAuthenticationFilter(authenticationManager()));
        http.addFilter(new JWTAuthorizationFilter(authenticationManager()));
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Bean CorsConfigurationSource corsConfigurationSource()
    {
        // I have no idea what the heck is cors, just copied and pasted it over the internet
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}