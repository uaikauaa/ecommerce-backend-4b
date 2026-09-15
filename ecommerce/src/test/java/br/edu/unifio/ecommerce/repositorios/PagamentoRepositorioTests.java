package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Pagamento;

@SpringBootTest 
public class PagamentoRepositorioTests {

    @Autowired 
    private PagamentoRepositorio pagamentoRepositorio;

    @Test 
    public void deveBuscarUmPagamentoPorId() {
        Pagamento pagamento = pagamentoRepositorio.findById(2).orElseThrow();

        assertNotNull(pagamento);
        assertEquals(new BigDecimal("92.50"), pagamento.getValor());
    }
}
