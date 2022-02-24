package br.com.letscode.santander.controler;

import br.com.letscode.santander.dto.RequestCliente;
import br.com.letscode.santander.dto.RequestDeposito;
import br.com.letscode.santander.dto.ResponseCliente;
import br.com.letscode.santander.model.BDClientes;
import br.com.letscode.santander.model.ClienteModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

//Eu trocando esse Controller por RestController pode retirar todos os ResponseBody
@Controller
//Com esse RequestMapping unifica o path e você trata por método(get ou post...)
@RequestMapping("/clientes")
public class ClienteController {
    BDClientes bdClientes = new BDClientes();

    @GetMapping
    @ResponseBody
    public List<ResponseCliente> clientes(){
        return ResponseCliente.toResponse(bdClientes.buscaClientes());
    }

//    @GetMapping
//    public String cadastroClientes(){
//        return "cadastroCliente";
//    }

    @PostMapping
    @ResponseBody
    public ResponseEntity<ResponseCliente> cadastrarCliente(@RequestBody RequestCliente requestCliente, UriComponentsBuilder uriComponentsBuilder){
        List<Conta> contaList = new ArrayList<>();
        Conta conta = new Conta(Tipo_Conta.CONTA_CORRENTE);
        contaList.add(conta);
        ClienteModel cliente = new ClienteModel(UUID.randomUUID(), requestCliente.getNome(), requestCliente.getEmail(), requestCliente.getSenha(), requestCliente.getSaldo(), contaList);
        bdClientes.adiciona(cliente);
        URI uri = uriComponentsBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).body(new ResponseCliente(cliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseCliente> detalhesCliente(@PathVariable UUID id) throws Exception{
        ClienteModel cliente = bdClientes.detalhesCliente(id);
        return ResponseEntity.ok(new ResponseCliente(cliente));
    }

    @PutMapping ("/{id}")
    public ResponseEntity<ResponseCliente> atualizaCliente(@PathVariable UUID id, @RequestBody RequestCliente requestCliente) throws Exception {
        ClienteModel cliente = bdClientes.atualizaCliente(id, requestCliente);
        return ResponseEntity.ok(new ResponseCliente(cliente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteCliente(@PathVariable UUID id) throws Exception {
        bdClientes.deletaCliente(id);
        return ResponseEntity.ok().build();
    }
}
