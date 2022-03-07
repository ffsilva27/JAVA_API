package br.com.letscode.santander.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor
public class ErroValidacao {
    private String campo;
    private String messagem;
}
