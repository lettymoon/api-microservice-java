package ibm.javer.javer.dtos;

import ibm.javer.javer.domain.user.User;

public record UsuarioByIdResponseDTO(String id, String cpf, String nome, String telefone, Boolean correntista, Float score_credito, Float saldo_cc) {
    public UsuarioByIdResponseDTO(User usuario){
        this(usuario.getId(), usuario.getCpf(), usuario.getNome(), usuario.getTelefone(), usuario.getCorrentista(), usuario.getScore_credito(), usuario.getSaldo_cc());
    }
}