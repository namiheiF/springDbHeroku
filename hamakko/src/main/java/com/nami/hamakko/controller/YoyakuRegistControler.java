package com.nami.hamakko.controller;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nami.hamakko.constance.EnPlaceKubun;
import com.nami.hamakko.constance.EnShisetsuShousai;
import com.nami.hamakko.constance.EnShisetsuShousaiKind;
import com.nami.hamakko.constance.EnTimeKubun;
import com.nami.hamakko.dto.YoyakuRegistInfo;
import com.nami.hamakko.mybatis.domain.MUserInfo;
import com.nami.hamakko.repsitory.MUserInfoRepository;
import com.nami.hamakko.service.YoyakuService;

@Controller
public class YoyakuRegistControler {
	
	@Autowired
	MUserInfoRepository rep;
	
	@RequestMapping("/yoyakuRegist")
    public void yoyakuRegist(Model model) {

		System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");

		YoyakuService yoyakuLogic = new YoyakuService();

		WebDriver driver = yoyakuLogic.forwordYoyakuPage();
		// ログイン
		MUserInfo userInfo = rep.getUserInfo("01065082");
		yoyakuLogic.login(driver, userInfo);
		JavascriptExecutor jexec = (JavascriptExecutor) driver;

		for(YoyakuRegistInfo yoyakuRegistInfo : getYoyakuDateTimeDataList()){

			try{
				yoyakuLogic.yoyakuAction(driver, jexec, yoyakuRegistInfo);
			}catch(WebDriverException e){
				System.out.println(e.getStackTrace());
				System.out.println("申し込みを失敗しました。" + yoyakuRegistInfo.yoyakuDate + ":" + yoyakuRegistInfo.placeKubun.getName());
				// TOPに戻る
				jexec.executeScript("toMenu(document.forms[0]);");
			}
		}

		driver.close();
		driver.quit();
		
//		return "selenum";
	}

	/**
	 * 予約登録を行う情報をリストで設定
	 *
	 * @return
	 */
	private static List<YoyakuRegistInfo> getYoyakuDateTimeDataList(){
		List<YoyakuRegistInfo> yoyakuRegistInfoList = new ArrayList<>();

		yoyakuRegistInfoList.add(new YoyakuRegistInfo("20200730", "30", EnTimeKubun._1300, EnPlaceKubun.NISHI, EnShisetsuShousai.TUDUKI, EnShisetsuShousaiKind.ALL));
//		yoyakuRegistInfoList.add(new YoyakuRegistInfo("20181126", "26", EnTimeKubun._2100, EnPlaceKubun.NISHI, EnShisetsuShousai.NISHI, EnShisetsuShousaiKind.ALL));
//		yoyakuRegistInfoList.add(new YoyakuRegistInfo("20181109", "09", EnTimeKubun._1900, EnPlaceKubun.HODOGAYA, EnShisetsuShousai.HODOGAYA, EnShisetsuShousaiKind.B));
//		yoyakuRegistInfoList.add(new YoyakuRegistInfo("20161115", "15", EnTimeKubun._1900, EnPlaceKubun.TOTSUKA, EnShisetsuShousai.TOTSUKA, EnShisetsuShousaiKind.A));
		return yoyakuRegistInfoList;
	}
}
