package utils;

public class UserSession 
{
	
	private static String userName; //usuario
	private static String userType; //tipo de usuario, seja loja ou cliente
	
	public static void setUser(String userName, String userType)
	{
		UserSession.userName = userName;
		UserSession.userType = userType;
	}
	
	public static String getUserName()
	{
		return userName;
	}
	
	public static String getUserType()
	{
		return userType;
	}
	
	public static void clearSession()
	{
		userName = null;
		userType = null;
	}
	
	public static boolean isLoggedIn()
	{
		return userName != null;
	}
	
	
}
