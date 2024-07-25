package ibm.javer.javer.repositories;

import ibm.javer.javer.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Query(value = "SELECT * FROM users WHERE cpf = :cpf", nativeQuery = true)
    Optional<User> findByCpf(@Param("cpf") String cpf);

    @Query(value = "SELECT * FROM users WHERE id = :id", nativeQuery = true)
    Optional<User> findById(@Param("id") String id);
}
