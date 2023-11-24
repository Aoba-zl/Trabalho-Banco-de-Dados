package view;

import java.sql.SQLException;

import control.ChangeSceneController;
import control.ProductController;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import utils.SceneName;

/**
 * Construtor da tela de edição de produto, com a implementação da interface GenericWindownInterface.
 */
public class WinEditProductConstructor implements GenericWindownInterface
{
	private Pane pWin;
	
	private IntegerProperty ipCod = new SimpleIntegerProperty(0);
	
	private ChangeSceneController changeSceneController = new ChangeSceneController();
	
	ProductController pControll = new ProductController();
	
	/**
     * Adiciona elementos à interface gráfica da janela de conta.
     *
     * @param pane O painel ao qual os elementos serão adicionados.
     */
	public void addElements(Pane pane) //necessário ter a tela de produto.
	{
		this.pWin = pane;
		
		Label lblHomePage = new Label("Editar Produto");
		lblHomePage.setPrefHeight(35);
		lblHomePage.setPrefWidth(640);
		lblHomePage.setStyle("-fx-font-size: 24px; -fx-alignment: center; -fx-font-weight: bold");
		lblHomePage.setLayoutY(25);
		
		Label lblExit = new Label("Sair❌");
		Label lblAccount = new Label("Conta");
		lblExit.setStyle("-fx-font-size: 16px; -fx-cursor: hand;");
		lblAccount.setStyle("-fx-font-size: 16px; -fx-cursor: hand;");

		Button btnReturn = new Button();
		setBtnBackImage(btnReturn);
		setOverButtonStyle(btnReturn);
		
		HBox hbOption = new HBox();
		hbOption.setPrefWidth(640);
		hbOption.setPrefHeight(30);
		hbOption.setPadding(new Insets(0, 15, 0, 0));
		hbOption.setStyle("-fx-alignment: top-right; -fx-spacing: 15px;");
		hbOption.getChildren().addAll(lblExit, lblAccount);
		
		Label lblDesc = new Label("Descrição");
		TextArea taDesc = new TextArea();
		taDesc.setWrapText(true);
		
		HBox hbLblDesc = new HBox();
		hbLblDesc.setMinHeight(50);
		hbLblDesc.setStyle("-fx-alignment: center; -fx-font-size: 18px; -fx-font-weight: bold;");
		hbLblDesc.getChildren().add(lblDesc);
		
		HBox hbTaDesc = new HBox();
		hbTaDesc.setMinHeight(250);
		hbTaDesc.setPadding(new Insets(0, 10, 0, 10));
		hbTaDesc.getChildren().add(taDesc);
		
		VBox vbDescProduct = new VBox();
		vbDescProduct.setPrefWidth(250);
		vbDescProduct.setPrefHeight(320);
		vbDescProduct.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px; -fx-border-style: dashed;");
		vbDescProduct.getChildren().addAll(hbLblDesc, hbTaDesc);
		
		Label lblName = new Label("Nome:  ");
		Label lblPrice = new Label("Preço:  ");
		Label lblInStock = new Label("Em estoque:  ");
		Label lblShipping = new Label("Frete:  ");
		Label lblCategory = new Label("Categoria:  ");
		lblName.setPrefWidth(75);
		lblName.setStyle("-fx-alignment: center-right;");
		lblPrice.setPrefWidth(75);
		lblPrice.setStyle("-fx-alignment: center-right;");
		lblInStock.setPrefWidth(75);
		lblInStock.setStyle("-fx-alignment: center-right;");
		lblShipping.setPrefWidth(75);
		lblShipping.setStyle("-fx-alignment: center-right;");
		lblCategory.setPrefWidth(75);
		lblCategory.setStyle("-fx-alignment: center-right;");
		
		TextField tfName = new TextField();
		TextField tfPrice = new TextField();
		TextField tfInStock = new TextField();
		TextField tfShipping = new TextField();
		TextField tfCategory = new TextField();
		tfName.setPrefWidth(225);
		tfPrice.setPrefWidth(225);
		tfInStock.setPrefWidth(225);
		tfShipping.setPrefWidth(225);
		tfCategory.setPrefWidth(225);
		
		Button btnConf = new Button("Confirmar");
		btnConf.setStyle("-fx-background-color: #C2FFC2; -fx-border-color: #ADFF2F; -fx-border-radius: 10px; -fx-background-radius: 10px;");
		
		Label lblMessage = new Label("");
		lblMessage.setMinHeight(30);
		lblMessage.setMinWidth(120);
		
		HBox hbName = new HBox();
		HBox hbPrice = new HBox();
		HBox hbInStock = new HBox();
		HBox hbShipping = new HBox();
		HBox hbCategory = new HBox();
		HBox hbBtnConfAndMessage = new HBox();
		hbName.getChildren().addAll(lblName, tfName);
		hbPrice.getChildren().addAll(lblPrice, tfPrice);
		hbInStock.getChildren().addAll(lblInStock, tfInStock);
		hbShipping.getChildren().addAll(lblShipping, tfShipping);
		hbCategory.getChildren().addAll(lblCategory, tfCategory);
		hbBtnConfAndMessage.getChildren().addAll(lblMessage, btnConf);
		hbBtnConfAndMessage.setStyle("-fx-alignment: bottom-right; -fx-spacing: 100px;");
		hbBtnConfAndMessage.setPadding(new Insets(0, 45, 0, 0));
		hbBtnConfAndMessage.setMinHeight(90);
		
		VBox vbRegProduct = new VBox(14);
		vbRegProduct.setPrefWidth(355);
		vbRegProduct.setPrefHeight(320);
		vbRegProduct.setLayoutX(250);
		vbRegProduct.setPadding(new Insets(20, 0, 0, 10));
		vbRegProduct.getChildren().addAll(hbName, hbPrice, hbInStock, hbShipping, hbCategory, hbBtnConfAndMessage);
		
		Pane pProduct = new Pane();
		pProduct.setPrefHeight(320);
		pProduct.setPrefWidth(605);
		pProduct.setLayoutX(18);
		pProduct.setLayoutY(60);
		pProduct.setStyle("-fx-border-color: black; -fx-border-radius: 10px; -fx-border-width: 2px;");
		pProduct.getChildren().addAll(vbDescProduct, vbRegProduct);
		
		pControll.setValueEdit(ipCod);
		
		setBidings(tfName, tfPrice, tfInStock, tfShipping, tfCategory, taDesc, lblMessage);
		
		btnConf.setOnAction(e -> updateProduct());
		
		//------------mudança de scene---------------
		lblAccount.setOnMouseClicked(e -> toAccount());
		lblExit.setOnMouseClicked(e -> toLogin());
		btnReturn.setOnAction(e -> toProductStore());
		
		
		pane.getChildren().addAll(btnReturn, lblHomePage, pProduct);
		
	}
	
