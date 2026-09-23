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
import br.edu.unifio.ecommerce.entidades.Pedido;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PedidoRepositorioTests {

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Test
    @Order(1)
    public void deveListarTodosOsPedidos() {

        int totalInicial = pedidoRepositorio.findAll().size();

        Cliente cliente = clienteRepositorio.findById(1).orElseThrow();

        Pedido p1 = new Pedido();
        p1.setData(LocalDateTime.of(2026, 9, 16, 18, 0));
        p1.setStatus("PAGO");
        p1.setValorTotal(new BigDecimal("120.00"));
        p1.setCliente(cliente);
        pedidoRepositorio.save(p1);

        Pedido p2 = new Pedido();
        p2.setData(LocalDateTime.of(2026, 9, 16, 19, 0));
        p2.setStatus("PENDENTE");
        p2.setValorTotal(new BigDecimal("240.00"));
        p2.setCliente(cliente);
        pedidoRepositorio.save(p2);

        List<Pedido> pedidos = pedidoRepositorio.findAll();

        assertTrue(pedidos.size() >= 2);
        assertEquals(totalInicial + 2, pedidos.size());
    }

    @Test
    @Order(2)
    public void deveBuscarUmPedidoPorId() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 20, 0));
        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("500.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        Pedido pedidoEncontrado = pedidoRepositorio
                .findById(pedido.getId())
                .orElseThrow();

        assertEquals(pedido.getId(), pedidoEncontrado.getId());
        assertEquals("PAGO", pedidoEncontrado.getStatus());
        assertEquals(new BigDecimal("500.00"), pedidoEncontrado.getValorTotal());
    }

    @Test
    @Order(3)
    public void deveExcluirUmPedidoPorId() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 21, 0));
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("200.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        assertTrue(
                pedidoRepositorio.existsById(pedido.getId())
        );

        pedidoRepositorio.deleteById(pedido.getId());

        assertFalse(
                pedidoRepositorio.existsById(pedido.getId())
        );
    }

    @Test
    @Order(4)
    public void deveSalvarUmPedido() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 22, 0));
        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("300.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        assertTrue(
                pedidoRepositorio.existsById(pedido.getId())
        );

        assertEquals(
                "PAGO",
                pedidoRepositorio
                        .findById(pedido.getId())
                        .orElseThrow()
                        .getStatus()
        );
    }

    @Test
    @Order(5)
    public void deveAlterarUmPedido() {

        Pedido pedido = new Pedido();

        pedido.setData(LocalDateTime.of(2026, 9, 16, 23, 0));
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(new BigDecimal("100.00"));

        pedido.setCliente(
                clienteRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        pedidoRepositorio.save(pedido);

        pedido.setStatus("PAGO");
        pedido.setValorTotal(new BigDecimal("150.00"));

        pedidoRepositorio.save(pedido);

        Pedido pedidoAlterado = pedidoRepositorio
                .findById(pedido.getId())
                .orElseThrow();

        assertEquals("PAGO", pedidoAlterado.getStatus());
        assertEquals(new BigDecimal("150.00"), pedidoAlterado.getValorTotal());
    }
}
