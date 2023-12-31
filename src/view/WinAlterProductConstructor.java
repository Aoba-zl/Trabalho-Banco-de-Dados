package view;

import control.ChangeSceneController;
import control.ProductController;
import control.RegisterUserController;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import model.Product;
import model.Store;
import utils.SceneName;

/**
 * Classe responsável por construir e adicionar os elementos a interface gráfica de
 * alteração de produto.
 * Implementa a interface GerericAccountMenuWinInterface.
 */
public class WinAlterProductConstructor implements GenericWindownInterface
{
	Pane pWin;
	private ProductController pCon = new ProductController();
	private RegisterUserController cCon = new RegisterUserController();
	private Product product = new Product();
	private Store store = new Store();
	private FlowPane fpCategory = new FlowPane();
	
	private IntegerProperty ipCod = new SimpleIntegerProperty(0);
	
	private ChangeSceneController changeSceneController = new ChangeSceneController();
	
    /**
     * Construtor da classe.
     *
     * @param pane O painel principal da interface gráfica.
     */
	public void addElements(Pane pane) 	
	{
		this.pWin = pane;
		
		// ----- Creating General Bord ----- //
		Pane paneConsult = new Pane();
		paneConsult.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
		paneConsult.setPrefHeight(390);
		paneConsult.setPrefWidth(630);
		paneConsult.relocate(5, 5);
		// ----- Carregando ----- //

		product.setCod(ipCod.get());
		product=pCon.consulta(product);
		store.setLogin(product.getLogin());
		store=cCon.consultStore(store);
		// ----- Creating Info Bord ----- //
		
		Pane paneInfo = new Pane();
		paneInfo.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
		paneInfo.setPrefHeight(380);
		paneInfo.setPrefWidth(280);
		paneInfo.relocate(5, 5);

		
		// ----- Creating paneInfo ----- //

			// ----- Creating Labels ----- //
		
			Label lbNameProd = new Label(product.getName());
			lbNameProd.setPrefWidth(277);
			lbNameProd.setAlignment(Pos.CENTER);
			lbNameProd.setStyle("-fx-font-size: 16px;");
			lbNameProd.relocate(5, 20);
			Label lbPrice = new Label(product.getPrice()+" $");
			lbPrice.setPrefWidth(277);
			lbPrice.setAlignment(Pos.CENTER);
			lbPrice.setStyle("-fx-font-size: 14px;-fx-font-weight: bold");
			lbPrice.relocate(5, 39);
			Label lbTotal = new Label("Compras:");
			setAlignment(lbTotal);
			Label lbShipping = new Label("Frete:");
			setAlignment(lbShipping);
			Label lbStock = new Label("Estoque:");
			setAlignment(lbStock);
			Label lbCategory = new Label("Categoria:");
			setAlignment(lbCategory);
			fpCategory.getChildren().add(lbCategory);
			lbCategory.setAlignment(Pos.BASELINE_LEFT);
			lbCategory.setPrefWidth(71);
			
			// ----- Creating TextField ----- //
			
			TextField txTotal = new TextField(pCon.quant(product)+"");
			setAlignment(txTotal);
			txTotal.setDisable(true);
			TextField txShipping = new TextField(product.getShipping()+"");
			setAlignment(txShipping);
			txShipping.setDisable(true);
			TextField txStock = new TextField(product.getTotalStock()+"");
			setAlignment(txStock);
			txStock.setDisable(true);

			
			
			// ----- Add Info into Screen ----- //
			VBox vbInfoLb = new VBox(40,lbTotal,lbShipping,lbStock);
			VBox vbInfoTx = new VBox(33,txTotal, txShipping, txStock);
			HBox hbInfo = new HBox(10,vbInfoLb,vbInfoTx);
			hbInfo.setPrefHeight(170);
			hbInfo.setPrefWidth(250);
			hbInfo.relocate(10, 80);
			
			// ----- Creating Description ----- //
			
			fpCategory.relocate(20, 260);
			fpCategory.setPrefHeight(80);
			fpCategory.setPrefWidth(250);
			
			
		// ----- Creating paneDescription ----- //
			
			// ----- Creating GoBack Button ----- //
			
			Button btnReturn = new Button();
			setBtnBackImage(btnReturn);
			setOverButtonStyle(btnReturn);
			btnReturn.relocate(4, 4);
			
			// ----- Creating Buttons ----- //

			Button btnDelete = new Button("Excluir");
			btnDelete.relocate(482, 360);
			btnDelete.setPrefWidth(140);
			Button btnEdit = new Button("Editar");
			btnEdit.relocate(291, 360);
			btnEdit.setPrefWidth(140);
			
			// ----- Creating Label ----- //
			
			Label lbDescription = new Label("Descrição");
			lbDescription.relocate(282.9, 20);
			lbDescription.setPrefWidth(345);
			lbDescription.setAlignment(Pos.CENTER);
			lbDescription.setStyle("-fx-font-size: 18px;-fx-font-weight: bold");
			// ----- Creating TextArea ----- //
			
			TextArea txDescription = new TextArea(product.getDescription());
			txDescription.relocate(290,50);
			txDescription.setPrefHeight(300);
			txDescription.setPrefWidth(332); 
			txDescription.setStyle("-fx-border-width: 2; -fx-border-radius: 10; -fx-border-color: black;");
			txDescription.setWrapText(true);
			txDescription.setDisable(true);
			
		// ----- Add to pane ----- //
		addCategory(product.getCategory());

			
		paneInfo.getChildren().addAll(hbInfo,lbNameProd,lbPrice,btnReturn,fpCategory);
		paneConsult.getChildren().addAll(paneInfo,btnEdit,btnDelete,lbDescription,txDescription);
		
		//ChangeScene
		btnReturn.setOnAction(e -> toStore());
		btnEdit.setOnAction(e -> toEditProduct());
		btnDelete.setOnAction(e -> delete(product));
		
		pane.getChildren().addAll(paneConsult);
	}
	
