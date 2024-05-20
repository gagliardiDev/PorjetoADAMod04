package tech.ada.banco.usuario;

import lombok.*;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UsuarioDTO {

    @NotBlank(message = "Nome obrigatório")
    private String nome;
    @Email(message = "email invalido")
    private String email;
    @CPF(message = "CPF inválido")
    @NotBlank(message = "CPF obrigatório")
    private String cpf;
    private String telefone;
    @NotBlank(message = "Username obrigatório")
    private String username;
    @NotBlank(message = "Password obrigatória")
    private String password;
    @NotBlank(message = "Role obrigatória")
    private String roles;

}
