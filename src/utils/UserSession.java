package utils;

public class UserSession 
{
	
	private static String userName; //usuario
	private static String userType; //tipo de usuario, seja loja ou cliente
	
	/**
	 * Faz o set do usuário e o tipo de usuário ao logar no sistema.
	 * @param userName Nome de usuário.
	 * @param userType Tipo de usuário.
	 */
	public static void setUser(String userName, String userType)
	{
		UserSession.userName = userName;
		UserSession.userType = userType;
	}
	
	/**
	 * Busca o nome do usuário.
	 * @return O nome do usuário.
	 */
	public static String getUserName()
	{
		return userName;
	}
	
	/**
	 * Busca o tipo de usuário.
	 * @return O tipo de usuário.
	 */
	public static String getUserType()
	{
		return userType;
	}
	
	/**
	 * Redefine para null o nome e o tipo de usuário para caso tenha deslogado.
	 */
	public static void clearSession()
	{
		userName = null;
		userType = null;
	}
	
	/**
	 * Faz a verificação de login para saber se está realmente logado no aplicativo.
	 * @return True para caso o usuário não seja null, false caso o contrário.
	 */
	public static boolean isLoggedIn()
	{
		return userName != null;
	}
	
	
}
