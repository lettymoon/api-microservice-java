package ibm.javer.javer.Usuario;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cpf", unique = true)
    private String cpf;

    @Column(name = "nome")
    private String nome;

    @Column(name = "telefone")
    private Long telefone;

    @Column(name = "correntista")
    private Boolean correntista;

    @Column(name = "score_credito")
    private Float score_credito;

    @Column(name = "saldo_cc")
    // TODO: Definir valor default
    private Float saldo_cc;

    public Usuario(UsuarioRequestDTO data) {
        this.cpf = data.cpf();
        this.nome = data.nome();
        this.telefone = data.telefone();
        this.correntista = data.correntista();
    }
}
