package tech.ada.banco.factory;

import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.ClientePJ;



public class ClientePJFactory implements ClienteFactory {
    @Override
    public ClientePJ criarCliente() {
        return new ClientePJ();
    }
}