package com.nami.hamakko.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.nami.hamakko.constance.EnPlaceKubun;
import com.nami.hamakko.constance.EnShisetsuShousai;
import com.nami.hamakko.constance.EnShisetsuShousaiKind;
import com.nami.hamakko.constance.YoyakuConstans;
import com.nami.hamakko.dto.YoyakuDateTimeData;
import com.nami.hamakko.dto.YoyakuRegistInfo;
import com.nami.hamakko.mybatis.domain.MUserInfo;

public class YoyakuService {
	/**
	 * ページ遷移
	 *
	 * @return
	 */
	public WebDriver forwordYoyakuPage(){
		WebDriver driver = new ChromeDriver();
		driver.get(YoyakuConstans.YOYAKU_URL);
//		driver.get("https://google.co.jp");
		return driver;
	}

	/**
	 * ログインを行う
	 *
	 * @param driver
	 */
	public void login(WebDriver driver, MUserInfo userInfo){
		// ログインIDの設定
		WebElement searchBox = driver.findElement(By.name(YoyakuConstans.TOP_LOGIN_TEXT_ID));
        searchBox.sendKeys(userInfo.getUser_id());
        // パスワードの設定
        WebElement passwordBox = driver.findElement(By.name(YoyakuConstans.TOP_PASSWORD_TEXT_ID));
        passwordBox.sendKeys(userInfo.getPassword());

        // ログインボタン押下
        driver.findElement(By.id(YoyakuConstans.TOP_LOGIN_BUTTON_ID)).click();
	}

	/**
	 * ログアウト
	 *
	 * @param jexec
	 */
	public void logout(JavascriptExecutor jexec){
		jexec.executeScript(YoyakuConstans.LOGOUT_SCRIPT);
	}

	public void moushikomiAction(WebDriver driver, JavascriptExecutor jexec, YoyakuDateTimeData yoyakuDateTimeData){
		// 抽選申し込みボタン押下
		driver.findElement(By.id(YoyakuConstans.TOP_CHUSEN_BUTTON_ID)).click();
		sleep(1000);
		// 施設分類選択
		jexec.executeScript(YoyakuConstans.SHISETSU_BUNRUI_SCRIPT);
		sleep(1000);

		if(EnPlaceKubun.HIRANUMA == yoyakuDateTimeData.placeKubun) {
			// 申込枠区分選択
			jexec.executeScript(YoyakuConstans.MOUSHIKOMI_KUBUN_SCRIPT_HIRANUMA);
			sleep(1000);
			// 室場選択
			jexec.executeScript("fcSubmitBtn('1902','体育室（深夜）');");
		} else {
			// 申込枠区分選択
			jexec.executeScript(YoyakuConstans.MOUSHIKOMI_KUBUN_SCRIPT);
			sleep(1000);
			// 室場選択
			selectShitsuJyo(jexec, driver, yoyakuDateTimeData);
		}
		sleep(1000);
		// 日付選択
		jexec.executeScript("fcSubmitSlctDay('" + yoyakuDateTimeData.yoyakuDay + "')");
		sleep(1000);
		// 時間選択
		selectTime(jexec, yoyakuDateTimeData);
		sleep(1000);
		// 申し込みボタン押下
		jexec.executeScript("javascript:return fcSubmitRestrict(FRM_RSGK407,'MOSHIKOMI_BTN','rsv.bean.RSGK407BusinessMoshikomiBtn','RSGK407');");
		sleep(1000);
		// 次へボタン押下
		jexec.executeScript("fcSubmitRestrict(FRM_RSGK407,'KAKUNIN_BTN','rsv.bean.RSGK408BusinessInit','RSGK407');");
		sleep(1000);
		// 利用目的選択ボタン押下
		clickRiyouMokuteki(jexec, yoyakuDateTimeData);
		sleep(1000);
		// 利用目的選択
		selectRiyouMokuteki(jexec, yoyakuDateTimeData);
		sleep(1000);
		// 次へボタン押下
		jexec.executeScript("fcSubmitRestrict(FRM_RSGK409,'BACK_BTN','rsv.bean.RSGK408BusinessFromRSGK409','RSGK409');");
		sleep(1000);
		// 確定ボタン押下
		//jexec.executeScript("fcSubmitRestrict(FRM_RSGK408,'KAKUTEI_BTN','rsv.bean.RSGK408BusinessKakuteiBtn','RSGK408');");
		// メニューへボタン押下 TODO 必要か？
		jexec.executeScript("toMenu(document.forms[0]);");
		sleep(1000);
	}

