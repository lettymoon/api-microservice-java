package ibm.javer.javer.dtos;

import ibm.javer.javer.domain.user.User;

public record UserByIdResponseDTO(String id, String cpf, String nome, String telefone, Boolean correntista, Double score_credito, Double saldo_cc) {
    public UserByIdResponseDTO(User usuario){
        this(usuario.getId(), usuario.getCpf(), usuario.getNome(), usuario.getTelefone(), usuario.getCorrentista(), usuario.getScore_credito(), usuario.getSaldo_cc());
    }
}