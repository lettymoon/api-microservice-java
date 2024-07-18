package ibm.javer.javer.Usuario;

public record UsuarioResponseDTO(Long id, String cpf, String nome, Long telefone, Boolean correntista, Float score_credito, Float saldo_cc) {
    public UsuarioResponseDTO(Usuario usuario){
        this(usuario.getId(), usuario.getCpf(), usuario.getNome(), usuario.getTelefone(), usuario.getCorrentista(), usuario.getScore_credito(), usuario.getSaldo_cc());
    }
}
