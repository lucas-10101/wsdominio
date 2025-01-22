package lucas10101.wsdominio.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import lucas10101.wsdominio.entities.GrupoAcesso;

@Component
@Scope("singleton")
public class GrupoAcessoRowMapper implements RowMapper<GrupoAcesso> {

    @Override
    public GrupoAcesso mapRow(ResultSet rs, int row) throws SQLException {
        GrupoAcesso grupo = new GrupoAcesso();
        grupo.setId(UUID.fromString(rs.getString("ID")));
        grupo.setNome(rs.getString("NOME"));
        grupo.setIdEmpresa(UUID.fromString(rs.getString("ID_EMPRESA")));

        return grupo;
    }
}
