package control;

import java.sql.SQLException;

import factory.ClientFactory;
import factory.UserFactory;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
    private StringProperty spName = new SimpleStringProperty("");
    private StringProperty spCpf = new SimpleStringProperty("");
    private StringProperty spEmail = new SimpleStringProperty("");
    private StringProperty spPasswd = new SimpleStringProperty("");
    private StringProperty spSocialName = new SimpleStringProperty("");
    private StringProperty spPhone = new SimpleStringProperty("");
    private StringProperty spBirthDate = new SimpleStringProperty("");
    private StringProperty spSex = new SimpleStringProperty("");
    private StringProperty spWarning = new SimpleStringProperty("");
    private BooleanProperty spMale = new SimpleBooleanProperty();
    private BooleanProperty spFem = new SimpleBooleanProperty();
    private BooleanProperty spOther = new SimpleBooleanProperty();
    
 
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
		
    	User newUser = new User(getNameValue());
    	newUser.setEmail(getEmailValue());
    	newUser.setPassword(getPasswdValue());
    	newUser.setPermission("client");
    	newUser.setTelephone(getPhoneValue());
    	if (validateUserLogin(newUser)) {
    		spWarning.setValue("Nome Ja Cadastrado");
        	return false;
    	}
    	String Sex;
    	if (spMale.getValue()) {
    		Sex = "Masculino";
    	} else { 
    		if (spFem.getValue()){
    			Sex = "Feminino";
    		} else {
    			Sex = getSexValue ();
    		}
    	} 	
        Client newClient = new Client();
        newClient.setLogin(getNameValue());
        newClient.setSocialName(getSocialNameValue());
        newClient.setCpf(getCpfValue());
        newClient.setDateBirth(getBirthDateValue());
        newClient.setSex(Sex);
        
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
    public boolean checkValuesClient () {
    	System.out.println(getName());
    	// 01-02-2013 formato que deve chegar
    	if (!getBirthDateValue ().trim().isBlank() && getBirthDateValue ().length() == 10) {
    		spBirthDate.setValue(spBirthDate.getValue().replace("/", "-"));
    		String[] data = getBirthDateValue().split("-");
    		if (data.length !=3 || data[0].length() !=2 || data[1].length() !=2 || data[2].length() !=4 || !data[0].matches("\\d+")|| !data[1].matches("\\d+")|| !data[2].matches("\\d+") || Integer.parseInt(data[2]) < 1800 || Integer.parseInt(data[0]) > 31 || Integer.parseInt(data[1]) > 12 ) {
    			spWarning.setValue("Data de Nascimento invalida");
            	return false;
    		}
    	}else {
    		spWarning.setValue("Data de Nascimento Deve Estar no Formato: dd-mm-aaaa");
    		return false;
    	}
    	
    	if (getCpfValue().trim().isBlank() || !getCpfValue().matches("\\d+") || getCpfValue().length() != 11) {
    		spWarning.setValue("CPF Invalido");
        	return false;
    	}
    	if (getEmailValue().trim().isBlank() || getEmailValue().length() > 100) {
    		spWarning.setValue("Email Invalido");
        	return false;
    	}
    	if (getNameValue().trim().isBlank() || getNameValue().length() > 60) {
    		spWarning.setValue("Nome de Usuario Invalido");
        	return false;
    	}
    	if (getPasswdValue ().trim().isBlank() || getPasswdValue ().length() > 60) {
    		spWarning.setValue("Senha Invalido");
        	return false;
    	}
    	if (getPhoneValue().trim().isBlank() || !getPhoneValue().matches("\\d+") || getPhoneValue().length() <9 || getPhoneValue().length() > 10 ) {
    		spWarning.setValue("Telefone Invalido");
        	return false;
    	}
    	if (spOther.getValue()) {
    		if (getSexValue ().trim().isBlank() || getSexValue ().length() > 25) {
    			spWarning.setValue("Sexo Invalido");
    			return false;
    		}
    	}
    	if (!spFem.getValue() && !spMale.getValue() && !spOther.getValue()) {
    		spWarning.setValue("Selecione um sexo");
        	return false;
    	}
    	
    	if (getSocialNameValue().trim().isBlank() || getSocialNameValue().length() > 100) {
    		spWarning.setValue("Nome Social Invalido");
        	return false;
    	}
    	return true;
    }
    public StringProperty getName () { return spName; }
    public StringProperty getCpf () { return spCpf; }
    public StringProperty getEmail () { return spEmail; }
    public StringProperty getPasswd () { return spPasswd; }
    public StringProperty getSocialName () { return spSocialName; }
    public StringProperty getPhone () { return spPhone; }
    public StringProperty getBirthDate () { return spBirthDate; }
    public StringProperty getSex () { return spSex; }
    public StringProperty getWarning () { return spWarning; }
    public BooleanProperty getMale () { return spMale; }
    public BooleanProperty getFem () { return spFem; }
    public BooleanProperty getOther () { return spOther; }
    
    public String getNameValue () { return spName.getValue(); }
    public String getCpfValue () { return spCpf.getValue(); }
    public String getEmailValue () { return spEmail.getValue(); }
    public String getPasswdValue () { return spPasswd.getValue(); }	
    public String getSocialNameValue () { return spSocialName.getValue(); }
    public String getPhoneValue () { return spPhone.getValue(); }
    public String getBirthDateValue () { return spBirthDate.getValue(); }
    public String getSexValue () { return spSex.getValue(); }
    
}