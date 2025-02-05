package lucas10101.wsdominio.dao;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import lucas10101.wsdominio.dao.rowmapper.GrupoAcessoRowMapper;
import lucas10101.wsdominio.entities.GrupoAcesso;

@Component
public class GrupoAcessoDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RowMapper<GrupoAcesso> grupoAcessoRowMapper;

    public GrupoAcesso novo(GrupoAcesso grupoAcesso) {

        grupoAcesso.setId(UUID.randomUUID());

        jdbcTemplate.update("INSERT INTO GRUPO_ACESSO (ID, NOME, ID_EMPRESA) VALUES (?, ?, ?)",
                grupoAcesso.getId(),
                grupoAcesso.getNome(),
                grupoAcesso.getIdEmpresa());

        return grupoAcesso;
    }

    public int atualizar(GrupoAcesso grupoAcesso) {

        return jdbcTemplate.update("UPDATE GRUPO_ACESSO SET NOME = ? WHERE ID = ?",
                grupoAcesso.getNome(),
                grupoAcesso.getId());
    }

    public Collection<GrupoAcesso> buscarTodos(Pageable paginacao) {
        return jdbcTemplate.query(
                "SELECT ID, NOME, ID_EMPRESA FROM GRUPO_ACESSO ORDER BY ID LIMIT ? OFFSET ?",
                grupoAcessoRowMapper,
                paginacao.getPageSize(),
                paginacao.getOffset());
    }

    public Optional<GrupoAcesso> buscarPorId(UUID uuid) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        "SELECT ID, NOME, ID_EMPRESA FROM GRUPO_ACESSO WHERE ID = ?",
                        grupoAcessoRowMapper,
                        uuid));
    }
}
