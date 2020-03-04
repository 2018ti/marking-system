package com.xiaoman;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@MapperScan("com.xiaoman.mapper")
public class DemoApplication {

	public static void main(String[] args) {
		System.out.println("火箭发射");
		SpringApplication.run(DemoApplication.class, args);
	}

}