	/**
	 * 予約登録
	 *
	 * @param driver
	 * @param jexec
	 * @param yoyakuRegistInfo
	 */
	public void yoyakuAction(WebDriver driver, JavascriptExecutor jexec, YoyakuRegistInfo yoyakuRegistInfo){
		// 空状況照会・予約（施設から選択）ボタン押下
		driver.findElement(By.id(YoyakuConstans.TOP_EMPTY_SEARCH_ID_MOKUTEKI)).click();
		sleep(500);
		// 利用目的選択
		selectRiyouMokuteki(driver);
		sleep(500);
		
		// フットサル選択
		driver.findElement(By.id(YoyakuConstans.RIYOU_MOKUTEKI_FU)).click();
		driver.findElement(By.id(YoyakuConstans.NEXT_PAGE_ID)).click();
		driver.findElement(By.id(YoyakuConstans.NEXT_PAGE_ID)).click();
		jexec.executeScript(getSelectRiyouMokutekiFootsal());
		sleep(500);

		// 申し込み枠区分
		selectMoushikomiKubun(yoyakuRegistInfo.placeKubun, jexec);;
		sleep(500);
		
		// 施設選択
		jexec.executeScript(getSelectShisetsu(yoyakuRegistInfo.placeKubun));
		sleep(500);
		
		// 施設分類
		jexec.executeScript(YoyakuConstans.SHISETSU_BUNRUI_SCRIPT_2);
		sleep(500);
		
		sleep(500);
		// 体育室選択（西区の場合のみ・・・）
		if(EnPlaceKubun.NISHI == yoyakuRegistInfo.placeKubun){
			jexec.executeScript(YoyakuConstans.PLACE_SELECT_SCRIPT_NISHI);
		}else{
			jexec.executeScript(YoyakuConstans.PLACE_SELECT_SCRIPT);
		}
		sleep(500);

		// 翌月の予約の場合は翌月ボタンを押下
		goNextMonth(yoyakuRegistInfo.yoyakuDate, jexec);

		//TODO 7:00になるまで待つ
		
		// 日にち選択 
		jexec.executeScript(getSelectDay(yoyakuRegistInfo.yoyakuDay));
		sleep(500);
		// 時間、コート種類選択
		jexec.executeScript(getSelectTime(yoyakuRegistInfo));
		sleep(500);
		// 次へ
		jexec.executeScript("fcSubmitRestrict(FRM_RSGK306,'KAKUNIN','rsv.bean.RSGK308BusinessInit','RSGK306');");
		sleep(500);
//		// 利用目的
//		jexec.executeScript("fcLineDataBtn( 1 );");
//		sleep(500);
//		// footsal
//		jexec.executeScript("fcLineDataBtn( '0037' );");
//		// コートが全面以外の場合は音の設定をしなくてはいけない
//		if(EnShisetsuShousaiKind.ALL.getCode().equals(yoyakuRegistInfo.enShisetsuShousaiKind.getCode()) == false){
//			jexec.executeScript("fcSubmitRestrict(FRM_RSGK309,'OTOCHOSEI','rsv.bean.RSGK310BusinessInit','RSGK309');");
//			sleep(500);
//			jexec.executeScript("fcLineDataBtn( '04' );");
//			sleep(500);
//			jexec.executeScript("fcSubmitRestrict(FRM_RSGK310,'KAKUNIN','rsv.bean.RSGK308BusinessFromRSGK310','RSGK310');");
//		}else{
//			// 次へ
//			jexec.executeScript("fcSubmitRestrict(FRM_RSGK309,'KAKUNIN','rsv.bean.RSGK308BusinessFromRSGK309','RSGK309');");
//		}
//		sleep(500);
		// 次へ
		jexec.executeScript("fcSubmitRestrict(FRM_RSGK308,'RYOKIN','rsv.bean.RSGK312BusinessInit','RSGK308');");
		sleep(500);
		// 予約ボタン押下
		jexec.executeScript("fcSubmitRestrict(FRM_RSGK312,'YOYAKU','rsv.bean.RSGK312BusinnessShinkiYoyaku','RSGK312');");

		// メニューへボタン押下
		jexec.executeScript("toMenu(document.forms[0]);");
	}
	
