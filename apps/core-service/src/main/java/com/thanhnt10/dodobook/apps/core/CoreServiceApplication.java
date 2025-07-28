package com.thanhnt10.dodobook.apps.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "com.thanhnt10.dodobook.*",
})
@EnableJpaRepositories(basePackages = {
        "com.thanhnt10.dodobook.*",
//        "com.thanhnt10.dodobook.auditlog.repository",
//        "com.thanhnt10.dodobook.auditlog.interceptor"
})
@EntityScan(basePackages = {
        "com.thanhnt10.dodobook.*",
//        "com.thanhnt10.dodobook.auditlog.model.entity",
//        "com.thanhnt10.dodobook.auditlog.interceptor"
})
public class CoreServiceApplication {
    public static void main(String[] args) {
        // Comment
        SpringApplication.run(CoreServiceApplication.class, args);
    }
}