	private void addCategory (String categoria) {
		Label lblCategory = new Label("("+categoria+") ");
		fpCategory.getChildren().add(lblCategory);
	}
	
	private void setAlignment(Label label) {
		label.setPrefWidth(120);
		label.setAlignment(Pos.CENTER_RIGHT);
		label.setStyle("-fx-font-size: 13px;");
	}
	private void setAlignment(TextField tf) {
		tf.setPrefWidth(206);
		tf.setStyle("-fx-font-size: 12px;");
	}
	
	private void toStore()
	{
		fpCategory.getChildren().clear();
		changeSceneController.changeScene(SceneName.STORE, this.pWin);
	}
	
	private void delete (Product product) {
		pCon.delete(product);
		fpCategory.getChildren().clear();
		changeSceneController.changeScene(SceneName.STORE, this.pWin);
	}
	
	private void toEditProduct()
	{
		fpCategory.getChildren().clear();
		changeSceneController.setCodValue(ipCod);
		changeSceneController.changeScene(SceneName.EDIT_PRODUCT, this.pWin);
	}
	
	private void setBtnBackImage(Button btnBack) {
        Image imgGoBackBtn = new Image(getClass().getResource("image/goBack.png").toString());
        ImageView ivGoBackBtn = new ImageView(imgGoBackBtn);
        int widthHeight = 25;
        ivGoBackBtn.setFitHeight(widthHeight);
        ivGoBackBtn.setFitWidth(widthHeight);

        btnBack.setGraphic(ivGoBackBtn);
    }

    private void setBtnStyle(Button button, String style) {
        button.setStyle(style);
    }


    private void setOverButtonStyle(Button button) 
    {
    	String styleEnter = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(94,94,94,0.26); -fx-background-radius: 1000px";
    	String styleExit = "-fx-border-color: rgba(255,255,255,0); -fx-cursor: hand; " +
                "-fx-background-color: rgba(255,255,255,0);";
        button.setOnMouseEntered(e -> setBtnStyle(button, styleEnter));
        button.setOnMouseExited(e -> setBtnStyle(button, styleExit));
        button.setStyle(styleExit);
    }
    
    /**
     * Obtém o codigo de produto de outra tela.
     * @param cod O codigo do produto.
     */
    public void setCodValue(IntegerProperty cod) { ipCod.bindBidirectional(cod); }
}
