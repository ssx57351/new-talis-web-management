package com.itheima;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication

@ServletComponentScan
public class TalisApplication {

    public static void main(String[] args) {
        SpringApplication.run(TalisApplication.class, args);
    }

}