	/**
	 * 利用目的をスポーツに選択
	 * 
	 * @param driver
	 */
	private void selectRiyouMokuteki(WebDriver driver) {
		  WebElement elment =   driver.findElement(By.name("SLT_RIYOUMOKUTEKI"));
		  Select sel = new Select(elment);
		  sel.selectByValue("2");
	}
	
	/**
	 * 利用目的でフットサルを選択
	 * 
	 * @param enPlaceKubun
	 * @return
	 */
	private String getSelectRiyouMokutekiFootsal(){
		StringBuilder sb = new StringBuilder();
		sb.append("fcSubmit_Yoyaku( FRM_RSGK301, 'LINK_CLICK', 'rsv.bean.RSGK315BusinessInit', 'RSGK301', true, '");
		sb.append(YoyakuConstans.RIYOU_MOKUTEKI_FOOTSAL);
		sb.append("');");
		return sb.toString();
	}

	/**
	 * 申し込み区分選択
	 * 
	 * @param enPlaceKubun
	 * @param jexec
	 */
	private void selectMoushikomiKubun(EnPlaceKubun enPlaceKubun, JavascriptExecutor jexec) {
		switch(enPlaceKubun) {
			case HIRANUMA:
				jexec.executeScript(YoyakuConstans.MOUSHIKOMI_KUBUN_SCRIPT_2_HIRA);
				break;
			case SHIMIZUGAOKA:
				jexec.executeScript(YoyakuConstans.MOUSHIKOMI_KUBUN_SCRIPT_2_SHIMI);
				break;
			default:
				jexec.executeScript(YoyakuConstans.MOUSHIKOMI_KUBUN_SCRIPT_2);
				break;
		}
	}
	
	/**
	 * 室場選択
	 *
	 * @param jexec
	 * @param driver
	 * @param value
	 */
	private void selectShitsuJyo(JavascriptExecutor jexec, WebDriver driver, YoyakuDateTimeData yoyakuDateTimeData){
		 Select element = new Select(driver.findElement(By.name(YoyakuConstans.PLACE_SELECT_PULLDOWN_NAME)));
		 element.selectByValue(yoyakuDateTimeData.placeKubun.getCode());

		 jexec.executeScript("fcSubmit(FRM_RSGK404, 'ONCHANGE', 'rsv.bean.RSGK404BusinessOnChange', 'RSGK404');");

		 selectShitsujo(jexec, yoyakuDateTimeData);;
	}
	private void selectShitsujo(JavascriptExecutor jexec, YoyakuDateTimeData yoyakuDateTimeData){
		StringBuilder sb = new StringBuilder();
		sb.append("fcSubmitBtn(");
		sb.append("'").append(yoyakuDateTimeData.getShitsujo()).append("'").append(",");
		sb.append("'第一体育室（全面）'").append(");");

		jexec.executeScript(sb.toString());
	}

	/**
	 * 時間を選択する
	 *
	 * @param jexec
	 * @param yoyakuDateTimeData
	 */
	private void selectTime(JavascriptExecutor jexec, YoyakuDateTimeData yoyakuDateTimeData){
		 StringBuilder sb = new StringBuilder();
		 sb.append("fcClickSubmit(");
		 sb.append("FRM_RSGK407").append(",");
		 sb.append("'CLICK'").append(",");
		 sb.append("'rsv.bean.RSGK407BusinessClick'").append(",");
		 sb.append("'RSGK407'").append(",");
		 sb.append("'").append(yoyakuDateTimeData.getShitsujo()).append("'").append(",");
		 sb.append("'").append(yoyakuDateTimeData.yoyakuDate).append("'").append(",");
		 sb.append("'").append(yoyakuDateTimeData.yoyakuTime).append("'").append(",");
		 sb.append("'0'").append(",");
		 sb.append("'1'").append(",");
		 sb.append("''").append(");");

		 jexec.executeScript(sb.toString());
	 }

