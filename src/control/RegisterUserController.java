package control;

import model.Address;
import model.Client;
import model.Store;

public class RegisterUserController
{
    Store loja;
    Client cliente;
    Address novoEndereco;

    public boolean validateUserLogin(String login)
    {
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        /*
        se o login não estiver no BD -> True, pode criar user c/ o login.
         */
        return false;
    }
    public Address completeAddress(String cep)
    {
        Address newAddress = new Address();
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        // vai ser via sets do endereço pra ficar bunitu
        // qlqr coisa estranha vou botar a culpa no Ucle Bob. Discutam c/ ele.
        return newAddress;
    }
    public Client generateClient()
    {
        Client newClient = new Client();
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return newClient;
    }
    public Store generateStore()
    {
        Store newStore = new Store();
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return newStore;
    }
    public Client updateClient()
    {
        Client client = null;
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return client;
    }
    public Store updateStore()
    {
        Store store = null;
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return store;
    }
}