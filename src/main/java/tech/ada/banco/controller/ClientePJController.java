package tech.ada.banco.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import tech.ada.banco.dto.ClientePJDTO;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.factory.ClientePJFactory;
import tech.ada.banco.model.ClientePJ;
import tech.ada.banco.service.ClientePJService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientesPJ")
@RequiredArgsConstructor
public class ClientePJController {

    private final ClientePJService service;
    private final ModelMapper modelMapper;

    @GetMapping
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public List<ClientePJDTO> listarTodos(){
        return service.listarClientes().stream().collect(Collectors.toList());
    }

    @GetMapping("/{uuid}")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity<ClientePJDTO> buscarPorUuid(@PathVariable("uuid")UUID uuid){
        var cliente = service.buscarPorUuid(uuid);
        return cliente.map(ClientePJDTO -> ResponseEntity.ok(modelMapper.map(ClientePJDTO, ClientePJDTO.class))).
                orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity excluir(@PathVariable("uuid") UUID uuid){
        service.excluir(uuid);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity<ClientePJDTO> atualizar(@PathVariable("uuid") UUID uuid, @RequestBody ClientePJDTO clientePJDTO){
        clientePJDTO.setUuid(uuid);
        return new ResponseEntity(service.atualizar(clientePJDTO), HttpStatus.OK);
    }

    @PostMapping
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity<ClientePJDTO> inserir(@Valid @RequestBody ClientePJDTO clientePJDTO){
        try {
            service.setClienteFactory(new ClientePJFactory());
            ClientePJ clientePJ = service.criarClientePJ();
            modelMapper.map(clientePJDTO, clientePJ); // Atualize clientePF com os dados do DTO
            var clienteSalvo = service.salvar(clientePJDTO);
            return ResponseEntity.ok(modelMapper.map(clienteSalvo, ClientePJDTO.class));
        } catch (ValorInvalidoException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
