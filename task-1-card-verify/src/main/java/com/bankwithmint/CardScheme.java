package com.bankwithmint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Adewale Azeez <azeezadewale98@gmail.com>
 * @date 28-Aug-20 07:32 AM
 */
@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
public class CardScheme {

    public static void main(String[] args) {
        SpringApplication.run(CardScheme.class, args);
    }

}
