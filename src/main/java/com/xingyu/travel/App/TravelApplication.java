package com.xingyu.travel.App;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan(basePackages = {"com.xingyu.travel"})
@EnableTransactionManagement//开启事务管理
@MapperScan("com.xingyu.travel.mapper")//Mybatis的DAO所在包
@ServletComponentScan("com.xingyu.travel.servlet")
public class TravelApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}
}
