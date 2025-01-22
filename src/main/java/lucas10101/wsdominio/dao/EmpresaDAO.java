package lucas10101.wsdominio.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lucas10101.wsdominio.entities.Empresa;

@Component
public class EmpresaDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // Método para criar um novo registro
    public Empresa novo(Empresa Empresa) {
        Empresa.setId(UUID.randomUUID());
        final String SQL = "INSERT INTO EMPRESA (ID, NOME) VALUES (?, ?)";
        jdbcTemplate.update(SQL, Empresa.getId(), Empresa.getNome());
        return Empresa;
    }

    // Método para atualizar um registro existente
    public int atualizar(Empresa Empresa) {
        final String SQL = "UPDATE EMPRESA SET NOME = ? WHERE ID = ?";
        return jdbcTemplate.update(SQL, Empresa.getNome(), Empresa.getId());
    }

    // Método para buscar um registro por ID
    public Optional<Empresa> buscarPorId(UUID id) {
        final String SQL = "SELECT ID, NOME FROM EMPRESA WHERE ID = ?";

        Empresa resultado = jdbcTemplate.queryForObject(SQL, (rs, rowNum) -> {

            var empresa = new Empresa();
            empresa.setId(UUID.fromString(rs.getString("ID")));
            empresa.setNome(rs.getString("NOME"));
            empresa.setIdEndereco(UUID.fromString(rs.getString("ID_ENDERECO")));

            return empresa;
        }, id);
        return Optional.ofNullable(resultado);
    }

    // Método para buscar todos os registros
    public List<Empresa> buscarTodos() {
        final String SQL = "SELECT ID, NOME FROM EMPRESA";
        return jdbcTemplate.query(SQL, (rs, rowNum) -> {

            var empresa = new Empresa();
            empresa.setId(UUID.fromString(rs.getString("ID")));
            empresa.setNome(rs.getString("NOME"));
            empresa.setIdEndereco(UUID.fromString(rs.getString("ID_ENDERECO")));

            return empresa;
        });
    }
}
