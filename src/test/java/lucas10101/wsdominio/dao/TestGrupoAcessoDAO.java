package lucas10101.wsdominio.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lucas10101.wsdominio.dao.rowmapper.GrupoAcessoRowMapper;
import lucas10101.wsdominio.entities.Dominio;
import lucas10101.wsdominio.entities.Empresa;
import lucas10101.wsdominio.entities.GrupoAcesso;

@JdbcTest
@Import({ EmpresaDAO.class, DominioDAO.class, GrupoAcessoDAO.class, GrupoAcessoRowMapper.class })
public class TestGrupoAcessoDAO {

    @Autowired
    private DominioDAO dominioDAO;

    @Autowired
    private EmpresaDAO empresaDAO;

    @Autowired
    private GrupoAcessoDAO grupoAcessoDAO;

    @Test
    @DisplayName("Teste para criar novo grupo de acesso")
    public void testCriarNovoGrupoAcesso() {

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

            GrupoAcesso grupoAcesso = new GrupoAcesso();
            grupoAcesso.setNome("Grupo A");
            grupoAcesso.setIdEmpresa(empresa.getId());

            grupoAcesso = grupoAcessoDAO.novo(grupoAcesso);
            assertNotNull(grupoAcesso.getId());
        });
    }

    @Test
    @DisplayName("Teste para atualizar um grupo de acesso existente")
    public void testAtualizarGrupoAcessoExistente() {

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

            GrupoAcesso grupoAcesso = new GrupoAcesso();
            grupoAcesso.setNome("Grupo A");
            grupoAcesso.setIdEmpresa(empresa.getId());

            grupoAcesso = grupoAcessoDAO.novo(grupoAcesso);
            assertNotNull(grupoAcesso.getId());

            grupoAcesso.setNome("Grupo B");
            int updates = grupoAcessoDAO.atualizar(grupoAcesso);

            assertTrue(updates == 1);

            var resultadoBusca = grupoAcessoDAO.buscarPorId(grupoAcesso.getId());
            assertNotNull(resultadoBusca.get());
            assertEquals(grupoAcesso, resultadoBusca.get());
        });
    }

    @Test
    @DisplayName("Teste para buscar um grupo de acesso por id")
    public void testeBuscarGrupoDeAcessoPorId() {

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

            GrupoAcesso grupoAcesso = new GrupoAcesso();
            grupoAcesso.setNome("Grupo A");
            grupoAcesso.setIdEmpresa(empresa.getId());

            grupoAcesso = grupoAcessoDAO.novo(grupoAcesso);
            assertNotNull(grupoAcesso.getId());

            var resultadoBusca = grupoAcessoDAO.buscarPorId(grupoAcesso.getId());
            assertNotNull(resultadoBusca.get());
            assertEquals(grupoAcesso, resultadoBusca.get());
        });
    }

    @Test
    @DisplayName("Teste para listar todos os grupos de acesso paginado")
    public void testeListarTodosGruposAcesso() {

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

            GrupoAcesso grupoAcesso = new GrupoAcesso();
            grupoAcesso.setNome("Grupo A");
            grupoAcesso.setIdEmpresa(empresa.getId());

            grupoAcesso.setNome("Grupo A");
            grupoAcessoDAO.novo(grupoAcesso);

            grupoAcesso.setNome("Grupo B");
            grupoAcessoDAO.novo(grupoAcesso);

            grupoAcesso.setNome("Grupo C");
            grupoAcessoDAO.novo(grupoAcesso);

            Pageable page = PageRequest.of(0, 2);
            var result = grupoAcessoDAO.buscarTodos(page);

            assertTrue(result.size() == 2);
        });
    }
}
