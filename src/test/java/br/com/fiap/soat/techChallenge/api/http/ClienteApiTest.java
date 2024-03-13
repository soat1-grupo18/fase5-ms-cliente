package br.com.fiap.soat.techChallenge.api.http;

import br.com.fiap.soat.techChallenge.api.http.ClienteApi;
import br.com.fiap.soat.techChallenge.api.http.requests.CadastrarClienteRequest;
import br.com.fiap.soat.techChallenge.config.CoreExceptionsAdvicer;
import br.com.fiap.soat.techChallenge.controllers.ClienteController;
import br.com.fiap.soat.techChallenge.entities.Cliente;
import br.com.fiap.soat.techChallenge.presenters.ClientePresenter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class ClienteApiTest {

    private MockMvc mockMvc;

    @Mock
    private ClienteController clienteController;

    @BeforeEach
    void setUp() {
        ClienteApi clienteApi = new ClienteApi(clienteController);
        mockMvc = MockMvcBuilders.standaloneSetup(clienteApi)
                .setControllerAdvice(new CoreExceptionsAdvicer())
                .build();
    }

    @Test
    void identificarCliente() throws Exception {
        var cpf = "12345678900";

        Cliente cliente = new Cliente("Nome do Cliente", cpf, "32955557777");

        when(clienteController.identificarCliente(cpf))
                .thenAnswer(i -> ClientePresenter.fromDomain(cliente));

        mockMvc.perform(get("/clientes/{cpf}", cpf))
                .andExpect(status().isOk());

        verify(clienteController, times(1)).identificarCliente(cpf);
    }

    @Test
    void cadastrarCliente() throws Exception {
        var request = new CadastrarClienteRequest();
        request.setNome("Nome do Cliente");
        request.setCpf("12345678900");
        request.setTelefone("32955557777");

        var cliente = new Cliente("Nome do Cliente", "12345678900", "32955557777");

        when(clienteController.cadastrarCliente(any(Cliente.class)))
                .thenAnswer(i -> ClientePresenter.fromDomain(cliente));

        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(request)))
                .andExpect(status().isOk());

        verify(clienteController, times(1)).cadastrarCliente(any());
    }

    @Test
    void removerCliente() throws Exception {
        Cliente cliente = new Cliente("Nome do Cliente", "12345678900", "32955557777");
        cliente.setId(UUID.randomUUID());

        doNothing().when(clienteController).removerCliente(cliente.getId());
    
        mockMvc.perform(delete("/clientes/{id}", cliente.getId()))
                .andExpect(status().isNoContent());
    
        verify(clienteController, times(1)).removerCliente(cliente.getId());
    }    

    @Test
    void removerClienteNaoExiste() throws Exception {
        var id = UUID.randomUUID();
        doNothing().when(clienteController).removerCliente(id);
    
        mockMvc.perform(delete("/clientes/{id}", id))
                .andExpect(status().isNoContent());
    
        verify(clienteController, times(1)).removerCliente(id);
    }    

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}