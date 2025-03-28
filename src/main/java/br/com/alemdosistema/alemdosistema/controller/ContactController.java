package br.com.alemdosistema.alemdosistema.controller;

import br.com.alemdosistema.alemdosistema.dto.ContactDTO;
import br.com.alemdosistema.alemdosistema.mapper.ContactMapper;
import br.com.alemdosistema.alemdosistema.model.Client;
import br.com.alemdosistema.alemdosistema.model.Contact;
import br.com.alemdosistema.alemdosistema.service.ClientService;
import br.com.alemdosistema.alemdosistema.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients/{clientId}/contacts")
public class ContactController {
    @Autowired
    private ContactService contactService;

    @Autowired
    private ClientService clientService;

    @GetMapping
    public List<ContactDTO> listAllContacts() {
        return contactService.listAllContacts().stream()
                .map(ContactMapper.INSTANCE::contactToContactDTO)
                .toList();
    }

    @PostMapping
    public ResponseEntity<ContactDTO> createContact(@PathVariable UUID clientId, @Valid @RequestBody ContactDTO contactDTO) {
        Contact contact = ContactMapper.INSTANCE.contactDTOToContact(contactDTO);

        Client client = clientService.findClientById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado com o ID: " + clientId));

        contact.setClient(client);
        Contact createdContact = contactService.createContact(contact);
        return ResponseEntity.status(201).body(ContactMapper.INSTANCE.contactToContactDTO(createdContact));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactDTO> updateContact(@PathVariable Long clientId, @PathVariable Long id,@Valid @RequestBody ContactDTO contactDTO) {
        Contact contact = ContactMapper.INSTANCE.contactDTOToContact(contactDTO);
        contact.setId(id);

        Contact updatedContact = contactService.updateContact(id, contact);
        return ResponseEntity.ok(ContactMapper.INSTANCE.contactToContactDTO(updatedContact));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable UUID clientId, @PathVariable Long id) {
        Contact contact = contactService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado com ID: " + id));

        if (!contact.getClient().getId().equals(clientId)) {
            throw new IllegalArgumentException("Contato não pertence ao cliente com ID: " + clientId);
        }

        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}
