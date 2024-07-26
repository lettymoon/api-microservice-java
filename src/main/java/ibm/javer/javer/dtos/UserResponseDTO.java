package ibm.javer.javer.dtos;

import ibm.javer.javer.domain.user.User;

public record UserResponseDTO(String id, String nome) {
    public UserResponseDTO(User usuario){
        this(usuario.getId(), usuario.getNome());
    }
}
