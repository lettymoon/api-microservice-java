package ibm.javer.javer.entity;

import ibm.javer.javer.service.dto.UsuarioRequestDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Usuario {

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
    private Float saldo_cc;

    public Usuario(UsuarioRequestDTO data) {
        this.cpf = data.getCpf();
        this.nome = data.getNome();
        this.telefone = data.getTelefone();
        this.correntista = data.getCorrentista();
        this.score_credito = (float) 0;
        this.saldo_cc = (float) 0;
    }
}
