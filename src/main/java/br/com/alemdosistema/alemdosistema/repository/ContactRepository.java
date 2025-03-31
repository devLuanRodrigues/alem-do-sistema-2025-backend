package br.com.alemdosistema.alemdosistema.repository;

import br.com.alemdosistema.alemdosistema.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}
