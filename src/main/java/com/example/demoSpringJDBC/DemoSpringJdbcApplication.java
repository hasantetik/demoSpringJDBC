package com.example.demoSpringJDBC;

import com.example.demoSpringJDBC.demo.Car;
import com.example.demoSpringJDBC.demo.DemoComp;
import com.example.demoSpringJDBC.demo.Person;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;

// exclude ile auto configuration yok sayılabiliyor
@SpringBootApplication//(exclude = DemoComp.class)
@AllArgsConstructor
public class DemoSpringJdbcApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(DemoSpringJdbcApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    /*@Bean
    public FilterRegistrationBean<CustomFilter> loggingFilter()
    {
        // sadece belirli url 'ler filtrelensin istersek
        FilterRegistrationBean<CustomFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.addUrlPatterns("/users/*");
        return registrationBean;
    }*/

    /*@Bean(value = "araba")
    public Car araba()
    {
        return new Car("Mavi araba");
    }

    @Bean
    @DependsOn("araba")
    public Person getPerson(Car araba)
    {
        return new Person(araba);
    }*/

    /*@Bean public CommandLineRunner commandLineRunner(ApplicationContext ctx)
    {
        return args ->
        {
            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            System.err.println(beanNames.length);
            for (String beanName : beanNames)
            {
                System.err.println(beanName);
            }
        };
    }*/

}