package br.com.letscode.santander.dto;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RequestCliente {
    private String nome;
    private String email;
    private String senha;
    private Integer agencia;
    private double saldo;
}
