package com.ZEST_CRM._SYSTEM;

import com.ZEST_CRM._SYSTEM.config.ModelMapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ModelMapperConfig.class)
@ComponentScan(basePackages = "com.ZEST_CRM._SYSTEM")

public class ZestCrmSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZestCrmSystemApplication.class, args);
	}

}
