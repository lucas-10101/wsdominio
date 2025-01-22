package lucas10101.wsdominio.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lucas10101.wsdominio.entities.Dominio;

@Component
public class DominioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Dominio novo(Dominio dominio) {

        final UUID novoId = UUID.randomUUID();
        final String SQL = "INSERT INTO DOMINIO (ID, NOMES) VALUES (?, ?)";

        jdbcTemplate.update(SQL, novoId, dominio.getNome());

        return dominio;
    }

    public int atualizar(Dominio dominio) {
        final String SQL = "UPDATE DOMINIO SET NOME = ? WHERE ID = ?";
        return jdbcTemplate.update(SQL, dominio.getNome(), dominio.getId());
    }

    public Optional<Dominio> buscarPorId(UUID ID) {
        final String SQL = "SELECT ID, NOME FROM DOMINIO WHERE ID = ?";

        Dominio resultado = jdbcTemplate.queryForObject(SQL, (rs, numeroLinha) -> {

            var dominio = new Dominio();
            dominio.setId(UUID.fromString(rs.getString("ID")));
            dominio.setNome(rs.getString("NOME"));

            return dominio;
        }, ID);

        return Optional.ofNullable(resultado);
    }

    public List<Dominio> buscarTodos() {
        final String SQL = "SELECT ID, NOME FROM DOMINIO";
        return jdbcTemplate.query(SQL, (rs, rowNum) -> {

            var dominio = new Dominio();
            dominio.setId(UUID.fromString(rs.getString("ID")));
            dominio.setNome(rs.getString("NOME"));

            return dominio;
        });
    }
}
