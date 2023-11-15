package control;

import java.sql.SQLException;

import factory.ClientFactory;
import factory.UserFactory;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import model.Address;
import model.Client;
import model.Store;
import model.User;
import persistence.ClientDao;
import persistence.GenericDao;
import persistence.UserDao;

public class RegisterUserController {
    Store loja;
    Client cliente;
    Address novoEndereco;
    
    // cliente
    private TextField tfName;
    private TextField tfCPF;
    private TextField tfEmail;
    private TextField tfPasswd;
    private TextField tfSocialName;
    private TextField tfPhone;
    private TextField tfBirthDate;
    private TextField tfSex;
    private RadioButton rbMale;
    private RadioButton rbFem;
    private RadioButton rbOther;
    private Label lblWarning;
    
    
    // Cliente
    public RegisterUserController (TextField tfName,TextField tfCPF,TextField tfEmail,TextField tfPasswd,TextField tfSocialName,TextField tfPhone,TextField tfBirthDate,TextField tfSex, RadioButton rbMale, RadioButton rbFem, RadioButton rbOther,Label lblWarning) {
    	this.tfBirthDate = tfBirthDate;
    	this.tfCPF = tfCPF;
    	this.tfEmail = tfEmail;
    	this.tfName = tfName;
    	this.tfPasswd = tfPasswd;
    	this.tfPhone = tfPhone;
    	this.tfSex = tfSex;
    	this.tfSocialName = tfSocialName;
    	this.rbFem = rbFem;
    	this.rbMale = rbMale;
    	this.rbOther = rbOther;
    	this.lblWarning = lblWarning;
    }
    
    public boolean validateUserLogin(String login) {
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        /*
        se o login não estiver no BD -> True, pode criar user c/ o login.
         */
        return false;
    }
    public Address completeAddress(String cep) {
        Address newAddress = new Address();
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        // vai ser via sets do endereço pra ficar bunitu
        // qlqr coisa estranha vou botar a culpa no Ucle Bob. Discutam c/ ele.
        return newAddress;
    }
    public boolean generateClient() throws SQLException {
        //TODO: CtrlCadastroUsuario. Corpo da operacao
    	if(!checkValuesClient()) {
    		return false;
    	}
		GenericDao gDao = new GenericDao();
		UserDao uDao = new UserDao(gDao);
		ClientDao cDao = new ClientDao(gDao);
    	User newUser = new UserFactory().create(tfName.getText().trim(), tfPasswd.getText().trim(), "client", tfEmail.getText().trim(), tfPhone.getText().trim());
    	String Sex;
    	if (rbMale.isSelected()) {
    		Sex = "Masculino";
    	} else { 
    		if (rbFem.isSelected()){
    			Sex = "Feminino";
    		} else {
    			Sex = tfSex.getText().trim();
    		}
    	} 	
        Client newClient = new ClientFactory().create(newUser, tfSocialName.getText().trim(), tfCPF.getText().trim(), tfBirthDate.getText().trim(), Sex);
        uDao.insert(newUser);
        cDao.insert(newClient);
        return true;
    }
    public Store generateStore() {
        Store newStore = new Store();
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return newStore;
    }
    public Client updateClient() {
        Client client = null;
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return client;
    }
    public Store updateStore() {
        Store store = null;
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return store;
    }
    private boolean checkValuesClient () {
    	
    	if (tfBirthDate.getText().trim().isBlank()) {
    		lblWarning.setText("Nome Social Invalido");
        	return false;
    	}
    	if (tfCPF.getText().trim().isBlank() || tfCPF.getText().matches("\\d+")) {
    		lblWarning.setText("Nome Social Invalido");
        	return false;
    	}
    	if (tfEmail.getText().trim().isBlank()) {
    		lblWarning.setText("Nome Social Invalido");
        	return false;
    	}
    	if (tfName.getText().trim().isBlank()) {
    		lblWarning.setText("Nome Social Invalido");
        	return false;
    	}
    	if (tfPasswd.getText().trim().isBlank()) {
    		lblWarning.setText("Nome Social Invalido");
        	return false;
    	}
    	if (tfPhone.getText().trim().isBlank() || tfCPF.getText().matches("\\d+")) {
    		lblWarning.setText("Nome Social Invalido");
        	return false;
    	}
    	if (rbOther.isSelected()) {
    		if (tfSex.getText().trim().isBlank()) {
    			lblWarning.setText("Nome Social Invalido");
    			return false;
    		}
    	}
    	if (tfSocialName.getText().trim().isBlank() || tfSocialName.getText().length() > 100) {
    		lblWarning.setText("Nome Social Invalido");
        	return false;
    	}
    	return true;
    }
    
}