package com.nami.hamakko;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HamakkoApplication {

	public static void main(String[] args) {
		SpringApplication.run(HamakkoApplication.class, args);
//		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
	}

}
