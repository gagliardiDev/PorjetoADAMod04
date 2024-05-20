package tech.ada.banco.service.transferencia;

import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

public interface Transferencia <T extends Cliente, S extends Conta> {

    void transferir(T cliente, S contaOrigem, BigDecimal valor, Conta contaDestino) throws ValorInvalidoException, SaldoInsuficienteException;
}
