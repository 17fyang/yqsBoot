package com.stu.yqs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.PropertySource;


@SpringBootApplication(scanBasePackages = {"com.stu.yqs.*"})
@MapperScan(basePackages = "com.stu.yqs.dao")
@PropertySource(value = "classpath:yqs.properties", encoding = "utf-8")
public class YqsBootApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(YqsBootApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(this.getClass());
    }
}
