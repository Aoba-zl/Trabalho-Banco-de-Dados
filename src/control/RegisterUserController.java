package control;

import java.sql.SQLException;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.Address;
import model.Client;
import model.ClientAddress;
import model.Store;
import model.User;
import persistence.AddressDao;
import persistence.ClientAddressDao;
import persistence.ClientDao;
import persistence.GenericDao;
import persistence.StoreDao;
import persistence.UserDao;
import utils.ServicoDeCep;

public class RegisterUserController {
    Store loja = new Store();
    Client cliente = new Client();
    Address novoEndereco = new Address();
	User newUser = new User();
    
    // dados
	//User
    private StringProperty spName = new SimpleStringProperty("");
    private StringProperty spPasswd = new SimpleStringProperty("");
    private StringProperty spPhone = new SimpleStringProperty("");
    private StringProperty spEmail = new SimpleStringProperty("");
    
    //Client
    private StringProperty spSocialName = new SimpleStringProperty("");
    private StringProperty spCpf = new SimpleStringProperty("");
    private StringProperty spBirthDate = new SimpleStringProperty("");
    private StringProperty spSex = new SimpleStringProperty("");
    private BooleanProperty spMale = new SimpleBooleanProperty();
    private BooleanProperty spFem = new SimpleBooleanProperty();
    private BooleanProperty spOther = new SimpleBooleanProperty();

    //Store
    private StringProperty spCnpj = new SimpleStringProperty("");
    private StringProperty spStore = new SimpleStringProperty("");
    
    // generico
    private StringProperty spWarning = new SimpleStringProperty("");

    // endereco
    private StringProperty spCep = new SimpleStringProperty("");
    private StringProperty spEstate = new SimpleStringProperty("");
    private StringProperty spNeighborhood = new SimpleStringProperty("");
    private StringProperty spNumber = new SimpleStringProperty("");
    private StringProperty spCity = new SimpleStringProperty("");
    private StringProperty spComplement = new SimpleStringProperty("");
    private StringProperty spStreet = new SimpleStringProperty("");
    
    // endereço cliente
    private StringProperty spAddressName = new SimpleStringProperty("");
  
    public boolean validateUserLogin(User newUser) throws SQLException {
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        /*
        se o login não estiver no BD -> True, pode criar user c/ o login.
         */
		GenericDao gDao = new GenericDao();
		UserDao uDao = new UserDao(gDao);
		User u = new User();
		u.setLogin(uDao.consult(newUser).getLogin()); 
		if (u.getLogin() != newUser.getLogin()) {
				return true;
		}
        return false;
    }
    public boolean generateClient() throws SQLException {
        //TODO: CtrlCadastroUsuario. Corpo da operacao
    	if(!checkValuesClient()) {
    		return false;
    	}
		GenericDao gDao = new GenericDao();
		UserDao uDao = new UserDao(gDao);
		ClientDao cDao = new ClientDao(gDao);
		ClientAddressDao caDao = new ClientAddressDao(gDao, cliente);
		
        novoEndereco.setComplement(getComplementValue());
        novoEndereco.setDoorNumber(Integer.parseInt(getNumberValue()) );
        ClientAddress endereco = new ClientAddress(novoEndereco, getNameValue());
        uDao.insert(newUser);
        cDao.insert(cliente);
        caDao.insert(endereco);
        return true;
    }
    
