package ibm.javer.javer.domain.user;

import ibm.javer.javer.dtos.UsuarioRequestDTO;
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
    private Float score_credito;

    @Column(name = "saldo_cc")
    private Float saldo_cc = 0.0f;

    public User(UsuarioRequestDTO data) {
        this.cpf = data.getCpf();
        this.nome = data.getNome();
        this.telefone = data.getTelefone();
        this.correntista = data.getCorrentista();
        this.score_credito = (float)0;
        this.saldo_cc = data.getSaldo_cc();
    }
}