	/**
	 * 利用目的ボタン押下
	 *
	 * @param jexec
	 * @param yoyakuDateTimeData
	 */
	private void clickRiyouMokuteki(JavascriptExecutor jexec, YoyakuDateTimeData yoyakuDateTimeData){
		StringBuilder sb = new StringBuilder();
		sb.append("fcSubmitRiyoMokutekiBtn(");
		sb.append("'1'").append(",");
		sb.append("'").append(yoyakuDateTimeData.yoyakuDate).append("'").append(",");
		sb.append("'").append(yoyakuDateTimeData.getShitsujo()).append("'").append(");");

		jexec.executeScript(sb.toString());
	}

	/**
	 * 利用目的を選択
	 *
	 * @param jexec
	 * @param yoyakuDateTimeData
	 * @return
	 */
	private void selectRiyouMokuteki(JavascriptExecutor jexec, YoyakuDateTimeData yoyakuDateTimeData){
		 StringBuilder sb = new StringBuilder();
		 sb.append("fcSubmitRiyoMokutekiClick(");
		 sb.append("'1'").append(",");
		 sb.append("'").append(yoyakuDateTimeData.yoyakuDate).append("'").append(",");
		 sb.append("'").append(yoyakuDateTimeData.getShitsujo()).append("'").append(",");
		 sb.append("'").append(YoyakuConstans.RIYOU_MOKUTEKI_FOOTSAL).append("'").append(");");

		 jexec.executeScript(sb.toString());
	 }

	private static String getSelectShisetsu(EnPlaceKubun enPlaceKubun){
		StringBuilder sb = new StringBuilder();
		sb.append("fcSubmit_Yoyaku( FRM_RSGK303, 'LINK_CLICK', 'rsv.bean.RSGK304BusinessInit', 'RSGK303', true, '");
		sb.append(enPlaceKubun.getCode());
		sb.append("');");
		return sb.toString();
	}

	private static String getSelectDay(String day){
		StringBuilder sb = new StringBuilder();
		sb.append("fcSubmit_Yoyaku( FRM_RSGK305, 'SEARCH_POINT_RSGK306', 'rsv.bean.RSGK306BusinessInit', 'RSGK305', true, '");
		sb.append(day);
		sb.append("');");
		return sb.toString();
	}

	private static String getSelectTime(YoyakuRegistInfo yoyakuRegistInfo){
		StringBuilder sb = new StringBuilder();
		sb.append("fcRSGK306ClickSubmit(FRM_RSGK306,'SEARCH_CHANGE','rsv.bean.RSGK306BusinessClick','RSGK306','");
		sb.append(yoyakuRegistInfo.enShisetsuShousai.getCode()).append("','");
		sb.append(yoyakuRegistInfo.getShisetsuShousai()).append("','");
		sb.append(yoyakuRegistInfo.yoyakuDate).append("','");
		sb.append(yoyakuRegistInfo.enTimeKubun.getTime()).append("','");
		sb.append("0','','1','");
		sb.append(yoyakuRegistInfo.getTimeKubunCode());
		sb.append("');");
		return sb.toString();
	}

	private void goNextMonth(String yoyakuDate, JavascriptExecutor jexec) {
		SimpleDateFormat fo = new SimpleDateFormat("yyyyMM");

		String nowYearMonth = fo.format(new Date());

		if(yoyakuDate.substring(0, 6).equals(nowYearMonth) == false) {
			jexec.executeScript("fcSubmit_Yoyaku( FRM_RSGK305, 'SEARCH_NEXT1M', 'rsv.bean.RSGK305BusinessMovePage', 'RSGK305', true, '');");
		}
	}

	private void sleep(int microtime){
		try{
			Thread.sleep(microtime);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
