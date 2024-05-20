package tech.ada.banco.controller;

import tech.ada.banco.dto.ContaDTO;
import tech.ada.banco.enums.StatusEnum;
import tech.ada.banco.exception.NaoEncontradoException;
import tech.ada.banco.exception.SaldoExistenteException;
import tech.ada.banco.exception.SaldoInsuficienteException;
import tech.ada.banco.exception.ValorInvalidoException;
import tech.ada.banco.login.JwtService;
import tech.ada.banco.service.ContaCorrentePFService;
import tech.ada.banco.usuario.Usuario;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/contas")
@RequiredArgsConstructor
public class ContaController {

    private final ContaCorrentePFService service;
    private final ModelMapper modelMapper;
    private final JwtService jwtService;

    @GetMapping("/{uuid}")
    public List<ContaDTO> listarContasUsuario(@PathVariable("uuid") UUID uuid){
        return service.listarContas(uuid).stream().collect(Collectors.toList());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity<ContaDTO> inserir(@Valid @RequestBody ContaDTO contaDTO){
        try {
            return new ResponseEntity<>(service.salvar(contaDTO), HttpStatus.CREATED);
        }catch (NaoEncontradoException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name())")
    public ResponseEntity excluir(@PathVariable("uuid") UUID uuid){
        try {
            service.excluir(uuid);
            return ResponseEntity.noContent().build();
        } catch (SaldoExistenteException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/saldo/{uuid}")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).FUNCIONARIO.name(),T(tech.ada.banco.usuario.Role).CLIENTE.name())")
    public ResponseEntity consultarSaldo(@PathVariable("uuid") UUID uuid){
        return new ResponseEntity<>(service.consutarSaldo(uuid), HttpStatus.OK);
    }

    @PostMapping("/sacar")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).CLIENTE.name())")
    public ResponseEntity<ContaDTO> sacar(@Valid @RequestBody ContaDTO contaDTO, @RequestHeader (name="Authorization") String bearerToken){
        try {
            service.validarUsuario(obterCpfToken(bearerToken),contaDTO);
            return new ResponseEntity<>(service.sacar(contaDTO), HttpStatus.OK);
        } catch (SaldoInsuficienteException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (ValorInvalidoException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/depositar")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).CLIENTE.name())")
    public ResponseEntity<ContaDTO> depositar(@Valid @RequestBody ContaDTO contaDTO){
        try {
            return new ResponseEntity<>(service.deposito(contaDTO), HttpStatus.OK);
        } catch (ValorInvalidoException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/transferir")
    @PreAuthorize("hasAnyRole(T(tech.ada.banco.usuario.Role).ADMIN.name(),T(tech.ada.banco.usuario.Role).CLIENTE.name())")
    public ResponseEntity<ContaDTO> transferir(@Valid @RequestBody ContaDTO contaDTO, @RequestHeader (name="Authorization") String bearerToken){
        try {
            service.validarUsuario(obterCpfToken(bearerToken),contaDTO);
            return new ResponseEntity<>(service.transferir(contaDTO), HttpStatus.OK);
        } catch (ValorInvalidoException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        } catch (SaldoInsuficienteException e) {
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    private String obterCpfToken(String bearerToken){
        String token = bearerToken.substring(7);
        Usuario u = (Usuario) jwtService.getUserDetails(token);
        return u.getCpf();
    }

}
