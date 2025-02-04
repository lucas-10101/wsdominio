package lucas10101.wsdominio.entities;

import java.util.UUID;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Setter;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @EqualsAndHashCode.Include
    private String usuario;

    @Setter(value = AccessLevel.NONE)
    private String senha;

    private boolean ativo;

    private boolean senhaTemporaria;

    private String idIDPExterno;

    private UUID idPessoa;

    private UUID idDominio;

    /**
     * Garante a integridade do campo por só autorizar o armazenamento de
     * informações criptografadas em qualquer ponto da aplicação (encapsulamento)
     * 
     * <br>
     * <br>
     * <br>
     * 
     * Caso a senha esteje criptografada, ela será armazenada 'as-is' e
     * caso a senha não seja uma criptografia válida pelo encoder padrão, ela será
     * criptografada de acordo
     * 
     * {@link PasswordEncoderFactories} to get some of the allowed list
     * 
     * @param senha A senha que pode ou não estar criptografada
     */
    public void setSenha(String senha) {

        assert senha != null && !senha.isBlank();

        final String[] allowedAlgorithms = new String[] {
                "bcrypt",
                "ldap",
                "MD5",
                "noop",
                "SHA-256",
                "sha256",
                "argon2"
        };

        boolean criptografado = false;
        for (int i = 0; i < allowedAlgorithms.length; i++) {

            if (senha.indexOf(allowedAlgorithms[i]) != -1) {
                criptografado = true;
                break;
            }
        }

        if (!criptografado) {
            this.senha = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(senha);
            return;
        }

        this.senha = senha;
    }

}
