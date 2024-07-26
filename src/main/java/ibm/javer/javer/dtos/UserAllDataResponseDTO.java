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
public class UserAllDataResponseDTO {
    private String id;
    private String cpf;
    private String nome;
    private String telefone;
    private Boolean correntista;
    private Double score_credito;
    private Double saldo_cc;

    public UserAllDataResponseDTO(User usuario) {
        this.id = usuario.getId();
        this.cpf = usuario.getCpf();
        this.nome = usuario.getNome();
        this.telefone = usuario.getTelefone();
        this.correntista = usuario.getCorrentista();
        this.score_credito = usuario.getScore_credito();
        this.saldo_cc = usuario.getSaldo_cc();
    }
}
