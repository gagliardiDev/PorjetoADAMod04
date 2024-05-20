package tech.ada.banco.service;

import lombok.Setter;
import tech.ada.banco.dto.ClientePFDTO;
import tech.ada.banco.enums.StatusEnum;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.factory.ClienteFactory;
import tech.ada.banco.factory.ClientePFFactory;
import tech.ada.banco.model.Cliente;
import tech.ada.banco.model.ClientePF;
import tech.ada.banco.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientePFService {

    @Setter
    private ClientePFFactory clientePFFactory;

    private final ClienteRepository<ClientePF> clienteRepository;

    private final ModelMapper modelMapper;

    public ClientePF criarClientePF() {
        if (clientePFFactory == null) {
            throw new IllegalStateException("ClientePFFactory não está definido.");
        }
        return clientePFFactory.criarCliente();
    }

    private ClientePFDTO convertDto(ClientePF cliente){
        return modelMapper.map(cliente, ClientePFDTO.class);
    }

    private ClientePF convertFromDto(ClientePFDTO ClientePFDTO){
        return modelMapper.map(ClientePFDTO, ClientePF.class);
    }

    public List<ClientePFDTO> listarClientes(){
        return clienteRepository.findAll().stream().map(this::convertDto).collect(Collectors.toList());
    }

    public ClientePFDTO salvar(ClientePFDTO clientePFDTO) throws ValorInvalidoException {
       if(clientePFDTO.getDataNascimento().isAfter(LocalDate.now().minusYears(18))){
            throw new ValorInvalidoException("Cliente não pode ser menor de 18 anos");
        }

        var cliente = convertFromDto(clientePFDTO);
        cliente.setUuid(UUID.randomUUID());
        cliente.setDataCadastro(LocalDate.now());
        cliente.setStatus(StatusEnum.ATIVO);
        return convertDto(clienteRepository.save(cliente));
    }

    public Optional<ClientePFDTO> buscarPorUuid(UUID uuid){
        return clienteRepository.findByUuid(uuid).map(this::convertDto);
    }

    public void excluir(UUID uuid){
        clienteRepository.delete(clienteRepository.findByUuid(uuid).orElseThrow());
    }

    public ClientePFDTO atualizar(ClientePFDTO clientePFDTO){
        var cliente = clienteRepository.findByUuid(clientePFDTO.getUuid()).orElseThrow();
        cliente.setNome(clientePFDTO.getNome());
        return convertDto(clienteRepository.save(cliente));
    }

    public void setClienteFactory(ClientePFFactory clientePFFactory) {
    }
}
