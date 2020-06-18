package com.nami.hamakko.controller;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nami.hamakko.mybatis.domain.MUserInfo;
import com.nami.hamakko.mybatis.domain.MUserInfoExample;
import com.nami.hamakko.mybatis.mapper.MUserInfoMapper;

@Controller
@MapperScan("com.nami.hamakko.mybatis.mapper")
public class ChusenController {

	@Autowired
	MUserInfoMapper mui;
	
	@RequestMapping("/chusen")
    public String index(Model model) {
		
		MUserInfoExample example = new MUserInfoExample();
		List<MUserInfo> userList = mui.selectByExample(example);
		
        model.addAttribute("userList", userList);
        return "chusen";
    }

	public WebDriver forwordYoyakuPage(){
		WebDriver driver = new ChromeDriver();
//		driver.get(YoyakuConstans.YOYAKU_URL);
		driver.get("https://google.co.jp");
		return driver;
	}
}
