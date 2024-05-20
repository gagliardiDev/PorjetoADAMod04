package tech.ada.banco.exception;

public class SaldoExistenteException extends Exception{

    public SaldoExistenteException() {
        super("Conta possui saldo!");

    }
}
