package com.sidequest.storingjson;

import org.springframework.boot.SpringApplication;

public class TestStoringjsonApplication {

	public static void main(String[] args) {
		SpringApplication.from(StoringjsonApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
