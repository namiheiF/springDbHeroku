package com.nami.hamakko.controller;

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

	@Autowired
	MUserInfoMapper mui;
	
	@RequestMapping("/index")
    public String index(Model model) {
		MUserInfo s = mui.selectByPrimaryKey("0001");
        model.addAttribute("title", s.getPassword());
        return "index";
    }
	
	@RequestMapping("/selenum")
	public String selenum(Model model) {
		forwordYoyakuPage();
		return "selenum";
	}

	public WebDriver forwordYoyakuPage(){
		ChromeOptions options = new ChromeOptions();
		// headlessモードで起動
		options.addArguments("--headless");

		// Chrome WebDriver生成処理
		ChromeDriverService driverService = ChromeDriverService.createDefaultService();
//		driver = new ChromeDriver(driverService, options);
		WebDriver driver = new ChromeDriver(driverService, options);
//		driver.get(YoyakuConstans.YOYAKU_URL);
		driver.get("https://google.co.jp");
		return driver;
	}
}
