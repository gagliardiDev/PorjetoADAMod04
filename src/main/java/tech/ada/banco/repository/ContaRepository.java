package tech.ada.banco.repository;

import tech.ada.banco.model.ClientePF;
import tech.ada.banco.model.ContaCorrente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContaRepository extends JpaRepository<ContaCorrente, Long> {

    public Optional<ContaCorrente> findByUuid(UUID uuid);
    public List<ContaCorrente> findByCliente(Optional<ClientePF> cliente);
}
