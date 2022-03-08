package br.com.letscode.santander.service;

import br.com.letscode.santander.SantanderApplication;
import br.com.letscode.santander.controler.Conta;
import br.com.letscode.santander.controler.Tipo_Conta;
import br.com.letscode.santander.dto.RequestCliente;
import br.com.letscode.santander.model.ClienteModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

//O service serve para adicionarmos o tratamento/lógica da operação, deixando o controller apenas recebendo os requests
//e devolvendo a response.
public class ClienteService {

    public ClienteModel cadastraCliente(RequestCliente requestCliente) {
        List<Conta> contaList = new ArrayList<>();
        Conta conta = new Conta(Tipo_Conta.CONTA_CORRENTE);
        contaList.add(conta);
        ClienteModel cliente = new ClienteModel(UUID.randomUUID(), requestCliente.getNome(), requestCliente.getEmail(), requestCliente.getSenha(), requestCliente.getSaldo(), contaList);
        SantanderApplication.bdClientes.adiciona(cliente);
        return cliente;
    }

    public List<ClienteModel> buscaTodosClientes(){
        return SantanderApplication.bdClientes.buscaClientes();
    }

    public ClienteModel detalhesClientes(UUID id) throws Exception {
        return SantanderApplication.bdClientes.detalhesCliente(id);
    }

    public ClienteModel atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception {
        return SantanderApplication.bdClientes.atualizaCliente(id, requestCliente);
    }
}
