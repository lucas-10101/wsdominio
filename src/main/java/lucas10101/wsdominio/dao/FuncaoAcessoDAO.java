package lucas10101.wsdominio.dao;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import lucas10101.wsdominio.entities.FuncaoAcesso;

@Component
public class FuncaoAcessoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<FuncaoAcesso> rowMapper;

    public FuncaoAcesso novo(FuncaoAcesso funcaoAcesso) {

        funcaoAcesso.setId(UUID.randomUUID());

        jdbcTemplate.update("INSERT INTO FUNCAO_ACESSO(ID, FUNCAO, DESCRICAO) VALUES(?, ?, ?)",
                funcaoAcesso.getId(),
                funcaoAcesso.getFuncao(),
                funcaoAcesso.getDescricao());

        return funcaoAcesso;
    }

    public FuncaoAcesso atualizar(FuncaoAcesso funcaoAcesso) {
        jdbcTemplate.update("UPDATE FUNCAO_ACESSO SET FUNCAO = ?, DESCRICAO = ? WHERE ID = ?",
                funcaoAcesso.getFuncao(),
                funcaoAcesso.getDescricao(),
                funcaoAcesso.getId());

        return funcaoAcesso;
    }

    public Optional<FuncaoAcesso> buscarPorId(UUID uuid) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        "SELECT FUNCAO, DESCRICAO FROM FUNCAO_ACESSO WHERE ID = ?",
                        rowMapper,
                        uuid));
    }

    public Collection<FuncaoAcesso> listar(Pageable paginacao) {
        return jdbcTemplate.query(
                "SELECT ID, FUNCAO, DESCRICAO FROM FUNCAO_ACESSO ORDER BY ID OFFSET ? ROWS FETCH ? ROWS ONLY",
                rowMapper,
                paginacao.getOffset(),
                paginacao.getPageSize());

    }
}
