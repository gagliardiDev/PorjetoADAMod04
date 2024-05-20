package tech.ada.banco.service;

import tech.ada.banco.dto.ContaDTO;
import tech.ada.banco.exception.NaoEncontradoException;
import tech.ada.banco.exception.SaldoExistenteException;
import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.model.ClientePF;
import tech.ada.banco.model.ContaCorrente;
import tech.ada.banco.repository.ClienteRepository;
import tech.ada.banco.repository.ContaRepository;
import tech.ada.banco.service.consultaSaldo.ConsultaSaldo;
import tech.ada.banco.service.deposito.Deposito;
import tech.ada.banco.service.saque.SaquePFImpl;
import tech.ada.banco.service.transferencia.TransferenciaPFImpl;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContaCorrentePFService implements ConsultaSaldo<ContaCorrente>, Deposito<ContaCorrente>,
        SaquePFImpl<ContaCorrente>, TransferenciaPFImpl<ContaCorrente> {

    private final ContaRepository contaRepository;
    private final ClienteRepository<ClientePF> clienteRepository;
    private final ModelMapper modelMapper;


    private ContaDTO convertDto(ContaCorrente conta){
        return modelMapper.map(conta, ContaDTO.class);
    }

    private ContaCorrente convertFromDto(ContaDTO contaDTO){
        return modelMapper.map(contaDTO, ContaCorrente.class);
    }

    public List<ContaDTO> listarContas(UUID clienteUuid){
        var cliente = clienteRepository.findByUuid(clienteUuid);
        return contaRepository.findByCliente(cliente).stream().map(this::convertDto).collect(Collectors.toList());
    }

    public ContaDTO salvar(ContaDTO contaDTO) throws NaoEncontradoException {
        var cliente = clienteRepository.findByUuid(contaDTO.getClienteUuid()).orElseThrow();
        var conta = convertFromDto(contaDTO);
        conta.setCliente(cliente);
        conta.setUuid(UUID.randomUUID());
        conta.setDataCriacao(LocalDate.now());
        return convertDto(contaRepository.save(conta));
    }

    public Optional<ContaDTO> buscarPorUuid(UUID uuid){
        return contaRepository.findByUuid(uuid).map(this::convertDto);
    }

    public void excluir(UUID uuid) throws SaldoExistenteException {
        var conta = contaRepository.findByUuid(uuid).orElseThrow();
        if(conta.getSaldo().compareTo(BigDecimal.ZERO)==0){
            contaRepository.delete(conta);
        }else{
            throw new SaldoExistenteException();
        }
    }

    public ContaDTO sacar(ContaDTO contaDTO) throws SaldoInsuficienteException, ValorInvalidoException {
        var conta = contaRepository.findByUuid(contaDTO.getUuid()).orElseThrow();
        sacar((ClientePF) conta.getCliente(), (ContaCorrente) conta, contaDTO.getValorOperacao());
        return convertDto(contaRepository.save(conta));
    }

    public ContaDTO deposito(ContaDTO contaDTO) throws ValorInvalidoException {
        var conta = contaRepository.findByUuid(contaDTO.getUuid()).orElseThrow();
        depositar((ContaCorrente) conta, contaDTO.getValorOperacao());
        return convertDto(contaRepository.save(conta));
    }

    public ContaDTO consutarSaldo(UUID uuid)  {
        var conta = contaRepository.findByUuid(uuid).orElseThrow();
        return convertDto(conta);
    }

    public ContaDTO transferir(ContaDTO contaDTO) throws SaldoInsuficienteException, ValorInvalidoException {
        var conta = contaRepository.findByUuid(contaDTO.getUuid()).orElseThrow();
        var contaDestino = contaRepository.findByUuid(contaDTO.getContaDestinoUuid()).orElseThrow();
        transferir((ClientePF) conta.getCliente(), (ContaCorrente) conta, contaDTO.getValorOperacao(), contaDestino);
        contaRepository.save(conta);
        contaRepository.save(contaDestino);
        return convertDto(conta);
    }

    public void validarUsuario(String cpf, ContaDTO contaDTO){
        var conta = contaRepository.findByUuid(contaDTO.getUuid()).orElseThrow();
        if(!conta.getCliente().getCpf().equals(cpf)) {
            throw new AccessDeniedException("Apenas o dono da conta pode realizar esta operação.");
        }
    }
}
