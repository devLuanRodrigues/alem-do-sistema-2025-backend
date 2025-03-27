package br.com.alemdosistema.alemdosistema.mapper;

import br.com.alemdosistema.alemdosistema.dto.ClientDTO;
import br.com.alemdosistema.alemdosistema.dto.ContactDTO;
import br.com.alemdosistema.alemdosistema.model.Client;
import br.com.alemdosistema.alemdosistema.model.Contact;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ContactMapper {
    ContactMapper INSTANCE = Mappers.getMapper(ContactMapper.class);

    ContactDTO contactToContactDTO(Contact contact);
    Contact contactDTOToContact(ContactDTO contactDTO);

}
