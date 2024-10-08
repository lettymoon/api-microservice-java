package ibm.javer.javer.domain.user;

import ibm.javer.javer.dtos.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "users")
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private String id;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private String telefone;

    @Column(name = "correntista")
    private Boolean correntista;

    @Column(name = "score_credito")
    private Double score_credito;

    @Column(name = "saldo_cc")
    private Double saldo_cc;

    public User(UserRequestDTO userRequest) {
        this.cpf = userRequest.getCpf();
        this.nome = userRequest.getNome();
        this.telefone = userRequest.getTelefone();
        this.correntista = userRequest.getCorrentista();
        this.score_credito = 0.0;
        this.saldo_cc = userRequest.getSaldo_cc();
    }
}
