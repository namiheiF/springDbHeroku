package com.nami.hamakko.controller;

import java.io.File;
import java.io.IOException;

import org.mybatis.spring.annotation.MapperScan;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nami.hamakko.mybatis.domain.MUserInfo;
import com.nami.hamakko.mybatis.mapper.MUserInfoMapper;

@Controller
@MapperScan("com.nami.hamakko.mybatis.mapper")
public class SampleController {

	private static final String CHROME_DRIVER_PATH = "/app/.chromedriver/bin/chromedriver";
	
	@Autowired
	MUserInfoMapper mui;
	
	@RequestMapping("/index")
    public String index(Model model) {
		MUserInfo s = mui.selectByPrimaryKey("0001");
        model.addAttribute("title", s.getPassword());
        return "index";
    }
	
	@RequestMapping("/selenum1")
	public String selenum1(Model model) throws IOException {
		
		ChromeOptions options = new ChromeOptions();
		// headlessモードで起動
		options.addArguments("--headless");
		options.addArguments("--window-size=1920,1080");
		options.addArguments("start-maximized");
		options.addArguments("--disable-gpu");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-dev-shm-usage");

		// Chrome WebDriver生成処理
		ChromeDriverService service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File (CHROME_DRIVER_PATH))
                .usingPort(9515)
                .build();
        service.start();
		WebDriver driver = new ChromeDriver(service, options);
		driver.get("https://google.co.jp");
		
		sleep(2000);
		
		return "selenum";
	}
	
	@RequestMapping("/selenum2")
	public String selenum2(Model model) {
		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://google.co.jp");
		
		sleep(2000);
		
		return "selenum";
	}
	
	@RequestMapping("/selenum3")
	public String selenum3(Model model) {
		System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://google.co.jp");
		
		sleep(2000);
		
		return "selenum";
	}

	private void sleep(int microtime){
		try{
			Thread.sleep(microtime);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
	
}
