package tech.ada.banco.factory;

import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.ClientePF;

public class ClientePFFactory implements ClienteFactory {
    @Override
    public ClientePF criarCliente() {
        return new ClientePF();
    }
}
