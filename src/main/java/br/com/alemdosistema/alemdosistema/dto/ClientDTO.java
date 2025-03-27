package br.com.alemdosistema.alemdosistema.dto;

import br.com.alemdosistema.alemdosistema.model.Contact;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class ClientDTO {
    private UUID id;

    @NotBlank(message = "Nome é obrigatório!")
    private String nome;

    @NotBlank(message = "CPF é obrigatório!")
    private String cpf;
    private Date dataNascimento;
    private String endereco;
    private List<Contact> contato = new ArrayList<>();

    public ClientDTO(UUID id, String nome, String cpf, Date dataNascimento, String endereco) {
        setId(id);
        setNome(nome);
        setCpf(cpf);
        setDataNascimento(dataNascimento);
        setEndereco(endereco);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public List<Contact> getContato() {
        return contato;
    }

    public void setContato(List<Contact> contato) {
        this.contato = contato;
    }
}
