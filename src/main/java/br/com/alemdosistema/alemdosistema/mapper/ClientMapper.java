package br.com.alemdosistema.alemdosistema.mapper;

import br.com.alemdosistema.alemdosistema.dto.ClientDTO;
import br.com.alemdosistema.alemdosistema.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);

    ClientDTO clientToClientDTO(Client client);
    Client clientDTOToClient(ClientDTO clientDTO);
}
