package control;

import javafx.scene.layout.Pane;
import utils.SceneName;
import utils.UserSession;
import view.*;

import java.util.HashMap;

public class ChangeSceneController
{
	private static HashMap<SceneName, GenericWindownInterface> windowns = new HashMap<>();

	public static void changeScene(SceneName sceneName, Pane pWin)
	{
		WinLoginConstructor winLogin;
		WinHomePageConstructor winHomePage;
		WinStoreConstructor winStore;
		WinRegProductConstructor winRegProduct;
		WinEditProductConstructor winEditProduct;
		WinAccountMenuConstructor winAccountMenu;

		pWin.getChildren().clear();

		if(UserSession.isLoggedIn())
		{
			switch(sceneName)
			{
				case LOGIN:
					if (!windowns.containsKey(sceneName))
					{
						System.out.println("login novo");
						winLogin = new WinLoginConstructor();
						windowns.put(sceneName, winLogin);
					}
					else
						winLogin = (WinLoginConstructor) windowns.get(sceneName);
					UserSession.clearSession();
					winLogin.addElements(pWin);
					break;
				case HOME_PAGE:
					if (!windowns.containsKey(sceneName))
					{
						System.out.println("Home nova");
						winHomePage = new WinHomePageConstructor();
						windowns.put(sceneName, winHomePage);
					}
					else
						winHomePage = (WinHomePageConstructor) windowns.get(sceneName);
					winHomePage.addElements(pWin);
					break;
				case CONSULT_PRODUCT:
					System.out.println("Product Client");
					break;
				case CART:
					System.out.println("carrinho");
					break;
				case STORE:
					if (!windowns.containsKey(sceneName))
					{
						winStore = new WinStoreConstructor();
						windowns.put(sceneName, winStore);
					}
					else
						winStore = (WinStoreConstructor) windowns.get(sceneName);
					winStore.addElements(pWin);
					break;
				case REG_PRODUCT:
					if (!windowns.containsKey(sceneName))
					{
						winRegProduct = new WinRegProductConstructor();
						windowns.put(sceneName, winRegProduct);
					}
					else
						winRegProduct = (WinRegProductConstructor) windowns.get(sceneName);
					winRegProduct.addElements(pWin);
					break;
				case ALTER_PRODUCT:
					System.out.println("Product Store");
					break;
				case EDIT_PRODUCT:
					if (!windowns.containsKey(sceneName))
					{
						winEditProduct = new WinEditProductConstructor();
						windowns.put(sceneName, winEditProduct);
					}
					else
						winEditProduct = (WinEditProductConstructor) windowns.get(sceneName);
					winEditProduct.addElements(pWin);
					break;
				case ACCOUNT_MENU:
					if (!windowns.containsKey(sceneName))
					{
						winAccountMenu = new WinAccountMenuConstructor();
						windowns.put(sceneName, winAccountMenu);
					}
					else
						winAccountMenu = (WinAccountMenuConstructor) windowns.get(sceneName);
					winAccountMenu.addElements(pWin);;
					break;
			}
		}
		else
		{
			if (!windowns.containsKey(sceneName))
				winLogin = new WinLoginConstructor();
			else
				winLogin = (WinLoginConstructor) windowns.get(sceneName);
			UserSession.clearSession();
			winLogin.addElements(pWin);
		}
	}
}
