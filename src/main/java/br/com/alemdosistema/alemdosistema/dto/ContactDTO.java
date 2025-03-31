package br.com.alemdosistema.alemdosistema.dto;

import br.com.alemdosistema.alemdosistema.enums.ContactType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ContactDTO {
    private Long id;

    @NotNull(message = "O tipo de contato é obrigatório!")
    private ContactType tipoContato;

    @NotBlank(message = "O valor (informação) de contato é obrigatório!")
    private String valorContato;

    private String observacao;

    public ContactDTO(Long id, ContactType tipoContato, String valorContato, String observacao) {
        setId(id);
        setTipoContato(tipoContato);
        setValorContato(valorContato);
        setObservacao(observacao);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ContactType getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(ContactType tipoContato) {
        this.tipoContato = tipoContato;
    }

    public String getValorContato() {
        return valorContato;
    }

    public void setValorContato(String valorContato) {
        this.valorContato = valorContato;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
