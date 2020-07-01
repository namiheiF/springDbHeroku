package com.nami.hamakko.constance;

public enum EnShisetsuShousai {
	/** 鶴見スポーツセンター */
	TSURUMI("1300", "1304", "1305", "130"),
	/** 神奈川スポーツセンター */
	KANAGAWA("1800", "1804", "1805", "180"),
	/** 西スポーツセンター */
	NISHI("2201", "2201", "2201", "220"),
	/** 中スポーツセンター */
	NAKA("2700", "2704", "2705", "270"),
	/** 南スポーツセンター */
	MINAMI("3300", "3304", "3305", "330"),
	/** 港南スポーツセンター */
	KOUNAN("3700", "3705", "3706", "370"),
	/** 保土ケ谷スポーツセンター */
	HODOGAYA("4000", "4006", "4007", "400"),
	/** 旭スポーツセンター */
	ASAHI("4400", "4404", "4405", "440"),
	/** 磯子スポーツセンター */
	ISOGO("4800", "4805", "4806", "480"),
	/** 金沢スポーツセンター */
	KANAZAWA("5400", "5404", "5405", "540"),
	/** 港北スポーツセンター */
	KOUHOKU("5800", "5807", "5808", "580"),
	/** 緑スポーツセンター */
	MIDORI("6300", "6304", "6305", "630"),
	/** 青葉スポーツセンター */
	AOBA("6700", "6705", "6706", "670"),
	/** 都筑スポーツセンター */
	TUDUKI("7100", "7104", "7105", "710"),
	/** 戸塚スポーツセンター */
	TOTSUKA("7600", "7605", "7606", "760"),
	/** 栄スポーツセンター */
	SAKAE("8000", "8003", "8004", "800"),
	/** 泉スポーツセンター */
	IZUMI("8300", "8305", "8306", "830"),
	/** 瀬谷スポーツセンター */
	SEYA("8700", "8704", "8705", "870");

	private String code;
	private String allCort;
	private String aCort;
	private String bCort;

	private EnShisetsuShousai(String allCort, String aCort, String bCort, String code){
		this.allCort = allCort;
		this.aCort = aCort;
		this.bCort = bCort;
		this.code = code;
	}

	public String getAllCort(){
		return allCort;
	}

	public String getACort(){
		return aCort;
	}

	public String getBCort(){
		return bCort;
	}

	public String getCode(){
		return code;
	}

	public EnShisetsuShousai convertByCode(String code){
		if(code == null || code.length() == 0){
			return null;
		}
		for(EnShisetsuShousai enShisetsuShousai : EnShisetsuShousai.values()){
			if(enShisetsuShousai.code.equals(code)){
				return enShisetsuShousai;
			}
		}

		return null;
	}


}
