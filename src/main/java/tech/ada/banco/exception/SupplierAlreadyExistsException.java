package tech.ada.banco.exception;

public class SupplierAlreadyExistsException extends RuntimeException{

    public SupplierAlreadyExistsException() {
    }

    public SupplierAlreadyExistsException(String message) {
        super(message);
    }
}
