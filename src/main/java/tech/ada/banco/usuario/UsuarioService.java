package tech.ada.banco.usuario;

import tech.ada.banco.exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private UsuarioDTO convertDto(Usuario usuario) {
        return this.modelMapper.map(usuario, UsuarioDTO.class);
    }

    private Usuario convertFromDto(UsuarioDTO usuarioDto) {
        return this.modelMapper.map(usuarioDto, Usuario.class);
    }

    public List<UsuarioDTO> listarUsuarios() {
        return this.usuarioRepository.findAll().stream()
                .map(this::convertDto)
                .collect(Collectors.toList());
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDto) {
        var usuario = new UsuarioBuilder()
                .email(usuarioDto.getEmail())
                .cpf(usuarioDto.getCpf())
                .username(usuarioDto.getUsername())
                .password(passwordEncoder.encode(usuarioDto.getPassword()))
                .telefone(usuarioDto.getTelefone())
                .roles(usuarioDto.getRoles())
                .active(true)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();
        var savedUsuario = this.usuarioRepository.save(usuario);
        return this.convertDto(savedUsuario);
    }

    public Optional<UsuarioDTO> buscarPorCpf(String cpf) {
        return this.usuarioRepository.findByCpf(cpf).map(this::convertDto);
    }

    public void excluir(String cpf) {
        var usuario = this.usuarioRepository.findByCpf(cpf).orElseThrow();
        this.usuarioRepository.delete(usuario);
    }

    public UsuarioDTO atualizar(UsuarioDTO usuarioDto) {
        var usuario = this.usuarioRepository.findByCpf(usuarioDto.getCpf()).orElseThrow();
        var updatedUsuario = new UsuarioBuilder()
                .email(usuario.getEmail())
                .cpf(usuario.getCpf())
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // Assuming password remains unchanged
                .telefone(usuarioDto.getTelefone())
                .roles(usuarioDto.getRoles())
                .active(usuario.isActive())
                .accountExpired(usuario.isAccountExpired())
                .accountLocked(usuario.isAccountLocked())
                .credentialsExpired(usuario.isCredentialsExpired())
                .build();
        var savedUsuario = this.usuarioRepository.save(updatedUsuario);
        return this.convertDto(savedUsuario);
    }

    public Usuario getByUsernameEntity(String username) {
        return this.usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new NaoEncontradoException("Usuário não encontrado"));
    }
}
