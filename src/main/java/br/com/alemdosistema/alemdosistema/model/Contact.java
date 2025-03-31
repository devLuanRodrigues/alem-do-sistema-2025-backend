package br.com.alemdosistema.alemdosistema.model;

import br.com.alemdosistema.alemdosistema.enums.ContactType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "contato")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoContato")
    private ContactType tipoContato;

    private String valorContato;
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Client client;

    public Contact(){}

    public Contact(Long id, ContactType tipoContato, String valorContato, String observacao) {
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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
