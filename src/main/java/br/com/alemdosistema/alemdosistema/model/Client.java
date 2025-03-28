package br.com.alemdosistema.alemdosistema.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cliente")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Column(unique = true)
    private String cpf;

    private LocalDate dataNascimento;
    private String endereco;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Contact> contato = new ArrayList<>();

    public Client(){}

    public Client(UUID id, String nome, String cpf, LocalDate dataNascimento, String endereco) {
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

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
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
