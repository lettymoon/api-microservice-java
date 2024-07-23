package ibm.javer.javer.repository;

import ibm.javer.javer.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UsuariosRepository extends JpaRepository<Usuario, String> {

    @Query(value = "SELECT * FROM usuarios WHERE cpf = :cpf", nativeQuery = true)
    Optional<Usuario> findByCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT * FROM usuarios WHERE id = :id", nativeQuery = true)
    Optional<Usuario> findById(@Param("id") String id);
}
