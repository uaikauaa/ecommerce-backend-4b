package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.ecommerce.entidades.Cliente;
import br.edu.unifio.ecommerce.entidades.Pagamento;
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PagamentoRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private PagamentoRepositorio pagamentoRepositorio;

    @Test
    @Order(1)
    public void deveListarTodosOsPagamentos() {

        int totalInicial = pagamentoRepositorio.findAll().size();

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido1 = new Pedido();
        pedido1.setData(LocalDateTime.of(2026, 9, 16, 18, 0));
        pedido1.setStatus("PAGO");
        pedido1.setValorTotal(new BigDecimal("150.00"));
        pedido1.setCliente(cliente);
        pedidoRepositorio.save(pedido1);

        Pagamento pag1 = new Pagamento();
        pag1.setValor(new BigDecimal("150.00"));
        pag1.setData(LocalDateTime.of(2026, 9, 16, 18, 5));
        pag1.setStatus("APROVADO");
        pag1.setTipo("PIX");
        pag1.setPedido(pedido1);
        pagamentoRepositorio.save(pag1);

        Pedido pedido2 = new Pedido();
        pedido2.setData(LocalDateTime.of(2026, 9, 16, 19, 0));
        pedido2.setStatus("PENDENTE");
        pedido2.setValorTotal(new BigDecimal("250.00"));
        pedido2.setCliente(cliente);
        pedidoRepositorio.save(pedido2);

        Pagamento pag2 = new Pagamento();
        pag2.setValor(new BigDecimal("250.00"));
        pag2.setData(LocalDateTime.of(2026, 9, 16, 19, 5));
        pag2.setStatus("PENDENTE");
        pag2.setTipo("BOLETO");
        pag2.setPedido(pedido2);
        pagamentoRepositorio.save(pag2);

        List<Pagamento> pagamentos = pagamentoRepositorio.findAll();

        assertTrue(pagamentos.size() >= 2);
        assertEquals(totalInicial + 2, pagamentos.size());
    }

    @Test
    @Order(2)
    public void deveBuscarUmPagamentoPorId() {

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido pedido = new Pedido();
        pedido.setData(LocalDateTime.of(2026, 9, 16, 20, 0));
        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("400.00"));
        pedido.setCliente(cliente);
        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();
        pagamento.setValor(new BigDecimal("400.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 16, 20, 5));
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("CARTAO");
        pagamento.setPedido(pedido);
        pagamentoRepositorio.save(pagamento);

        Pagamento pagamentoEncontrado = pagamentoRepositorio
                .findById(pagamento.getId())
                .orElseThrow();

        assertEquals(pagamento.getId(), pagamentoEncontrado.getId());
        assertEquals("APROVADO", pagamentoEncontrado.getStatus());
        assertEquals("CARTAO", pagamentoEncontrado.getTipo());
    }

    @Test
    @Order(3)
    public void deveExcluirUmPagamentoPorId() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 20, 0));
        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("100.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("100.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 16, 20, 5));
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        assertTrue(
                pagamentoRepositorio.existsById(pagamento.getId())
        );

        pagamentoRepositorio.deleteById(pagamento.getId());

        assertFalse(
                pagamentoRepositorio.existsById(pagamento.getId())
        );

        pedidoRepositorio.deleteById(pedido.getId());
    }

    @Test
    @Order(4)
    public void deveSalvarUmPagamento() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 21, 0));
        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("200.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("200.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 16, 21, 5));
        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        assertTrue(
                pagamentoRepositorio.existsById(pagamento.getId())
        );

        assertEquals(
                "PIX",
                pagamentoRepositorio
                        .findById(pagamento.getId())
                        .orElseThrow()
                        .getTipo()
        );

        pagamentoRepositorio.deleteById(pagamento.getId());
        pedidoRepositorio.deleteById(pedido.getId());
    }

    @Test
    @Order(5)
    public void deveAlterarUmPagamento() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 22, 0));
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("300.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        Pagamento pagamento = new Pagamento();

        pagamento.setValor(new BigDecimal("300.00"));
        pagamento.setData(LocalDateTime.of(2026, 9, 16, 22, 5));
        pagamento.setStatus("PENDENTE");
        pagamento.setTipo("BOLETO");
        pagamento.setPedido(pedido);

        pagamentoRepositorio.save(pagamento);

        pagamento.setStatus("APROVADO");
        pagamento.setTipo("PIX");

        pagamentoRepositorio.save(pagamento);

        Pagamento pagamentoAlterado = pagamentoRepositorio
                .findById(pagamento.getId())
                .orElseThrow();

        assertEquals("APROVADO", pagamentoAlterado.getStatus());
        assertEquals("PIX", pagamentoAlterado.getTipo());

        pagamentoRepositorio.deleteById(pagamento.getId());
        pedidoRepositorio.deleteById(pedido.getId());
    }
}
