package com.api.clientes.apiusuarios;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(@RequestBody UsuarioRequestDTO usuarioDto) {
        Usuario usuarioCadastrado = new Usuario(usuarioDto);
        repository.save(usuarioCadastrado);
        return ResponseEntity.status(HttpStatus.CREATED).body(new UsuarioResponseDTO(usuarioCadastrado));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listar() {
        var lista = repository.findAll().stream().map(UsuarioResponseDTO::new).toList();
        return ResponseEntity.status(HttpStatus.OK).body(lista);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable UUID id,
            @RequestBody UsuarioAtualizarDTO atualizarDto) {
        Usuario usuarioEditar = repository.getReferenceById(id);
        if (usuarioEditar == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        usuarioEditar.atualizarInformacoes(atualizarDto);
        repository.save(usuarioEditar);
        return ResponseEntity.status(HttpStatus.OK).body(new UsuarioResponseDTO(usuarioEditar));

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluir(@PathVariable UUID id) {
        Usuario usuarioExcluir = repository.getReferenceById(id);
        if (usuarioExcluir == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        repository.delete(usuarioExcluir);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
