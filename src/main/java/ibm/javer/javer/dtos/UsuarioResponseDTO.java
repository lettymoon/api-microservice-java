package ibm.javer.javer.dtos;

import ibm.javer.javer.domain.user.User;

public record UsuarioResponseDTO(String id, String nome) {
    public UsuarioResponseDTO(User usuario){
        this(usuario.getId(), usuario.getNome());
    }
}
