package ibm.javer.javer.service.dto;

import ibm.javer.javer.entity.Usuario;

public record UsuarioResponseDTO(String id, String nome) {
    public UsuarioResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome());
    }
}
