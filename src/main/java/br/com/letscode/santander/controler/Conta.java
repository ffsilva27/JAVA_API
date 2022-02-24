package br.com.letscode.santander.controler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

@Getter
@Setter

public class Conta {
    private int numeroConta;
    private int agencia;
    private Tipo_Conta tipoConta;
    private double saldo;

    public Conta(Tipo_Conta tipoConta) {
        this.numeroConta = gerarConta();
        this.agencia = 001;
        this.tipoConta = tipoConta;
    }

    public int gerarConta(){

        Random random = new Random();
        int numero = random.nextInt(9999);
        return numero;
    }
}