	private void toLogin()
	{
		changeSceneController.changeScene(SceneName.LOGIN, pWin);
	}
	
	private void toProductStore()
	{	
		changeSceneController.setCodValue(ipCod);
		changeSceneController.changeScene(SceneName.ALTER_PRODUCT, pWin);
	}
	
	private void toAccount()
	{
		changeSceneController.changeScene(SceneName.ACCOUNT_MENU, pWin);
	}
	
	private void updateProduct()
	{
		try {
			if(pControll.update(ipCod))
			{
				Label concluded = new Label("Produto Alterado!");
				concluded.setStyle("-fx-font-size: 15px; -fx-font-weight: bold;");
				
				Button btnConfirmed = new Button("Confirmar");
				btnConfirmed.setStyle("-fx-background-color: #C2FFC2; -fx-background-radius: 10px; -fx-font-size: 14px;");
				
				VBox vbRegister = new VBox(20);
				vbRegister.setPrefHeight(100);
				vbRegister.setPrefWidth(180);
				vbRegister.setLayoutX(220.5);
				vbRegister.setLayoutY(156);
				vbRegister.setStyle("-fx-background-color: #ffffff; -fx-background-radius: 10px; -fx-border-radius: 10px; -fx-border-color: BLACK; -fx-alignment: center; -fx-spacing: 30px;");
				vbRegister.getChildren().addAll(concluded, btnConfirmed);
				
				Pane pTransp = new Pane();
				pTransp.setPrefWidth(640);
				pTransp.setPrefHeight(400);
				pTransp.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4);");
				pTransp.getChildren().add(vbRegister);
				
				pWin.getChildren().add(pTransp);
				
				btnConfirmed.setOnAction(e -> toProductStore());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void setBidings(TextField tfName, TextField tfPrice, TextField tfInStock, TextField tfShipping, TextField tfCategory, TextArea taDescription, Label lblMessage)
	{
		Bindings.bindBidirectional(tfName.textProperty(), pControll.getNameProperty());
		Bindings.bindBidirectional(tfPrice.textProperty(), pControll.getPriceProperty());
		Bindings.bindBidirectional(tfInStock.textProperty(), pControll.getInStockProperty());
		Bindings.bindBidirectional(tfShipping.textProperty(), pControll.getShippingProperty());
		Bindings.bindBidirectional(tfCategory.textProperty(), pControll.getCategoryProperty());
		Bindings.bindBidirectional(taDescription.textProperty(), pControll.getDescriptionProperty());
		Bindings.bindBidirectional(lblMessage.textProperty(), pControll.getMessageProperty());
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
	
    public void setCodValue(IntegerProperty cod) { ipCod.bindBidirectional(cod); }

}
