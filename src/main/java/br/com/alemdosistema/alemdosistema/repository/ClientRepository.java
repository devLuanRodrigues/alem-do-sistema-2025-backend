package br.com.alemdosistema.alemdosistema.repository;

import br.com.alemdosistema.alemdosistema.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    Optional<Client> findById(UUID id);
    Optional<Client> findByNome(String nome);

    @Query("SELECT c FROM Client c WHERE c.cpf = ?1")
    Optional<Client> findByCpf(String cpf);
}
