package com.br.stylesync;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class StylesyncApplication {


    public static void main(String[] args) {
        SpringApplication.run(StylesyncApplication.class, args);
        PasswordEncoder encode = new BCryptPasswordEncoder();
        System.out.println(encode.encode("123"));
    }

}
