package com.pcfix_client;

public class API {
	public static final String BASE_URL = "http://192.168.0.110:8080/pcfix/";
	public static final String SIGNUP = BASE_URL + "signup";
	public static final String LOGIN = BASE_URL + "login";
	public static final String ADDORDER = BASE_URL + "addorder";
	public static final String LISTORDER = BASE_URL + "listorder";
	public static final String LISTMYORDER = BASE_URL + "listmyorder";
	public static final String MYORDERSERVER = BASE_URL + "myorderserver";
	public static final String VIEWORDER = BASE_URL + "vieworder";
	public static final String LIST_APPLYER = BASE_URL + "list_applyer";
	public static final String ADDPRICE = BASE_URL + "addprice";
	public static final String SELECTAPPLYER = BASE_URL + "selectapplyer";
	public static final String [] STATES = new String[]{"竞价中...", "处理中...", "完成", "过期"};
	public static final String [] STATES_SERVER = new String[]{"申请中...", "处理中...", "完成", "过期"};
	public static final String [] PROBLEMS = new String[]{"cpu","内存","显卡","硬盘","显示器","键盘","鼠标"};
	
}
