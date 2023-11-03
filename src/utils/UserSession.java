package utils;

public class UserSession 
{
	
	private static String userName;
	
	public static void setUser(String userName)
	{
		UserSession.userName = userName;
	}
	
	public static String getUser()
	{
		return userName;
	}
	
	public static void clearSession()
	{
		userName = null;
	}
	
	public static boolean isLoggedIn()
	{
		return userName != null;
	}
	
	
}