    public void completeAddress() {
    	if (getCepValue().length() == 9 && getCepValue().contains("-")) {
    		String[] cep = getCepValue().split("-");
    		if(cep.length == 2 && cep[0].length() == 5 && cep[1].length() == 3 && cep[0].matches("\\d+") && cep[1].matches("\\d+")) {
        		try {
        			String[] cepS = getCepValue().split("-");
        			String cepForm = cepS[0]+cepS[1];
        			System.out.println(cepForm);
        			novoEndereco = ServicoDeCep.buscaEnderecoPelo(getCepValue());
        			novoEndereco.setCep(cepForm);
    				spEstate.setValue(novoEndereco.getEstate());
    				spNeighborhood.setValue(novoEndereco.getNeighborhood());
    				spCity.setValue(novoEndereco.getCity());
    				spStreet.setValue(novoEndereco.getStreet());
    				System.out.println(novoEndereco.toString());
    			} catch (Exception e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
    public void generateStore() throws SQLException {
        //TODO: CtrlCadastroUsuario. Corpo da operacao
		GenericDao gDao = new GenericDao();
		UserDao uDao = new UserDao(gDao);
		StoreDao sDao = new StoreDao(gDao);
		AddressDao aDao = new AddressDao(gDao, newUser);
		novoEndereco.setDoorNumber(Integer.parseInt(getNumberValue()));
		novoEndereco.setComplement(getComplementValue());
        uDao.insert(newUser);
        sDao.insert(loja);
        aDao.insert(novoEndereco);
    }
    public Store consultStore (Store store) {
		GenericDao gDao = new GenericDao();
		StoreDao sDao = new StoreDao(gDao);
		try {
			store = sDao.consult(store);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
    	return store;
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
    public boolean checkValuesClient () throws SQLException {
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
    	if (getEmailValue().trim().isBlank() || getEmailValue().length() > 100 || !getEmailValue().contains("@") || !getEmailValue().contains(".com") ) {
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
    	User user = new User(getNameValue());
    	if (validateUserLogin(user)) {
    		spWarning.setValue("Nome Ja Cadastrado");
        	return false;
    	}
    	newUser.setLogin(getNameValue());
    	newUser.setEmail(getEmailValue());
    	newUser.setPassword(getPasswdValue());
    	newUser.setPermission("client");
    	newUser.setTelephone(getPhoneValue());

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
    	
        cliente.setLogin(getNameValue());
        cliente.setSocialName(getSocialNameValue());
        cliente.setCpf(getCpfValue());
        cliente.setDateBirth(getBirthDateValue());
        cliente.setSex(Sex);
    	return true;
    }
    public boolean checkAddressName() {
    	if (getAddressNameValue().trim().isBlank() || getAddressNameValue().length() > 50) {
    		spWarning.setValue("Nome Invalido");
    		return false;
    	}
    	return true;
    }
    
    public boolean checkValuesAddress () {
    	
    	if(getCepValue().trim().isBlank() || getCepValue().length() != 9 || !getCepValue().contains("-")) {
    		spWarning.setValue("CEP Deve Estar no formato: 00000-000");
    		return false;
    	}
    	if(getCity() == null) {
    		spWarning.setValue("CEP Invalido");
    		return false;
    	}
    	if(getNumberValue().trim().isBlank() || getNumberValue().length() > 5 || !getNumberValue().matches("\\d+")) {
    		spWarning.setValue("Numero Invalido");
    		return false;
    	}
    	if(getComplementValue ().trim().isBlank() || getComplementValue ().length() > 50) {
    		spWarning.setValue("Complemento Invalido");
    		return false;
    	}
    	return true;
    }
    public boolean checkValuesStore() throws SQLException {
    	if (getPasswdValue ().trim().isBlank() || getPasswdValue ().length() > 60) {
    		spWarning.setValue("Senha Invalido");
        	return false;
    	}
    	if (getStoreValue().trim().isBlank() || getStoreValue().length() > 100) {
    		spWarning.setValue("Nome da Loja Invalido");
    		return false;
    	}
    	if (getNameValue().trim().isBlank() || getNameValue().length() > 60) {
    		spWarning.setValue("Nome Invalido");
    		return false;
    	}
    	User user = new User(getNameValue());
    	if ( validateUserLogin(user)) {
    		spWarning.setValue("Nome já Cadastrado");
        	return false;
    	}
    	if(getCnpjValue().trim().isBlank() || getCnpjValue().length() != 14 || !getCnpjValue().matches("\\d+") ) {
    		spWarning.setValue("CNPJ Invalido");
    		return false;
    	}
    	if (getEmailValue().trim().isBlank() || getEmailValue().length() > 100 || !getEmailValue().contains("@") || !getEmailValue().contains(".com") ) {
    		spWarning.setValue("Email Invalido");
        	return false;
    	}
    	if (getPhoneValue().trim().isBlank() || !getPhoneValue().matches("\\d+") || getPhoneValue().length() <9 || getPhoneValue().length() > 10 ) {
    		spWarning.setValue("Telefone Invalido");
        	return false;
    	}
    	newUser.setLogin(getNameValue());
    	newUser.setEmail(getEmailValue());
    	newUser.setPassword(getPasswdValue());
    	newUser.setPermission("store");
    	newUser.setTelephone(getPhoneValue());
    	
    	loja.setLogin(getNameValue());
    	loja.setNameStore(getStoreValue());
    	loja.setCnpj(getCnpjValue());
    	return true;
    }
    public void clean () {
        spAddressName.setValue("");
        spBirthDate.setValue("");
        spCep.setValue("");
        spCity.setValue("");
        spComplement.setValue("");
        spCpf.setValue("");
        spEmail.setValue("");
        spEstate.setValue("");
        spName.setValue("");
        spNeighborhood.setValue("");
        spNumber.setValue("");
        spPasswd.setValue("");
        spPhone.setValue("");
        spSex.setValue("");
        spSocialName.setValue("");
        spStreet.setValue("");
        spWarning.setValue("");
        spCnpj.setValue("");
        spStore.setValue("");
    }
    // Conexão com dados
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
    public StringProperty getCnpj () { return spCnpj; }
    public StringProperty getStore () { return spStore; }
    
    
    public String getNameValue () { return spName.getValue(); }
    public String getCpfValue () { return spCpf.getValue(); }
    public String getEmailValue () { return spEmail.getValue(); }
    public String getPasswdValue () { return spPasswd.getValue(); }	
    public String getSocialNameValue () { return spSocialName.getValue(); }
    public String getPhoneValue () { return spPhone.getValue(); }
    public String getBirthDateValue () { return spBirthDate.getValue(); }
    public String getSexValue () { return spSex.getValue(); }
    public String getCnpjValue () { return spCnpj.getValue(); }
    public String getStoreValue () { return spStore.getValue(); }
    // Conexão com endereco
    public StringProperty getAddressName () { return spAddressName; }
    public StringProperty getCep () { return spCep; }
    public StringProperty getState () { return spEstate; }
    public StringProperty getNeighborhood () { return spNeighborhood; }
    public StringProperty getNumber () { return spNumber; }
    public StringProperty getCity () { return spCity; }
    public StringProperty getComplement () { return spComplement; }
    public StringProperty getStreet () { return spStreet; }

    public String getAddressNameValue () { return spAddressName.getValue(); }
    public String getCepValue () { return spCep.getValue(); }
    public String getStateValue () { return spEstate.getValue(); }
    public String getNeighborhoodValue () { return spNeighborhood.getValue(); }
    public String getNumberValue () { return spNumber.getValue(); }
    public String getCityValue () { return spCity.getValue(); }
    public String getComplementValue () { return spComplement.getValue(); }
    public String getStreetValue () { return spStreet.getValue(); }
    
}