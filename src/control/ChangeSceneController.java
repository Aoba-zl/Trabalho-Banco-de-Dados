package control;

import javafx.scene.layout.Pane;
import utils.SceneName;
import utils.UserSession;
import view.WinAccountMenuConstructor;
import view.WinEditProductConstructor;
import view.WinHomePageConstructor;
import view.WinLoginConstructor;
import view.WinRegProductConstructor;
import view.WinStoreConstructor;

public class ChangeSceneController
{
	private static WinLoginConstructor winLogin = new WinLoginConstructor();
	private static WinHomePageConstructor winHomePage = new WinHomePageConstructor();
	private static WinStoreConstructor winStore = new WinStoreConstructor();
	private static WinRegProductConstructor winRegProduct = new WinRegProductConstructor();
	private static WinEditProductConstructor winEditProduct = new WinEditProductConstructor();
	private static WinAccountMenuConstructor winAccountMenu = new WinAccountMenuConstructor();
	
	public static void changeScene(SceneName sceneName, Pane pWin)
	{
		pWin.getChildren().clear();
		if(UserSession.isLoggedIn())
		{
			switch(sceneName)
			{
			case LOGIN:
				UserSession.clearSession();
				winLogin.addElements(pWin);
				break;
			case HOME_PAGE:
				winHomePage.addElements(pWin);
				break;
			case CONSULT_PRODUCT:
				System.out.println("Product Client");
				break;
			case CART:
				System.out.println("carrinho");
				break;
			case STORE:
				winStore.addElements(pWin);
				break;
			case REG_PRODUCT:
				winRegProduct.addElements(pWin);
				break;
			case ALTER_PRODUCT:
				System.out.println("Product Store");
				break;
			case EDIT_PRODUCT:
				winEditProduct.addElements(pWin);
				break;
			case ACCOUNT_MENU:
				winAccountMenu.addElements(pWin);;
				break;
			}
		}
		else
		{
			UserSession.clearSession();
			winLogin.addElements(pWin);
		}
	}
}
