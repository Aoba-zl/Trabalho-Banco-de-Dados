package utils;

public class UserSession 
{
	
	private static String userName = "matheus"; //usuario ps:deixarei como default apenas para teste.
	private static String userType = "store"; //tipo de usuario, seja loja ou cliente | ps:deixarei como default apenas para teste.
	
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
