package com.api.clientes.apiusuarios;

import java.util.UUID;

public record UsuarioResponseDTO(UUID id, String nome, String email) {
    public UsuarioResponseDTO(Usuario usuarioCadastrado) {
        this(usuarioCadastrado.getId(), usuarioCadastrado.getNome(), usuarioCadastrado.getEmail());
    }

}
