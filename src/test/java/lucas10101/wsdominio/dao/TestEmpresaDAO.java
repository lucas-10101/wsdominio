package lucas10101.wsdominio.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import lucas10101.wsdominio.entities.Dominio;
import lucas10101.wsdominio.entities.Empresa;

@JdbcTest
@Import(value = { EmpresaDAO.class, DominioDAO.class })
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class TestEmpresaDAO {

    @Autowired
    private DominioDAO dominioDAO;

    @Autowired
    private EmpresaDAO empresaDAO;

    @Test
    @DisplayName("Teste para incluir nova empresa")
    public void testCriarNovaEmpresa() {
        assertDoesNotThrow(() -> {

            Dominio dominio = new Dominio();
            dominio.setNome("A".repeat(255));
            dominio = dominioDAO.novo(dominio);
            assertNotNull(dominio.getId());

            Empresa empresa = new Empresa();
            empresa.setNome("E".repeat(255));
            empresa.setIdDominio(dominio.getId());
            empresa.setIdEndereco(UUID.randomUUID());

            empresa = empresaDAO.novo(empresa);
            assertNotNull(empresa.getId());
        });
    }

    @Test
    @DisplayName("Teste para atualizar uma empresa existente")
    public void testCriarAtualizarEmpresaExistente() {
        assertDoesNotThrow(() -> {

            Dominio dominio = new Dominio();
            dominio.setNome("A".repeat(255));
            dominio = dominioDAO.novo(dominio);
            assertNotNull(dominio.getId());

            Empresa empresa = new Empresa();
            empresa.setNome("E".repeat(255));
            empresa.setIdDominio(dominio.getId());
            empresa.setIdEndereco(UUID.randomUUID());

            empresa = empresaDAO.novo(empresa);
            assertNotNull(empresa.getId());

            empresa.setNome("B".repeat(255));
            int updates = empresaDAO.atualizar(empresa);

            assertEquals(1, updates);

            var atualizado = empresaDAO.buscarPorId(empresa.getId());
            assertEquals(empresa, atualizado.get());
        });
    }

    @Test
    @DisplayName("Teste para buscar empresa por id")
    public void testCriarBuscarEmpresaPorId() {
        assertDoesNotThrow(() -> {

            Dominio dominio = new Dominio();
            dominio.setNome("A".repeat(255));
            dominio = dominioDAO.novo(dominio);
            assertNotNull(dominio.getId());

            Empresa empresa = new Empresa();
            empresa.setNome("E".repeat(255));
            empresa.setIdDominio(dominio.getId());
            empresa.setIdEndereco(UUID.randomUUID());

            empresa = empresaDAO.novo(empresa);
            assertNotNull(empresa.getId());

            var encontrado = empresaDAO.buscarPorId(empresa.getId());
            assertEquals(empresa, encontrado.get());
        });
    }

    @Test
    @DisplayName("Teste para listar todas as empresas existentes")
    public void testBuscarTodasAsEmpresas() {
        assertDoesNotThrow(() -> {

            Dominio dominio = new Dominio();
            dominio.setNome("A".repeat(255));
            dominio = dominioDAO.novo(dominio);
            assertNotNull(dominio.getId());

            Empresa empresa = new Empresa();
            empresa.setNome("E".repeat(255));
            empresa.setIdDominio(dominio.getId());
            empresa.setIdEndereco(UUID.randomUUID());

            empresaDAO.novo(empresa);
            empresaDAO.novo(empresa);
            empresaDAO.novo(empresa);

            var resultado = empresaDAO.buscarTodos();

            assertTrue(resultado.size() >= 3);
        });
    }

    @Test
    @DisplayName("Teste para inserir empresa inválida com informações requiridas em falta")
    public void testInserirEmpresaInvalida() {

        assertThrows(Exception.class, () -> {

            Empresa empresa = new Empresa();
            empresa.setNome("E".repeat(255));

            empresa = empresaDAO.novo(empresa);
        });
    }
}
