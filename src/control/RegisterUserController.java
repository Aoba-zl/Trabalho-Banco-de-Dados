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
    
    public boolean validateUserLogin(User newUser) throws SQLException {
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        /*
        se o login não estiver no BD -> True, pode criar user c/ o login.
         */
		GenericDao gDao = new GenericDao();
		UserDao uDao = new UserDao(gDao);
		User u = uDao.consult(newUser);
		if (u.getLogin() != newUser.getLogin()) {
				return true;
		}
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
    	if (validateUserLogin(newUser)) {
    		lblWarning.setText("Nome Ja Cadastrado");
        	return false;
    	}
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
    	// 01-02-2013 formato que deve ser chegar
    	if (!tfBirthDate.getText().trim().isBlank() && tfBirthDate.getLength() == 10) {
    		tfBirthDate.setText(tfBirthDate.getText().replace("/", "-"));
    		String[] data = tfBirthDate.getText().split("-");
    		if (data.length !=3 || data[0].length() !=2 || data[1].length() !=2 || data[2].length() !=4 || !data[0].matches("\\d+")|| !data[1].matches("\\d+")|| !data[2].matches("\\d+") || Integer.parseInt(data[2]) < 1800 || Integer.parseInt(data[0]) > 31 || Integer.parseInt(data[1]) > 12 ) {
    			lblWarning.setText("Data de Nascimento invalida");
            	return false;
    		}
    	}else {
    		lblWarning.setText("Data de Nascimento Deve Estar no Formato: dd-mm-aaaa");
    		return false;
    	}
    	
    	if (tfCPF.getText().trim().isBlank() || !tfCPF.getText().matches("\\d+") || tfCPF.getText().length() != 11) {
    		lblWarning.setText("CPF Invalido");
        	return false;
    	}
    	if (tfEmail.getText().trim().isBlank() || tfEmail.getLength() > 100) {
    		lblWarning.setText("Email Invalido");
        	return false;
    	}
    	if (tfName.getText().trim().isBlank() || tfName.getLength() > 60) {
    		lblWarning.setText("Nome de Usuario Invalido");
        	return false;
    	}
    	if (tfPasswd.getText().trim().isBlank() || tfPasswd.getLength() > 60) {
    		lblWarning.setText("Senha Invalido");
        	return false;
    	}
    	if (tfPhone.getText().trim().isBlank() || !tfPhone.getText().matches("\\d+") || tfPhone.getLength() <9 || tfPhone.getLength() > 10 ) {
    		lblWarning.setText("Telefone Invalido");
        	return false;
    	}
    	if (rbOther.isSelected()) {
    		if (tfSex.getText().trim().isBlank() || tfSex.getLength() > 25) {
    			lblWarning.setText("Sexo Invalido");
    			return false;
    		}
    	}
    	if (!rbFem.isSelected() && !rbMale.isSelected() && !rbOther.isSelected()) {
    		lblWarning.setText("Selecione um sexo");
        	return false;
    	}
    	
    	if (tfSocialName.getText().trim().isBlank() || tfSocialName.getText().length() > 100) {
    		lblWarning.setText("Nome Social Invalido");
        	return false;
    	}
    	return true;
    }
    
}