package controle;

import modelo.Cliente;
import modelo.Endereco;
import modelo.Loja;

public class CtrlCadastroUsuario
{
    Loja loja;
    Cliente cliente;
    Endereco novoEndereco;

    public boolean validarLoginUsuario (String login)
    {
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        /*
        se o login não estiver no BD -> True, pode criar user c/ o login.
         */
        return false;
    }
    public Endereco completarEndereco (String cep)
    {
        Endereco novoEndereco = new Endereco();
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        // vai ser via sets do endereço pra ficar bunitu
        // qlqr coisa estranha vou botar a culpa no Ucle Bob. Discutam c/ ele.
        return novoEndereco;
    }
    public Cliente gerarCliente ()
    {
        Cliente novoCliente = new Cliente();
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return novoCliente;
    }
    public Loja gerarLoja ()
    {
        Loja novaLoja = new Loja();
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return novaLoja;
    }
    public Cliente atualizarCliente ()
    {
        Cliente cliente = null;
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return cliente;
    }
    public Loja atualizarLoja ()
    {
        Loja loja = null;
        //TODO: CtrlCadastroUsuario. Corpo da operacao
        return loja;
    }
}