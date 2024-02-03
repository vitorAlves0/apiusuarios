package com.api.clientes.apiusuarios;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column
    private UUID id;
    @Column
    private String nome;
    @Column
    private String email;

    public Usuario() {
    }

    public Usuario(UUID id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }

    public Usuario(UsuarioRequestDTO usuarioDto) {
        this.nome = usuarioDto.nome();
        this.email = usuarioDto.email();
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void atualizarInformacoes(UsuarioAtualizarDTO atualizarDto) {
        if (atualizarDto.nome() != null) {
            this.nome = atualizarDto.nome();
        }
        if (atualizarDto.email() != null) {
            this.email = atualizarDto.email();
        }
    }
}
