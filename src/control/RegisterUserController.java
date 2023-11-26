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
/**
 * Controlador responsável pelas operações dos cadastros de Loja, Usuario e Cliente .
 */
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
	/**
	 * Ve se o nome de usuario ja esta cadastrado.
	 *
	 * @param newUser tera o nome da mesma para a procura.
	 * @return Se true não esta cadastrado, se false esta cadastrado
	 * @throws SQLException Se ocorrer um erro ao acessar o banco de dados durante a inserção do novo endereço.
	 */
    public boolean validateUserLogin(User newUser) throws SQLException {
		GenericDao gDao = new GenericDao();
		UserDao uDao = new UserDao(gDao);
		User u = new User();
		u.setLogin(newUser.getLogin());
		u = uDao.consult(u);
		if (u.getLogin() != newUser.getLogin()) {
				return true;
		}
        return false;
    }
    
    public boolean validateEmailLogin(User newEmail) throws SQLException {
    	GenericDao gDao = new GenericDao();
    	UserDao uDao = new UserDao(gDao);
    	User u = new User();
    	u.setEmail(newEmail.getEmail());
    	if (uDao.verifyEmail(u)) {
    		return true;
    	}
    	return false;
    }
    
	/**
	 * Cria e insere um novo endereço de cliente, Usuario e Cliente no banco de dados.
	 *
	 * @throws SQLException Se ocorrer um erro ao acessar o banco de dados durante a inserção do novo endereço, user ou cliente.
	 */
    public void generateClient() throws SQLException {
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
    }
	/**
	 * Procura por uma CEP passado, fazendo verificaçoes antes.
	 *
	 * @throws Pois o CEP passado pode não existir.
	 */
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
	/**
	 * Cria e insere um novo endereço de loja, Usuario e loja no banco de dados.
	 *
	 * @throws SQLException Se ocorrer um erro ao acessar o banco de dados durante a inserção do novo endereço, user ou cliente.
	 */
    public void generateStore() throws SQLException {
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
	/**
	 * Procura por uma loja pelo id e retorna ela completa.
	 *
	 * @param store tera o id da mesma para a procura.
	 * @return O novo objeto store com todos os dados do banco.
	 * @throws SQLException Se ocorrer um erro ao acessar o banco de dados durante a inserção do novo endereço.
	 */
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
    
   
	/**
	 * Checa se todos os valores do cliente estão validos.
	 * @return True se for valido, false caso não
	 */
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
    	User user = new User();
    	user.setLogin(getNameValue());
    	user.setEmail(getEmailValue());
    	if (validateUserLogin(user)) {
    		spWarning.setValue("Nome Ja Cadastrado");
        	return false;
    	}
    	if (validateEmailLogin(user)) {
    		spWarning.setValue("Email Ja Cadastrado");
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
        
        spWarning.setValue("");
    	return true;
    }
	/**
	 * Checa se o nome do endereço do cliente é valido
	 * @return True se for valido, false caso não
	 */
    public boolean checkAddressName() {
    	if (getAddressNameValue().trim().isBlank() || getAddressNameValue().length() > 50) {
    		spWarning.setValue("Nome Invalido");
    		return false;
    	}
    	return true;
    }
	/**
	 * Checa se todos os valores de endereço estão validos.
	 * @return True se for valido, false caso não
	 */
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
    	if(getComplementValue ().length() > 50) {
    		spWarning.setValue("Complemento Invalido");
    		return false;
    	}
    	return true;
    }
	/**
	 * Checa se todos os valores da loja estão validos.
	 * @return True se for valido, false caso não
	 */
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
    	User user = new User();
    	user.setLogin(getNameValue());
    	user.setEmail(getEmailValue());
    	if (validateUserLogin(user)) {
    		spWarning.setValue("Nome Ja Cadastrado");
        	return false;
    	}
    	if (validateEmailLogin(user)) {
    		spWarning.setValue("Email Ja Cadastrado");
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
    	
        spWarning.setValue("");
    	return true;
    }
	/**
	 * Limpa todos os campos
	 */
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
    /**
     * Retorna a propriedade do nome.
     * 
     * @return A propriedade do nome como StringProperty.
     */
    public StringProperty getName() {
        return spName;
    }
    /**
     * Retorna a propriedade do CPF.
     * 
     * @return A propriedade do CPF como StringProperty.
     */
    public StringProperty getCpf() {
        return spCpf;
    }
    /**
     * Retorna a propriedade do email.
     * 
     * @return A propriedade do email como StringProperty.
     */
    public StringProperty getEmail() {
        return spEmail;
    }
    /**
     * Retorna a propriedade da senha.
     * 
     * @return A propriedade da senha como StringProperty.
     */
    public StringProperty getPasswd() {
        return spPasswd;
    }
    /**
     * Retorna a propriedade do nome social.
     * 
     * @return A propriedade do nome social como StringProperty.
     */
    public StringProperty getSocialName() {
        return spSocialName;
    }
    /**
     * Retorna a propriedade do telefone.
     * 
     * @return A propriedade do telefone como StringProperty.
     */
    public StringProperty getPhone() {
        return spPhone;
    }
    /**
     * Retorna a propriedade da data de nascimento.
     * 
     * @return A propriedade da data de nascimento como StringProperty.
     */
    public StringProperty getBirthDate() {
        return spBirthDate;
    }
    /**
     * Retorna a propriedade do sexo.
     * 
     * @return A propriedade do sexo como StringProperty.
     */
    public StringProperty getSex() {
        return spSex;
    }
    /**
     * Retorna a propriedade do aviso.
     * 
     * @return A propriedade do aviso como StringProperty.
     */
    public StringProperty getWarning() {
        return spWarning;
    }
    /**
     * Retorna a propriedade indicando masculino.
     * 
     * @return A propriedade indicando masculino como BooleanProperty.
     */
    public BooleanProperty getMale() {
        return spMale;
    }
    /**
     * Retorna a propriedade indicando feminino.
     * 
     * @return A propriedade indicando feminino como BooleanProperty.
     */
    public BooleanProperty getFem() {
        return spFem;
    }
    /**
     * Retorna a propriedade indicando outro gênero.
     * 
     * @return A propriedade indicando outro gênero como BooleanProperty.
     */
    public BooleanProperty getOther() {
        return spOther;
    }
    /**
     * Retorna a propriedade do CNPJ.
     * 
     * @return A propriedade do CNPJ como StringProperty.
     */
    public StringProperty getCnpj() {
        return spCnpj;
    }
    /**
     * Retorna a propriedade da loja.
     * 
     * @return A propriedade da loja como StringProperty.
     */
    public StringProperty getStore() {
        return spStore;
    }
	/**
	 * Obtém o valor atual da propriedade de Name.
	 * @return O valor do nome.
	 */
    public String getNameValue () { return spName.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Cpf.
	 * @return O valor do Cpf.
	 */
    public String getCpfValue () { return spCpf.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Email.
	 * @return O valor do Email.
	 */
    public String getEmailValue () { return spEmail.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Passwd.
	 * @return O valor da senha.
	 */
    public String getPasswdValue () { return spPasswd.getValue(); }	
	/**
	 * Obtém o valor atual da propriedade de SocialName.
	 * @return O valor do Nome social.
	 */
    public String getSocialNameValue () { return spSocialName.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Phone.
	 * @return O valor do Telefone.
	 */
    public String getPhoneValue () { return spPhone.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de BirthDate.
	 * @return O valor do aniversario.
	 */
    public String getBirthDateValue () { return spBirthDate.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de tSex.
	 * @return O valor do sexo.
	 */
    public String getSexValue () { return spSex.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Cnpj.
	 * @return O valor do Cnpj.
	 */
    public String getCnpjValue () { return spCnpj.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Store.
	 * @return O valor nome da loja.
	 */
    public String getStoreValue () { return spStore.getValue(); }
    // Conexão com endereco
    /**
     * Retorna a propriedade do nome do endereço.
     * 
     * @return A propriedade do nome do endereço como StringProperty.
     */
    public StringProperty getAddressName() {
        return spAddressName;
    }
    /**
     * Retorna a propriedade do CEP.
     * 
     * @return A propriedade do CEP como StringProperty.
     */
    public StringProperty getCep() {
        return spCep;
    }
    /**
     * Retorna a propriedade do estado.
     * 
     * @return A propriedade do estado como StringProperty.
     */
    public StringProperty getState() {
        return spEstate;
    }
    /**
     * Retorna a propriedade do bairro.
     * 
     * @return A propriedade do bairro como StringProperty.
     */
    public StringProperty getNeighborhood() {
        return spNeighborhood;
    }
    /**
     * Retorna a propriedade do número.
     * 
     * @return A propriedade do número como StringProperty.
     */
    public StringProperty getNumber() {
        return spNumber;
    }
    /**
     * Retorna a propriedade da cidade.
     * 
     * @return A propriedade da cidade como StringProperty.
     */
    public StringProperty getCity() {
        return spCity;
    }
    /**
     * Retorna a propriedade do complemento.
     * 
     * @return A propriedade do complemento como StringProperty.
     */
    public StringProperty getComplement() {
        return spComplement;
    }
    /**
     * Retorna a propriedade da rua.
     * 
     * @return A propriedade da rua como StringProperty.
     */
    public StringProperty getStreet() {
        return spStreet;
    }
	/**
	 * Obtém o valor atual da propriedade de AddressName.
	 * @return O valor do Nome do endereço.
	 */
    public String getAddressNameValue () { return spAddressName.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Cep.
	 * @return O valor do Cep.
	 */
    public String getCepValue () { return spCep.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de State.
	 * @return O valor do estado.
	 */
    public String getStateValue () { return spEstate.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Neighborhood.
	 * @return O valor do bairro.
	 */
    public String getNeighborhoodValue () { return spNeighborhood.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Number.
	 * @return O valor do numero da casa.
	 */
    public String getNumberValue () { return spNumber.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de City.
	 * @return O valor da cidade.
	 */
    public String getCityValue () { return spCity.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de complement.
	 * @return O valor do complemento.
	 */
    public String getComplementValue () { return spComplement.getValue(); }
	/**
	 * Obtém o valor atual da propriedade de Street.
	 * @return O valor da rua.
	 */
    public String getStreetValue () { return spStreet.getValue(); }
    
}