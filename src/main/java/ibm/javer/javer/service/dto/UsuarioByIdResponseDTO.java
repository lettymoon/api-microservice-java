package ibm.javer.javer.service.dto;

import ibm.javer.javer.entity.Usuario;

public record UsuarioByIdResponseDTO(String id, String cpf, String nome, Long telefone, Boolean correntista, Float score_credito, Float saldo_cc) {
    public UsuarioByIdResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getCpf(), usuario.getNome(), usuario.getTelefone(), usuario.getCorrentista(), usuario.getScore_credito(), usuario.getSaldo_cc());
    }
}
