package tech.ada.banco.repository;

import tech.ada.banco.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteRepository<T extends Cliente> extends JpaRepository<T, Long> {

    public Optional<T> findByUuid(UUID uuid);
}
