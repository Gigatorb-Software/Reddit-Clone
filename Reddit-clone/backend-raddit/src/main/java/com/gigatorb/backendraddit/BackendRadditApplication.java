package com.gigatorb.backendraddit;

import com.gigatorb.backendraddit.config.SwaggerConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@Import(SwaggerConfiguration.class)
public class BackendRadditApplication {

	public static void main(String[] args) {

		SpringApplication.run(BackendRadditApplication.class, args);
	}

}
