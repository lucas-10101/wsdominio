package lucas10101.wsdominio.entities;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Usuario {

    @EqualsAndHashCode.Include
    private String usuario;

    private String senha;

    private boolean ativo;

    private boolean senhaTemporaria;

    private String idIDPExterno;

    private UUID idPessoa;
}
