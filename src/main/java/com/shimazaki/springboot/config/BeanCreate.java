package com.shimazaki.springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.shimazaki.springboot.utility.MyTLDialect;
import com.shimazaki.springboot.utility.MyTLUtility;

@Configuration
public class BeanCreate {

	@Bean
	public MyTLDialect dialect() {
		return new MyTLDialect();
	}

	@Bean
	public MyTLUtility utility() {
		return new MyTLUtility();
	}

}
