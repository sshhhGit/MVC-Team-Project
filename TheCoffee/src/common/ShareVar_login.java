package common;

public class ShareVar_login {
	
	public static String hostID = "sshhh0497";
	public static String hostPW = "tmdgh288";
	public static String authEamilCode = "12345";
	
	private static ShareVar_login shareVar = new ShareVar_login();
	private ShareVar_login() {}
	public static ShareVar_login getInstance() {
		return shareVar;
	}
	public static String getAuthEamilCode() {
		return authEamilCode;
	}
}
