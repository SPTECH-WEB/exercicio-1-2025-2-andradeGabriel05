package school.sptech.prova_ac1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import school.sptech.prova_ac1.entities.Usuario;
import school.sptech.prova_ac1.services.UsuarioService;
import school.sptech.prova_ac1.dto.UsuarioDTO;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodos() {
        List<Usuario> usuarios = usuarioService.buscarTodos();
        if(usuarios.size() == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> criar(@RequestBody UsuarioDTO usuarioDTO) {
        usuarioDTO = usuarioService.criar(usuarioDTO);

        if(usuarioDTO == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO).toUri();

        return ResponseEntity.created(uri).body(usuarioDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Usuario>> buscarPorId(@PathVariable Integer id) {
        Optional<Usuario> usuario = usuarioService.buscarPorId(id);
        if(usuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        Boolean response = usuarioService.deletar(id);

        if(response) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/filtro-data")
    public ResponseEntity<List<Usuario>> buscarPorDataNascimento(@RequestParam LocalDate nascimento) {
        if(nascimento == null) {
            return ResponseEntity.badRequest().build();
        }

        List<Usuario> user = usuarioService.buscarPorDataNascimento(nascimento);
        System.out.println("Found " + user.size() + " users");
        if(user.isEmpty()){
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> atualizar(
            @PathVariable Integer id,
            @RequestBody UsuarioDTO usuario
    ) {
        usuario = usuarioService.atualizar(id, usuario);


        if(usuario == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        return ResponseEntity.ok(usuario);
    }
}
