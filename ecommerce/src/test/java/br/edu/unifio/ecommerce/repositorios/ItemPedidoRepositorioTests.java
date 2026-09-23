package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import br.edu.unifio.ecommerce.entidades.ItemPedido;
import br.edu.unifio.ecommerce.entidades.Pedido;
import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemPedidoRepositorioTests {

    @Autowired
    private PedidoRepositorio pedidoRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Autowired
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Test
    @Order(1)
    public void deveListarTodosOsItensPedidos() {

        int totalInicial = itemPedidoRepositorio.findAll().size();

        Pedido pedido = pedidoRepositorio.findById(1).orElseThrow();
        Produto produto = produtoRepositorio.findById(1).orElseThrow();

        ItemPedido item1 = new ItemPedido();
        item1.setQuantidade(1);
        item1.setValorUnitario(new BigDecimal("50.00"));
        item1.setPedido(pedido);
        item1.setProduto(produto);
        itemPedidoRepositorio.save(item1);

        ItemPedido item2 = new ItemPedido();
        item2.setQuantidade(2);
        item2.setValorUnitario(new BigDecimal("100.00"));
        item2.setPedido(pedido);
        item2.setProduto(produto);
        itemPedidoRepositorio.save(item2);

        List<ItemPedido> itens = itemPedidoRepositorio.findAll();

        assertTrue(itens.size() >= 2);
        assertEquals(totalInicial + 2, itens.size());
    }

    @Test
    @Order(2)
    public void deveBuscarUmItemPedidoPorId() {

        Pedido pedido = pedidoRepositorio.findById(1).orElseThrow();
        Produto produto = produtoRepositorio.findById(1).orElseThrow();

        ItemPedido itemPedido = new ItemPedido();
        itemPedido.setQuantidade(4);
        itemPedido.setValorUnitario(new BigDecimal("120.00"));
        itemPedido.setPedido(pedido);
        itemPedido.setProduto(produto);

        itemPedidoRepositorio.save(itemPedido);

        ItemPedido itemEncontrado = itemPedidoRepositorio
                .findById(itemPedido.getId())
                .orElseThrow();

        assertEquals(itemPedido.getId(), itemEncontrado.getId());
        assertEquals(4, itemEncontrado.getQuantidade());
        assertEquals(new BigDecimal("120.00"), itemEncontrado.getValorUnitario());
    }

    @Test
    @Order(3)
    public void deveExcluirUmItemPedidoPorId() {

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setQuantidade(1);
        itemPedido.setValorUnitario(new BigDecimal("100.00"));

        itemPedido.setPedido(
                pedidoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedido.setProduto(
                produtoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedidoRepositorio.save(itemPedido);

        assertTrue(
                itemPedidoRepositorio.existsById(itemPedido.getId())
        );

        itemPedidoRepositorio.deleteById(itemPedido.getId());

        assertFalse(
                itemPedidoRepositorio.existsById(itemPedido.getId())
        );
    }

    @Test
    @Order(4)
    public void deveSalvarUmItemPedido() {

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setQuantidade(2);
        itemPedido.setValorUnitario(new BigDecimal("50.00"));

        itemPedido.setPedido(
                pedidoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedido.setProduto(
                produtoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedidoRepositorio.save(itemPedido);

        assertTrue(
                itemPedidoRepositorio.existsById(itemPedido.getId())
        );

        assertEquals(
                2,
                itemPedidoRepositorio
                        .findById(itemPedido.getId())
                        .orElseThrow()
                        .getQuantidade()
        );
    }

    @Test
    @Order(5)
    public void deveAlterarUmItemPedido() {

        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setQuantidade(1);
        itemPedido.setValorUnitario(new BigDecimal("50.00"));

        itemPedido.setPedido(
                pedidoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedido.setProduto(
                produtoRepositorio
                        .findById(1)
                        .orElseThrow()
        );

        itemPedidoRepositorio.save(itemPedido);

        itemPedido.setQuantidade(3);
        itemPedido.setValorUnitario(new BigDecimal("75.00"));

        itemPedidoRepositorio.save(itemPedido);

        ItemPedido itemPedidoAlterado = itemPedidoRepositorio
                .findById(itemPedido.getId())
                .orElseThrow();

        assertEquals(3, itemPedidoAlterado.getQuantidade());
        assertEquals(
                new BigDecimal("75.00"),
                itemPedidoAlterado.getValorUnitario()
        );
    }
}
