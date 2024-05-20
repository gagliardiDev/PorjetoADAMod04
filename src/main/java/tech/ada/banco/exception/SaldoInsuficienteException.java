package tech.ada.banco.exception;

public class SaldoInsuficienteException extends Exception{

    public SaldoInsuficienteException() {
        super("Saldo insuficiente!");
    }
}
