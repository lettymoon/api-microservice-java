package ibm.javer.javer.repositories;

import ibm.javer.javer.domain.user.User;
import ibm.javer.javer.dtos.UserRequestDTO;
import ibm.javer.javer.dtos.UserResponseDTO;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    EntityManager entityManager;

    @Test
    @DisplayName("Deve obter o usuário com sucesso no BD")
    void findByCpfCase1() {
        String cpf = "12345678910";
        UserRequestDTO data = new UserRequestDTO(cpf, "Letícia", "11916725678", true, 1000.0);
        this.createUser(data);

        Optional<User> result = this.userRepository.findByCpf(cpf);

        assertThat(result.isPresent()).isTrue();
    }

    @Test
    @DisplayName("Deve retornar que o usuário não existe")
    void findByCpfCase2() {
        String document = "99999999901";

        Optional<User> result = this.userRepository.findByCpf(document);

        assertThat(result.isEmpty()).isTrue();
    }

    private User createUser(UserRequestDTO data){
        User newUser = new User(data);
        this.entityManager.persist(newUser);
        return newUser;
    }
}