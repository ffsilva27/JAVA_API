package br.com.letscode.santander.controler;

import br.com.letscode.santander.SantanderApplication;
import br.com.letscode.santander.dto.RequestDeposito;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/operacoes")
public class OperacoesController {


    @PatchMapping("/deposita")
    public ResponseEntity deposita(@RequestHeader UUID id, @RequestBody RequestDeposito requestDeposito) throws Exception {
        SantanderApplication.bdClientes.deposita(id, requestDeposito);
        return ResponseEntity.ok().build();
    }
}
