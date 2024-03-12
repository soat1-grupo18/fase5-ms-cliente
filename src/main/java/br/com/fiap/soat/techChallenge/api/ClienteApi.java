package br.com.fiap.soat.techChallenge.api;

import br.com.fiap.soat.techChallenge.api.requests.CadastrarClienteRequest;
import br.com.fiap.soat.techChallenge.controllers.ClienteController;
import br.com.fiap.soat.techChallenge.presenters.ClientePresenter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@Tag(name = "API de Clientes")
public class ClienteApi {

    private final ClienteController clienteController;

    public ClienteApi(ClienteController clienteController) {
        this.clienteController = clienteController;
    }

    @Operation(summary = "Identificar cliente", description = "Retorna um cliente com o CPF informado.")
    @GetMapping("/clientes/{cpf}")
    public ResponseEntity<ClientePresenter> identificarCliente(@PathVariable String cpf) {
        return ResponseEntity.ok(this.clienteController.identificarCliente(cpf));
    }

    @Operation(summary = "Cadastrar cliente", description = "Cadastra um cliente com os dados informados.")
    @PostMapping("/clientes")
    public ResponseEntity<ClientePresenter> cadastrarCliente(@Valid @RequestBody CadastrarClienteRequest cadastrarClienteRequest) {
        return ResponseEntity.ok(this.clienteController.cadastrarCliente(cadastrarClienteRequest.toDomain()));
    }

    @Operation(summary = "Remover cliente", description = "Remove cliente informado.")
    @DeleteMapping("/clientes/{id}")
    public ResponseEntity<ClientePresenter> removerCliente(@PathVariable UUID id) {
        this.clienteController.removerCliente(id);
        return ResponseEntity.status(204).build();
    }
}
