package br.com.fiap.soat.techChallenge.api;

import br.com.fiap.soat.techChallenge.api.requests.CadastrarClienteRequest;
import br.com.fiap.soat.techChallenge.controllers.ClienteController;
import br.com.fiap.soat.techChallenge.presenters.ClientePresenter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClienteController clienteController;

    @InjectMocks
    private ClienteApi clienteApi;

    @Test
    void testCadastrarCliente() throws Exception {
        // Dados de exemplo
        String cpf = "123.456.789-01";
        CadastrarClienteRequest request = new CadastrarClienteRequest();
        request.setNome("John Doe");
        request.setCpf(cpf);
        request.setTelefone("123-456-789");

        ClientePresenter clientePresenter = new ClientePresenter(UUID.randomUUID(), "John Doe", cpf, "123-456-789");

        // Configurar o comportamento do mock
        when(clienteController.cadastrarCliente(request.toDomain())).thenReturn(clientePresenter);

        // Executar a requisição POST /clientes
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nome\": \"John Doe\", \"cpf\": \"123.456.789-01\", \"telefone\": \"123-456-789\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf").value(cpf));
    }

    @Test
    void testIdentificarCliente() throws Exception {
        // Dados de exemplo
        String cpf = "123.456.789-01";
        ClientePresenter clientePresenter = new ClientePresenter(UUID.randomUUID(), "John Doe", cpf, "123-456-789");

        // Configurar o comportamento do mock
        when(clienteController.identificarCliente(cpf)).thenReturn(clientePresenter);

        // Executar a requisição GET /clientes/{cpf}
        mockMvc.perform(get("/clientes/{cpf}", cpf))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.cpf").value(cpf));
    }


}
