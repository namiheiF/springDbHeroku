package com.nami.hamakko.controller;

import org.mybatis.spring.annotation.MapperScan;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nami.hamakko.mybatis.domain.MUserInfo;
import com.nami.hamakko.mybatis.mapper.MUserInfoMapper;

@Controller
//Mapperクラスが格納されているパスを明記する必要がある
@MapperScan("com.nami.hamakko.mybatis.mapper")
public class SampleController {

	@Autowired
	MUserInfoMapper mui;
	
	@RequestMapping("/index")
    public String index(Model model) {
		MUserInfo s = mui.selectByPrimaryKey("0001");    // 検索結果をSongsオブジェクトに格納
        model.addAttribute("title", s.getPassword());  // getterで値を取り出す
        forwordYoyakuPage();
        return "index";
    }

	public WebDriver forwordYoyakuPage(){
		WebDriver driver = new ChromeDriver();
//		driver.get(YoyakuConstans.YOYAKU_URL);
		driver.get("https://google.co.jp");
		return driver;
	}
}
