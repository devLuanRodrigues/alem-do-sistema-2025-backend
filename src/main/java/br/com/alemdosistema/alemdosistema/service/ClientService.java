package br.com.alemdosistema.alemdosistema.service;

import br.com.alemdosistema.alemdosistema.model.Client;
import br.com.alemdosistema.alemdosistema.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Validated
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> listAllClients() {
        return clientRepository.findAll();
    }

    public Optional<Client> findClientById(UUID id) {
        return clientRepository.findById(id);
    }

    public List<Client> findClientByNameOrCpf(String nome, String cpf) {
        List<Client> clients = new ArrayList<>();
        if (nome != null && !nome.isEmpty()) {
            clients.addAll(clientRepository.findByNome(nome).stream().toList());
        }
        if (cpf != null && !cpf.isEmpty()) {
            clientRepository.findByCpf(cpf).ifPresent(clients::add);
        }
        return clients;
    }

    public Client createClient(@Valid Client client) {
        validateClient(client);
        if (clientRepository.findByCpf(client.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado! " + client.getCpf());
        }
        if (client.getContato() == null) {
            client.setContato(new ArrayList<>());
        }
        client.getContato().forEach(contato -> contato.setClient(client));
        return clientRepository.save(client);
    }

    public Client updateClient(UUID id, @Valid Client client) {
        validateClient(client);
        Client existingClient = clientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));

        existingClient.setNome(client.getNome());
        existingClient.setCpf(client.getCpf());
        existingClient.setDataNascimento(client.getDataNascimento());
        existingClient.setEndereco(client.getEndereco());

        existingClient.getContato().clear();
        existingClient.getContato().addAll(client.getContato());
        existingClient.getContato().forEach(contato -> contato.setClient(existingClient));

        return clientRepository.save(existingClient);
    }

    public void deleteClient(UUID id) {
        clientRepository.deleteById(id);
    }

    private void validateClient(Client client) {
        if (client.getNome() == null || client.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é obrigatório!");
        }
        if (client.getCpf() == null || client.getCpf().trim().isEmpty()) {
            throw new IllegalArgumentException("CPF é obrigatório!");
        }
        if (!isValidCpf(client.getCpf())) {
            throw new IllegalArgumentException("CPF inválido!");
        }
        if (client.getDataNascimento() != null && client.getDataNascimento().isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("A data de nascimento deve ser uma data passada.");
        }
    }

    private boolean isValidCpf(String cpf) {
        return cpf.matches("\\d{11}");
    }
}