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
		//login
		WinLoginConstructor winLogin;
		WinRegClientInfoConstructor winRegClientInfo;
		WinRegClientAddressConstructor winRegClientAddress;
		WinRegStoreInfoConstructor winRegStoreInfo;
		WinRegStoreAddressConstructor winRegStoreAddress;
		
		//login client
		WinHomePageConstructor winHomePage;
		WinAccountMenuConstructor winAccountMenu;
		WinConsultProductConstructor winConsultProduct;
		WinShoppingCartConstructor winCart;
		WinPurchaseDetailsConstruct winPurchaseDetails;
		
		//login store
		WinStoreConstructor winStore;
		WinRegProductConstructor winRegProduct;
		WinAlterProductConstructor winAlterProduct;
		WinEditProductConstructor winEditProduct;

		pWin.getChildren().clear();

		if(UserSession.isLoggedIn())
		{
			switch(sceneName)
			{
				//Login
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
				case REG_CLIENT_INFO:
					if (!windowns.containsKey(sceneName))
					{
						System.out.println("login novo");
						winRegClientInfo = new WinRegClientInfoConstructor();
						windowns.put(sceneName, winRegClientInfo);
					}
					else
						winRegClientInfo = (WinRegClientInfoConstructor) windowns.get(sceneName);
					winRegClientInfo.addElements(pWin);
					break;
				case REG_STORE_INFO:
					if (!windowns.containsKey(sceneName))
					{
						System.out.println("login novo");
						winRegStoreInfo = new WinRegStoreInfoConstructor();
						windowns.put(sceneName, winRegStoreInfo);
					}
					else
						winRegStoreInfo = (WinRegStoreInfoConstructor) windowns.get(sceneName);
					winRegStoreInfo.addElements(pWin);
					break;
				case REG_STORE_ADDRESS:
					if (!windowns.containsKey(sceneName))
					{
						System.out.println("login novo");
						winRegStoreAddress = new WinRegStoreAddressConstructor();
						windowns.put(sceneName, winRegStoreAddress);
					}
					else
						winRegStoreAddress= (WinRegStoreAddressConstructor) windowns.get(sceneName);
					winRegStoreAddress.addElements(pWin);
					break;
					
				//Client
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
				case ACCOUNT_MENU:
					if (!windowns.containsKey(sceneName))
					{
						winAccountMenu = new WinAccountMenuConstructor();
						windowns.put(sceneName, winAccountMenu);
					}
					else
						winAccountMenu = (WinAccountMenuConstructor) windowns.get(sceneName);
					winAccountMenu.addElements(pWin);
					break;
				case CONSULT_PRODUCT:
					if (!windowns.containsKey(sceneName))
					{
						winConsultProduct= new WinConsultProductConstructor();
						windowns.put(sceneName, winConsultProduct);
					}
					else
						winConsultProduct = (WinConsultProductConstructor) windowns.get(sceneName);
					winConsultProduct.addElements(pWin);
					break;
				case CART:
					if (!windowns.containsKey(sceneName))
					{
						winCart = new WinShoppingCartConstructor();
						windowns.put(sceneName, winCart);
					}
					else
						winCart = (WinShoppingCartConstructor) windowns.get(sceneName);
					winCart.addElements(pWin);
					break;
				case PURCHASE_DETAILS:
					if (!windowns.containsKey(sceneName))
					{
						winPurchaseDetails = new WinPurchaseDetailsConstruct();
						windowns.put(sceneName, winPurchaseDetails);
					}
					else
						winPurchaseDetails = (WinPurchaseDetailsConstruct) windowns.get(sceneName);
					winPurchaseDetails.addElements(pWin);
					break;
					
				//Store
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
					if (!windowns.containsKey(sceneName))
					{
						winAlterProduct = new WinAlterProductConstructor();
						windowns.put(sceneName, winAlterProduct);
					}
					else
						winAlterProduct = (WinAlterProductConstructor) windowns.get(sceneName);
					winAlterProduct.addElements(pWin);
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
