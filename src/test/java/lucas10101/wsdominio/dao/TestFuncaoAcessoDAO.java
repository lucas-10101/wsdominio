package lucas10101.wsdominio.dao;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import lucas10101.wsdominio.dao.rowmapper.FuncaoAcessoRowMapper;
import lucas10101.wsdominio.entities.FuncaoAcesso;

@JdbcTest
@Import({ FuncaoAcessoDAO.class, FuncaoAcessoRowMapper.class })
public class TestFuncaoAcessoDAO {

    @Autowired
    private FuncaoAcessoDAO funcaoAcessoDAO;

    @Test
    @DisplayName("Teste para incluir nova função de acesso")
    public void testIncluirNovaFuncaoAcesso() {

        assertDoesNotThrow(() -> {
            FuncaoAcesso funcaoAcesso = new FuncaoAcesso();
            funcaoAcesso.setFuncao("ADMINISTRADOR_SISTEMA");
            funcaoAcesso.setDescricao("Função para administradores com acesso irrestrito ao sistema");

            funcaoAcesso = funcaoAcessoDAO.novo(funcaoAcesso);

            assertNotNull(funcaoAcesso.getId());
        });
    }

    @Test
    @DisplayName("Teste para incluir nova função de acesso")
    public void testAtualizarFuncaoAcesso() {
        assertDoesNotThrow(() -> {
            FuncaoAcesso funcaoAcesso = new FuncaoAcesso();
            funcaoAcesso.setFuncao("ADMINISTRADOR_SISTEMA");
            funcaoAcesso.setDescricao("Função para administradores com acesso irrestrito ao sistema");

            funcaoAcesso = funcaoAcessoDAO.novo(funcaoAcesso);
            assertNotNull(funcaoAcesso.getId());

            funcaoAcesso.setDescricao("USUARIO_GERAL");
            funcaoAcesso.setFuncao("Função representando um usuário geral autenticado do sistema");

            int updates = funcaoAcessoDAO.atualizar(funcaoAcesso);
            assertEquals(1, updates);

            var resultado = funcaoAcessoDAO.buscarPorId(funcaoAcesso.getId());

            assertNotNull(resultado.get());
            assertEquals(funcaoAcesso, resultado.get());
        });
    }

    @Test
    @DisplayName("Teste para incluir nova função de acesso")
    public void testBuscarFuncaoAcessoPorId() {

        assertDoesNotThrow(() -> {
            FuncaoAcesso funcaoAcesso = new FuncaoAcesso();
            funcaoAcesso.setFuncao("ADMINISTRADOR_SISTEMA");
            funcaoAcesso.setDescricao("Função para administradores com acesso irrestrito ao sistema");

            funcaoAcesso = funcaoAcessoDAO.novo(funcaoAcesso);
            assertNotNull(funcaoAcesso.getId());

            var resultado = funcaoAcessoDAO.buscarPorId(funcaoAcesso.getId());

            assertNotNull(resultado.get());
            assertEquals(funcaoAcesso, resultado.get());
        });
    }

    @Test
    @DisplayName("Teste para incluir nova função de acesso com paginação")
    public void testBuscarTodasFuncoesAcessoPaginado() {

        assertDoesNotThrow(() -> {
            FuncaoAcesso funcaoAcesso = new FuncaoAcesso();
            funcaoAcesso.setDescricao("");

            funcaoAcesso.setFuncao("F1");
            funcaoAcessoDAO.novo(funcaoAcesso);

            funcaoAcesso.setFuncao("F2");
            funcaoAcessoDAO.novo(funcaoAcesso);

            funcaoAcesso.setFuncao("F3");
            funcaoAcessoDAO.novo(funcaoAcesso);

            Pageable page = PageRequest.of(0, 2);

            var resultado = funcaoAcessoDAO.buscarTodos(page);

            assertEquals(2, resultado.size());
        });
    }
}
