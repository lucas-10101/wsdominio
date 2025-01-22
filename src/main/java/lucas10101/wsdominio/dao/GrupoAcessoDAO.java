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

    public GrupoAcesso atualizar(GrupoAcesso grupoAcesso) {

        jdbcTemplate.update("UPDATE GRUPO_ACESSO SET NOME = ? WHERE ID = ?",
                grupoAcesso.getNome(),
                grupoAcesso.getId());

        return grupoAcesso;
    }

    public Collection<GrupoAcesso> listar(Pageable paginacao) {
        return jdbcTemplate.query(
                "SELECT ID, NOME, ID_EMPRESA FROM GRUPO_ACESSO ORDER BY ID OFFSET ? ROWS FETCH NEXT ? ROWS ONLY",
                grupoAcessoRowMapper,
                paginacao.getOffset(),
                paginacao.getPageSize());
    }

    public Optional<GrupoAcesso> buscarPorId(UUID uuid) {
        return Optional.ofNullable(
                jdbcTemplate.queryForObject(
                        "SELECT ID, NOME, ID_EMPRESA FROM GRUPO_ACESSO WHERE ID = ?",
                        grupoAcessoRowMapper,
                        uuid));
    }
}
