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
import br.edu.unifio.ecommerce.entidades.Categoria;
import br.edu.unifio.ecommerce.entidades.Produto;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProdutoRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private ProdutoRepositorio produtoRepositorio;

    @Test
    @Order(1)
    public void deveListarTodosOsProdutos() {

        int totalInicial = produtoRepositorio.findAll().size();

        Categoria categoria = categoriaRepositorio.findById(Short.parseShort("1")).orElseThrow();

        Produto p1 = new Produto();
        p1.setNome("Produto Lista 1");
        p1.setDescricao("Descrição Lista 1");
        p1.setEstoque((short) 10);
        p1.setPreco(new BigDecimal("99.90"));
        p1.setCategoria(categoria);
        produtoRepositorio.save(p1);

        Produto p2 = new Produto();
        p2.setNome("Produto Lista 2");
        p2.setDescricao("Descrição Lista 2");
        p2.setEstoque((short) 20);
        p2.setPreco(new BigDecimal("199.90"));
        p2.setCategoria(categoria);
        produtoRepositorio.save(p2);

        List<Produto> produtos = produtoRepositorio.findAll();

        assertTrue(produtos.size() >= 2);
        assertEquals(totalInicial + 2, produtos.size());
    }

    @Test
    @Order(2)
    public void deveBuscarUmProdutoPorId() {

        Categoria categoria = categoriaRepositorio.findById(Short.parseShort("1")).orElseThrow();

        Produto produto = new Produto();
        produto.setNome("Teclado Mecânico Pro");
        produto.setDescricao("Teclado mecânico switch red");
        produto.setEstoque((short) 15);
        produto.setPreco(new BigDecimal("250.00"));
        produto.setCategoria(categoria);

        produtoRepositorio.save(produto);

        Produto produtoEncontrado = produtoRepositorio
                .findById(produto.getId())
                .orElseThrow();

        assertEquals(produto.getId(), produtoEncontrado.getId());
        assertEquals("Teclado Mecânico Pro", produtoEncontrado.getNome());
        assertEquals(new BigDecimal("250.00"), produtoEncontrado.getPreco());
    }

    @Test
    @Order(3)
    public void deveExcluirUmProdutoPorId() {

        Produto produto = new Produto();

        produto.setNome("Nome Teste");
        produto.setDescricao("Descrição Teste");
        produto.setEstoque(Short.parseShort("1"));
        produto.setPreco(new BigDecimal("1.00"));

        produto.setCategoria(
                categoriaRepositorio
                        .findById(Short.parseShort("1"))
                        .orElseThrow()
        );

        produtoRepositorio.save(produto);

        assertTrue(produtoRepositorio.existsById(produto.getId()));

        produtoRepositorio.deleteById(produto.getId());

        assertFalse(produtoRepositorio.existsById(produto.getId()));
    }

    @Test
    @Order(4)
    public void deveSalvarUmProduto() {

        Produto produto = new Produto();

        produto.setNome("Nome Teste");
        produto.setDescricao("Descrição Teste");
        produto.setEstoque(Short.parseShort("1"));
        produto.setPreco(new BigDecimal("1.00"));

        produto.setCategoria(
                categoriaRepositorio
                        .findById(Short.parseShort("1"))
                        .orElseThrow()
        );

        produtoRepositorio.save(produto);

        assertTrue(produtoRepositorio.existsById(produto.getId()));

        assertEquals(
                "Nome Teste",
                produtoRepositorio
                        .findById(produto.getId())
                        .orElseThrow()
                        .getNome()
        );
    }

    @Test
    @Order(5)
    public void deveAlterarUmProduto() {

        Produto produto = new Produto();

        produto.setNome("Produto Original");
        produto.setDescricao("Descrição Original");
        produto.setEstoque(Short.parseShort("5"));
        produto.setPreco(new BigDecimal("100.00"));

        produto.setCategoria(
                categoriaRepositorio
                        .findById(Short.parseShort("1"))
                        .orElseThrow()
        );

        produtoRepositorio.save(produto);

        produto.setNome("Produto Alterado");
        produto.setPreco(new BigDecimal("150.00"));

        produtoRepositorio.save(produto);

        Produto produtoAlterado = produtoRepositorio
                .findById(produto.getId())
                .orElseThrow();

        assertEquals("Produto Alterado", produtoAlterado.getNome());
        assertEquals(new BigDecimal("150.00"), produtoAlterado.getPreco());
    }
}
