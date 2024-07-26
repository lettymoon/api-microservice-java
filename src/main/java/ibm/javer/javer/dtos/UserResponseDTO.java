package ibm.javer.javer.dtos;

import ibm.javer.javer.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private String id;
    private String nome;

    public UserResponseDTO(User usuario) {
        this.id = usuario.getId();
        this.nome = usuario.getNome();
    }
}
