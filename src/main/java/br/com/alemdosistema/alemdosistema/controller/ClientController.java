package br.com.alemdosistema.alemdosistema.controller;


import br.com.alemdosistema.alemdosistema.dto.ClientDTO;
import br.com.alemdosistema.alemdosistema.mapper.ClientMapper;
import br.com.alemdosistema.alemdosistema.model.Client;
import br.com.alemdosistema.alemdosistema.repository.ClientRepository;
import br.com.alemdosistema.alemdosistema.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<ClientDTO> listAllClients() {
        return clientService.listAllClients().stream()
                .map(ClientMapper.INSTANCE::clientToClientDTO)
                .toList();
    }

    @GetMapping("/{id}")
    public String getClientById(@PathVariable UUID id, Model model) {
        Client client = clientService.findClientById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com ID: " + id));
        model.addAttribute("client", ClientMapper.INSTANCE.clientToClientDTO(client));
        return "profile";
    }

    @GetMapping("/search")
    public ResponseEntity<List<ClientDTO>> findClientByNameOrCpf(@RequestParam(required = false) String nome, @RequestParam(required = false) String cpf) {
        List<Client> clients = clientService.findClientByNameOrCpf(nome, cpf);
        return ResponseEntity.ok(clients.stream()
                .map(ClientMapper.INSTANCE::clientToClientDTO)
                .toList());
    }

    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@Valid @RequestBody ClientDTO clientDTO) {
        Client client = ClientMapper.INSTANCE.clientDTOToClient(clientDTO);
        Client createdClient = clientService.createClient(client);
        return ResponseEntity.status(201).body(ClientMapper.INSTANCE.clientToClientDTO(createdClient));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable UUID id, @Valid @RequestBody ClientDTO clientDTO) {
        Client client = ClientMapper.INSTANCE.clientDTOToClient(clientDTO);
        Client updatedClient = clientService.updateClient(id, client);
        return ResponseEntity.status(201).body(ClientMapper.INSTANCE.clientToClientDTO(updatedClient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
}
