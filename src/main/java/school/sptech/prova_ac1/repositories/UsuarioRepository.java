package school.sptech.prova_ac1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.prova_ac1.entities.Usuario;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByCpf(String cpf);

    Usuario findByEmail(String email);

    Boolean existsByEmail(String email);

    Boolean existsByCpf(String cpf);

    List<Usuario> findByDataNascimentoGreaterThan(LocalDate dataNascimento);

    boolean existsByCpfLikeOrEmailLike(String cpf, String email);
}
