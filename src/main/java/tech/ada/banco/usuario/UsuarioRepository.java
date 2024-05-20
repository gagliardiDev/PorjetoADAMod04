package tech.ada.banco.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Optional<Usuario> findByCpf(String cpf);

    Optional<Usuario> findByUsername(String username);
}
