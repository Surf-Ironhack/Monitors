package com.Surf.monitors_surf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class MonitorsSurfApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitorsSurfApplication.class, args);
	}

}
