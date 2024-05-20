package tech.ada.banco.service;

import tech.ada.banco.model.ContaInvestimento;
import tech.ada.banco.service.consultaSaldo.ConsultaSaldo;
import tech.ada.banco.service.deposito.Deposito;
import tech.ada.banco.service.investimento.InvestirPJImpl;
import tech.ada.banco.service.saque.SaquePJImpl;
import tech.ada.banco.service.transferencia.TransferenciaPJImpl;

public class ContaInvestimentoPJService implements SaquePJImpl<ContaInvestimento>, ConsultaSaldo<ContaInvestimento>,
        Deposito<ContaInvestimento>, TransferenciaPJImpl<ContaInvestimento>, InvestirPJImpl {
}
