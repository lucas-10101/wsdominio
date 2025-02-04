package lucas10101.wsdominio.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import lucas10101.wsdominio.entities.Dominio;

@JdbcTest
@Import(value = { DominioDAO.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class TestDominioDAO {

    @Autowired
    private DominioDAO dominioDAO;

    @Test
    @DisplayName("Teste para criação de dominio para novo locatário na aplicação")
    public void testCriarNovoDominio() {
        assertDoesNotThrow(() -> {
            Dominio dominio = new Dominio();
            dominio.setNome("A".repeat(255));
            dominio = dominioDAO.novo(dominio);

            assertNotNull(dominio.getId());
        });
    }

    @Test
    @DisplayName("Teste de busca de dominio pelo seu ID")
    public void testBuscarPorId() {
        assertDoesNotThrow(() -> {
            Dominio dominio = new Dominio();
            dominio.setNome("A".repeat(255));
            dominio = dominioDAO.novo(dominio);

            assertNotNull(dominio.getId());

            var resultadoBusca = dominioDAO.buscarPorId(dominio.getId());

            assertNotNull(resultadoBusca.get());
        });
    }

    @Test
    @DisplayName("Teste para atualizar dados de um dominio existente")
    public void testAtualizarDominioExistente() {

        assertDoesNotThrow(() -> {
            Dominio dominio = new Dominio();
            dominio.setNome("A".repeat(255));
            dominio = dominioDAO.novo(dominio);

            dominio.setNome("B".repeat(255));

            var updates = dominioDAO.atualizar(dominio);
            assertEquals(1, updates);

            var atualizado = dominioDAO.buscarPorId(dominio.getId());

            assertNotNull(atualizado.get());
            assertEquals("B".repeat(255), atualizado.get().getNome());
        });

    }

    @Test
    @DisplayName("Teste de listagem dos dominios existentes")
    public void testBuscarTodosOsDominiosExistentes() {

        assertDoesNotThrow(() -> {

            Dominio dominio = new Dominio();
            dominio.setNome("A".repeat(255));
            dominio = dominioDAO.novo(dominio);

            dominio.setNome("B".repeat(255));
            dominio = dominioDAO.novo(dominio);

            dominio.setNome("C".repeat(255));
            dominio = dominioDAO.novo(dominio);

            var resultadoBusca = dominioDAO.buscarTodos();

            assertTrue(resultadoBusca.size() >= 3);
        });
    }
}
