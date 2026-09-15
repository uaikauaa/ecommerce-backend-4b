package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.ItemPedido;

@SpringBootTest 
public class ItemPedidoRepositorioTests {

    @Autowired 
    private ItemPedidoRepositorio itemPedidoRepositorio;

    @Test 
    public void deveBuscarUmItemPedidoPorId() {
        ItemPedido itemPedido = itemPedidoRepositorio.findById(2).orElseThrow();
        
        assertNotNull(itemPedido);
        assertEquals(new BigDecimal("92.50"), itemPedido.getValorUnitario());
    }
}
