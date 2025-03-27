package br.com.alemdosistema.alemdosistema.service;

import br.com.alemdosistema.alemdosistema.model.Client;
import br.com.alemdosistema.alemdosistema.repository.ClientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

    public Client createClient(@Valid Client client){

        if (clientRepository.findByCpf(client.getCpf()).isPresent()) {
            throw new IllegalArgumentException("CPF já cadastrado! " + client.getCpf());
        }

        if (client.getContato() == null) {
            client.setContato(new ArrayList<>());
        }

        client.getContato().forEach(contato -> contato.setClient(client));
        return clientRepository.save(client);
    }

    public Client updateClient(UUID id, @Valid Client client){
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
}
