package tech.ada.banco.service.saque;

import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

public interface Saque<T extends Cliente, S extends Conta> {

    void sacar(T cliente, S conta, BigDecimal valor) throws ValorInvalidoException, SaldoInsuficienteException;
}
