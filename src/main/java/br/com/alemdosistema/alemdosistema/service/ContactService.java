package br.com.alemdosistema.alemdosistema.service;

import br.com.alemdosistema.alemdosistema.model.Contact;
import br.com.alemdosistema.alemdosistema.repository.ContactRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public List<Contact> listAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> findById(Long id) {
        return contactRepository.findById(id);
    }

    public Contact createContact(Contact contact) {
        return contactRepository.save(contact);
    }

    public Contact updateContact(Long id, @Valid Contact contact) {
        Contact existingContact = contactRepository.findById(contact.getId())
                .orElseThrow(() -> new IllegalArgumentException("Contato não encontrado com ID: " + contact.getId()));

        existingContact.setTipoContato(contact.getTipoContato());
        existingContact.setValorContato(contact.getValorContato());
        existingContact.setObservacao(contact.getObservacao());

        return contactRepository.save(existingContact);
    }

    public void deleteContact(Long id) {
        contactRepository.deleteById(id);
    }
}
