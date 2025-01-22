package lucas10101.wsdominio.dao.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import lucas10101.wsdominio.entities.FuncaoAcesso;

@Component
@Scope("singleton")
public class FuncaoAcessoRowMapper implements RowMapper<FuncaoAcesso> {

    @Override
    public FuncaoAcesso mapRow(ResultSet rs, int row) throws SQLException {

        FuncaoAcesso funcao = new FuncaoAcesso();

        funcao.setId(UUID.fromString(rs.getString("ID")));
        funcao.setFuncao(rs.getString("FUNCAO"));
        funcao.setDescricao(rs.getString("DESCRICAO"));

        return funcao;

    }
}
