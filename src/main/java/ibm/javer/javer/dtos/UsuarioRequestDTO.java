package ibm.javer.javer.dtos;

import jakarta.validation.constraints.*;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class UsuarioRequestDTO {
    @NotNull(message = "O campo cpf não pode ser nulo")
    @NotBlank(message = "O campo cpf é obrigatório")
    @Size(min = 11, max = 11, message = "O campo cpf é inválido")
    String cpf;

    @NotNull(message = "O campo nome não pode ser nulo")
    @NotEmpty(message = "O campo nome é obrigatório")
    @Pattern(regexp = "^[A-Za-zÀ-ÿ\\s]+$", message = "O campo nome deve conter apenas letras")
    String nome;

    @NotNull(message = "O campo telefone não pode ser nulo")
    @Size(min = 11, max = 11, message = "O campo telefone deve ter 11 digitos refletindo o formado (XX) 9XXXX-XXXX")
    @NotEmpty(message = "O campo telefone é obrigatório")
    String telefone;

    @NotNull(message = "O campo correntista é obrigatório")
    Boolean correntista;

    @Min(value = 0, message = "O valor deve ser maior ou igual a zero")
    Float saldo_cc;
}
