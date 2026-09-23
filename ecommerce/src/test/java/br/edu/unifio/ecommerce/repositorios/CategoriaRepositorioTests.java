package br.edu.unifio.ecommerce.repositorios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.ecommerce.entidades.Categoria;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    @Order(1)
    public void deveListarTodasAsCategorias() {

        int totalInicial = categoriaRepositorio.findAll().size();

        Categoria cat1 = new Categoria();
        cat1.setNome("Categoria Lista 1");
        cat1.setDescricao("Descrição Lista 1");
        categoriaRepositorio.save(cat1);

        Categoria cat2 = new Categoria();
        cat2.setNome("Categoria Lista 2");
        cat2.setDescricao("Descrição Lista 2");
        categoriaRepositorio.save(cat2);

        List<Categoria> categorias = categoriaRepositorio.findAll();

        assertTrue(categorias.size() >= 2);
        assertEquals(totalInicial + 2, categorias.size());
    }

    @Test
    @Order(2)
    public void deveBuscarUmaCategoriaPorId() {

        Categoria categoria = new Categoria();
        categoria.setNome("Casa e Cozinha");
        categoria.setDescricao("Itens para o lar");

        categoriaRepositorio.save(categoria);

        Categoria categoriaEncontrada = categoriaRepositorio
                .findById(categoria.getId())
                .orElseThrow();

        assertEquals(categoria.getId(), categoriaEncontrada.getId());
        assertEquals("Casa e Cozinha", categoriaEncontrada.getNome());
        assertEquals("Itens para o lar", categoriaEncontrada.getDescricao());
    }

    @Test
    @Order(3)
    public void deveExcluirUmaCategoriaPorId() {

        Categoria categoria = new Categoria();

        categoria.setNome("Categoria Exclusão");
        categoria.setDescricao("Descrição Exclusão");

        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));

        categoriaRepositorio.deleteById(categoria.getId());

        assertFalse(categoriaRepositorio.existsById(categoria.getId()));
    }

    @Test
    @Order(4)
    public void deveSalvarUmaCategoria() {

        Categoria categoria = new Categoria();

        categoria.setNome("Categoria Teste");
        categoria.setDescricao("Descrição Teste");

        categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio.existsById(categoria.getId()));

        assertEquals(
                "Categoria Teste",
                categoriaRepositorio
                        .findById(categoria.getId())
                        .orElseThrow()
                        .getNome()
        );
    }

    @Test
    @Order(5)
    public void deveAlterarUmaCategoria() {

        Categoria categoria = new Categoria();

        categoria.setNome("Categoria Original");
        categoria.setDescricao("Descrição Original");

        categoriaRepositorio.save(categoria);

        categoria.setNome("Categoria Alterada");
        categoria.setDescricao("Descrição Alterada");

        categoriaRepositorio.save(categoria);

        Categoria categoriaAlterada = categoriaRepositorio
                .findById(categoria.getId())
                .orElseThrow();

        assertEquals("Categoria Alterada", categoriaAlterada.getNome());
        assertEquals("Descrição Alterada", categoriaAlterada.getDescricao());
    }
}
