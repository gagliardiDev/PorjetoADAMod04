package tech.ada.banco.service;

import tech.ada.banco.model.ContaCorrente;
import tech.ada.banco.service.consultaSaldo.ConsultaSaldo;
import tech.ada.banco.service.deposito.Deposito;
import tech.ada.banco.service.saque.SaquePJImpl;
import tech.ada.banco.service.transferencia.TransferenciaPJImpl;

public class ContaCorrentePJService implements ConsultaSaldo<ContaCorrente>, Deposito<ContaCorrente>,
        SaquePJImpl<ContaCorrente>, TransferenciaPJImpl<ContaCorrente> {
}
