package school.sptech.prova_ac1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.prova_ac1.dto.UsuarioDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    public List<Usuario> buscarTodos() {
        return usuarioRepository.findAll();
    }

    public UsuarioDTO criar(UsuarioDTO usuarioDTO) {
        Usuario u = new Usuario();
        System.out.println(usuarioDTO.getNome());
        u.setNome(usuarioDTO.getNome());
        u.setEmail(usuarioDTO.getEmail());
        u.setSenha(usuarioDTO.getSenha());
        u.setDataNascimento(usuarioDTO.getDataNascimento());

        u = usuarioRepository.save(u);
        return new UsuarioDTO(u);
    }

    public Optional<Usuario> buscarPorId(Long id) {
        if(usuarioRepository.existsById(id)) {
        return usuarioRepository.findById(id);
        }
        throw new IllegalArgumentException("id null");
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public List<Usuario> buscarPorDataNascimento(LocalDate nascimento) {
        return usuarioRepository.findByDataNascimento(nascimento);
    }

    public UsuarioDTO atualizar(Long id, UsuarioDTO usuario) {
        Usuario user = usuarioRepository.getReferenceById(id);
        user.setNome(usuario.getNome());
        user.setEmail(usuario.getEmail());
        user.setDataNascimento(usuario.getDataNascimento());
        user.setSenha(usuario.getSenha());

        user = usuarioRepository.save(user);
        return new UsuarioDTO(user);

    }
}
