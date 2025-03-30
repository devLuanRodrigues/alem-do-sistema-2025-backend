package br.com.alemdosistema.alemdosistema.repository;

import br.com.alemdosistema.alemdosistema.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findById(Long id);
    List<Client> findByNome(String nome);

    @Query("SELECT c FROM Client c WHERE c.cpf = ?1")
    Optional<Client> findByCpf(String cpf);
}
