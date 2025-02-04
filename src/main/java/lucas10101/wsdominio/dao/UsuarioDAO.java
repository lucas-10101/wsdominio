package lucas10101.wsdominio.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lucas10101.wsdominio.entities.Usuario;

@Component
public class UsuarioDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Usuario novo(Usuario usuario) {


        

        jdbcTemplate.update(
                "INSERT INTO USUARIO (USUARIO, SENHA, ATIVO, SENHA_TEMPORARIA, ID_IDP_EXTERNO, ID_PESSOA, ID_DOMINIO) VALUES (?, ?, ?, ?, ?, ?, ?)",
                usuario.getUsuario(),
                usuario.getSenha(),
                usuario.isAtivo(),
                usuario.isSenhaTemporaria(),
                usuario.getIdIDPExterno(),
                usuario.getIdDominio());

    }
}
