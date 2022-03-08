package br.com.letscode.santander.service;


import br.com.letscode.santander.dto.RequestCliente;
import br.com.letscode.santander.model.ClienteModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.fail;

public class ClienteServiceTest {
    ClienteService clienteService = new ClienteService();
    @Test
    public void deveRetornarListaVazia(){
        List<ClienteModel> clientes = clienteService.buscaTodosClientes();
        Assertions.assertFalse(clientes.isEmpty());
    }

    @Test
    public void cadastraCliente(){
        RequestCliente requestCliente = new RequestCliente();
        requestCliente.setNome("Teste");
        requestCliente.setEmail("teste@teste.com.br");
        requestCliente.setCpf("12345678912");
        requestCliente.setSenha("1234");

        ClienteModel clienteModel = clienteService.cadastraCliente(requestCliente);
        Assertions.assertEquals(clienteModel.getNome(), requestCliente.getNome());
        Assertions.assertNotNull(clienteModel.getId());
    }

    @Test
    public void atualizaCliente() throws Exception {
        RequestCliente requestCliente = new RequestCliente();
        requestCliente.setNome("Teste");
        requestCliente.setEmail("teste@teste.com.br");
        requestCliente.setCpf("12345678912");
        requestCliente.setSenha("1234");

        ClienteModel clienteModel = clienteService.cadastraCliente(requestCliente);

        RequestCliente requestClienteAtualizado = new RequestCliente();
        requestClienteAtualizado.setNome("Teste2");
        requestClienteAtualizado.setEmail("teste2@teste.com.br");
        requestClienteAtualizado.setCpf("12345678912");
        requestClienteAtualizado.setSenha("1234");
        ClienteModel clienteAtualizado = clienteService.atualizaCliente(clienteModel.getId(), requestClienteAtualizado);

        Assertions.assertEquals("Teste2", clienteAtualizado.getNome());
    }

    @Test
    public void buscarClienteQueNaoExiste(){
        UUID id = UUID.randomUUID();
        Assertions.assertThrows(Exception.class, () -> clienteService.detalhesClientes(id));
    }

    @Test
    public void buscarClienteQueNaoExiste2(){
        UUID id = UUID.randomUUID();
        try{
            clienteService.detalhesClientes(id);
            fail("Não lançou a exceção.");
        }catch (Exception e){ }
    }
}
