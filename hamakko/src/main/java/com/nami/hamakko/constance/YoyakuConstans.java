package com.nami.hamakko.constance;

public final class YoyakuConstans {
	/** URL */
	public static final String YOYAKU_URL = "https://yoyaku.city.yokohama.lg.jp/ys/";

	/** TOPページのログインIDテキストボックスのID */
	public static final String TOP_LOGIN_TEXT_ID = "ID";

	/** TOPページのパスワードテキストボックスのID */
	public static final String TOP_PASSWORD_TEXT_ID = "PWD";

	/** TOPページのログインボタンのID */
	public static final String TOP_LOGIN_BUTTON_ID = "navi_login_r";

	/** TOPページの抽選申し込みボタンのID */
	public static final String TOP_CHUSEN_BUTTON_ID = "RSGK001_01";

	/** TOPページの空状況照会・予約（施設から選択）ボタンのID */
	public static final String TOP_EMPTY_SEARCH_ID_SHISETSU = "RSGK001_05";
	/** TOPページの空状況照会・予約（利用目的から選択）ボタンのID */
	public static final String TOP_EMPTY_SEARCH_ID_MOKUTEKI = "RSGK001_06";

	/** 施設分類選択ページのスポーツボタン押下時のスクリプト */
	public static final String SHISETSU_BUNRUI_SCRIPT = "fcSubmitBtn('01','','スポーツ');";

	/** 施設分類ページのスポーツボタンボタン押下時のスクリプト(TOP：室内選択) */
	public static final String SHISETSU_BUNRUI_SCRIPT_2 = "fcSubmit_Yoyaku(FRM_RSGK302, 'LINK_CLICK', 'rsv.bean.RSGK315BusinessInit', 'RSGK302', true, '01');";

	/** 申込枠区分選択ページのスポーツセンターボタン押下時のスクリプト */
	public static final String MOUSHIKOMI_KUBUN_SCRIPT = "fcSubmitBtn('01','01','スポーツセンター');";
	/** 申込枠区分選択ページの平沼体育館押下時のスクリプト */
	public static final String MOUSHIKOMI_KUBUN_SCRIPT_HIRANUMA = "fcSubmitBtn('01','02','平沼記念体育館');";

	/** 申込枠区分選択ページのスポーツセンターボタン押下時のスクリプト(TOP:室内選択) */
	public static final String MOUSHIKOMI_KUBUN_SCRIPT_2 = "fcSubmit_Yoyaku( FRM_RSGK315, 'CLICK', 'rsv.bean.RSGK303BusinessInit', 'RSGK315', true, '01');";
	public static final String MOUSHIKOMI_KUBUN_SCRIPT_2_HIRA = "fcSubmit_Yoyaku( FRM_RSGK315, 'CLICK', 'rsv.bean.RSGK303BusinessInit', 'RSGK315', true, '02');";
	public static final String MOUSHIKOMI_KUBUN_SCRIPT_2_SHIMI = "fcSubmit_Yoyaku( FRM_RSGK315, 'CLICK', 'rsv.bean.RSGK303BusinessInit', 'RSGK315', true, '03')";

	/** 室場選択の施設選択プルダウンのNAME */
	public static final String PLACE_SELECT_PULLDOWN_NAME = "LST_SHISETSU";

	/** 室場選択の施設選択プルダウンのスクリプト */
	public static final String PLACE_SELECT_PULLDOWN_SCRIPT = "fcSubmit(FRM_RSGK404, 'ONCHANGE', 'rsv.bean.RSGK404BusinessOnChange', 'RSGK404');";

	/** 室場選択ページの体育室ボタン押下 */
	public static final String PLACE_SELECT_SCRIPT = "fcSubmit_Yoyaku( FRM_RSGK304, 'LINK_CLICK', 'rsv.bean.RSGK305BusinessInit', 'RSGK304', true, '03');";
	public static final String PLACE_SELECT_SCRIPT_NISHI = "fcSubmit_Yoyaku( FRM_RSGK304, 'LINK_CLICK', 'rsv.bean.RSGK305BusinessInit', 'RSGK304', true, '07');";

	/** 利用目的（ふ）選択 */
	public static final String RIYOU_MOKUTEKI_FU = "idbtn_06";
	/** 利用目的（フットサル） */
	public static final String RIYOU_MOKUTEKI_FOOTSAL = "0037";

	/** 次のページボタンID */
	public static final String NEXT_PAGE_ID = "idbtn_next_page";
	
	/** ログアウトスクリプト */
	public static final String LOGOUT_SCRIPT = "toLogOut(document.forms[0]);";

}
