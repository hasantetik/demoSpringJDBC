package com.example.demoSpringJDBC.config;

import org.springframework.context.annotation.Configuration;

// içindeki bean 'leri ayağa kaldırmak için
// xml olmadığı için burada konfigürasyon yaptığımı söylüyorum
//@Configuration
//----------
// bunu sorabilirim
// @Component, @Service, @Repository
// 3 farklı stereotype saysinde kodlar daha anlaşılır olur
public class BeanConfig
{
    // bean olarak tanımladım ki metodu çalıştırsın bean olarak atsın
    // @Bean
    // depends on ile bu bean başka bean 'e bağlıdır mesajı verilir
    // önce dependency olanı oluştur
    // önce araba oluşur sonra öğrenci
    // örnek olarak datasource ve jdbctemplate de olabilir
    // @DependsOn(value = "araba")
    /*public Ogrenci ogrenciBean(araba)
    {
        Ogrenci ogrenci = new Ogrenci();
        ogrenci.setAraba(araba);
        return ogrenci;
    }*/
}