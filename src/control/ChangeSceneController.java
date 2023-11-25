package control;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.layout.Pane;
import utils.SceneName;
import utils.UserSession;
import view.*;

import java.util.HashMap;

public class ChangeSceneController
{
	private static HashMap<SceneName, GenericWindownInterface> windowns = new HashMap<>();
	
	private IntegerProperty ipCod = new SimpleIntegerProperty(0);
	
	/**
	 * Controlador responsável pela troca de tela dentro da aplicação.
	 * @param sceneName O nome do tipo de tela.
	 * @param pWin
	 */
	public void changeScene(SceneName sceneName, Pane pWin)
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
		WinPurchaseHistoryConstructor winPurchaseHistory;

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
				case REG_CLIENT_ADDRESS:
					if (!windowns.containsKey(sceneName))
					{
						System.out.println("login novo");
						winRegClientAddress = new WinRegClientAddressConstructor();
						windowns.put(sceneName, winRegClientAddress);
					}
					else
						winRegClientAddress= (WinRegClientAddressConstructor) windowns.get(sceneName);
					winRegClientAddress.addElements(pWin);
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
					winConsultProduct.setCodValue(ipCod);
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
					winCart.setCodValue(ipCod);
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
					winPurchaseDetails.setCodValue(ipCod);
					winPurchaseDetails.addElements(pWin);
					break;
				case PURCHASE_HISTORY:
					if (!windowns.containsKey(sceneName))
					{
						winPurchaseHistory = new WinPurchaseHistoryConstructor();
						windowns.put(sceneName, winPurchaseHistory);
					}
					else
						winPurchaseHistory  = (WinPurchaseHistoryConstructor) windowns.get(sceneName);
					winPurchaseHistory.addElements(pWin);
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
					winAlterProduct.setCodValue(ipCod);
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
					winEditProduct.setCodValue(ipCod);
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
	
	public void setCodValue(IntegerProperty cod) { ipCod.bindBidirectional(cod); }
}