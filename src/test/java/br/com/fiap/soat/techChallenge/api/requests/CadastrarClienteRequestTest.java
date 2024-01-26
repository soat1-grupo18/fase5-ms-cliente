package br.com.fiap.soat.techChallenge.api.requests;

import br.com.fiap.soat.techChallenge.entities.Cliente;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CadastrarClienteRequestTest {

    private static final String NOME = "John Doe";
    private static final String CPF = "123.456.789-01";
    private static final String TELEFONE = "123-456-789";

    @InjectMocks
    private CadastrarClienteRequest cadastrarClienteRequest;

    private Validator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testToDomain() {
        cadastrarClienteRequest.setNome(NOME);
        cadastrarClienteRequest.setCpf(CPF);
        cadastrarClienteRequest.setTelefone(TELEFONE);

        Cliente cliente = cadastrarClienteRequest.toDomain();

        assertNotNull(cliente);
        assertEquals(NOME, cliente.getNome());
        assertEquals(CPF, cliente.getCpf());
        assertEquals(TELEFONE, cliente.getTelefone());
    }

    @Test
    void testValidationSuccess() {
        cadastrarClienteRequest.setNome(NOME);
        cadastrarClienteRequest.setCpf(CPF);
        cadastrarClienteRequest.setTelefone(TELEFONE);

        assertTrue(validator.validate(cadastrarClienteRequest).isEmpty());
    }

    @Test
    void testValidationFailure() {
        cadastrarClienteRequest.setNome(null);
        cadastrarClienteRequest.setCpf(null);
        cadastrarClienteRequest.setTelefone(null);

        assertFalse(validator.validate(cadastrarClienteRequest).isEmpty());
    }
}
