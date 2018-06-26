package com.beiyan.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.beiyan.shop.dao")
public class ShopServerApplication  {

    public static void main(String[] args) {
        SpringApplication.run(ShopServerApplication.class, args);
    }
}
