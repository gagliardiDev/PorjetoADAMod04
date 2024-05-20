package tech.ada.banco.service;

import tech.ada.banco.model.ContaPoupanca;
import tech.ada.banco.service.consultaSaldo.ConsultaSaldo;
import tech.ada.banco.service.deposito.Deposito;
import tech.ada.banco.service.saque.SaquePFImpl;
import tech.ada.banco.service.transferencia.TransferenciaPFImpl;

public class ContaPoupancaService implements ConsultaSaldo<ContaPoupanca>, SaquePFImpl<ContaPoupanca>,
        Deposito<ContaPoupanca>, TransferenciaPFImpl<ContaPoupanca> {
}
