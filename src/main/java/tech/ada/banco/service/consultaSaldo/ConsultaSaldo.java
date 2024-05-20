package tech.ada.banco.service.consultaSaldo;



import tech.ada.banco.model.Conta;

import java.math.BigDecimal;

public interface ConsultaSaldo<T extends Conta> {

    default BigDecimal consultarSaldo(T conta) {
        return conta.getSaldo();
    }

}
