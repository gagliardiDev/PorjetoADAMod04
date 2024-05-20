package tech.ada.banco.service.investimento;

import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.model.ClientePJ;
import tech.ada.banco.model.ContaCorrente;
import tech.ada.banco.model.ContaInvestimento;

import java.math.BigDecimal;

public interface InvestirPJImpl extends Investir<ClientePJ> {
    BigDecimal RENDIMENTO = BigDecimal.valueOf(0.02);

    @Override
    default ContaInvestimento investir(ClientePJ cliente, ContaCorrente conta, BigDecimal valor) throws ValorInvalidoException, SaldoInsuficienteException {
        return null;
    }


}
