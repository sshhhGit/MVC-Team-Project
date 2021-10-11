package co.kr.TheCoffee.common;

public class ShareVar_login {
	
	public static String hostID = " ";
	public static String hostPW = " ";
	public static String authEamilCode = "123456";
	
	private static ShareVar_login shareVar = new ShareVar_login();
	private ShareVar_login() {}
	public static ShareVar_login getInstance() {
		return shareVar;
	}
	public static String getAuthEamilCode() {
		return authEamilCode;
	}
}
