package br.com.letscode.santander.dto;
import br.com.letscode.santander.util.CPF;
import br.com.letscode.santander.util.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class RequestCliente {
    @NotNull(message = "nao pode ser null vacilao") @NotEmpty @Length(min = 2)
    private String nome;
    @Email
    private String email;
    @CPF
    private String cpf;
    private String senha;
    private Integer agencia;
    private double saldo;
}
