package br.com.letscode.santander.model;

import br.com.letscode.santander.dto.RequestCliente;

import java.util.*;

public class BDClientes {
    private static List<ClienteModel> clientes = new ArrayList<>();

    public void adiciona(ClienteModel cliente){
        clientes.add(cliente);
    }

    public List<ClienteModel> buscaClientes(){
        return clientes;
    }

    public ClienteModel detalhesCliente(UUID id) throws Exception{
        Optional<ClienteModel> resultClienteModel = BDClientes.clientes.stream().filter(clientes -> Objects.equals(clientes.getId(),id)).findAny();
        if(resultClienteModel.isPresent()){
            return resultClienteModel.get();
        }else {
            throw new Exception("Usuário não encontrado");
        }
    }

    public ClienteModel atualizaCliente(UUID id, RequestCliente requestCliente) throws Exception {
            BDClientes.clientes.stream().filter(clientes -> Objects.equals(clientes.getId(),id)).forEach(clientes -> {
            clientes.setNome(requestCliente.getNome());
            clientes.setEmail(requestCliente.getEmail());
            clientes.setSenha(requestCliente.getSenha());
        });
            return detalhesCliente(id);
    }

    public void deletaCliente(UUID id) throws Exception {
        //ClienteModel clienteModel = (ClienteModel) BDClientes.clientes.stream().filter(cliente -> cliente.getId() == id);
        ClienteModel clienteModel = detalhesCliente(id);
        BDClientes.clientes.remove(clienteModel);
    }
}
