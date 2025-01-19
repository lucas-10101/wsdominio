package lucas10101.wsdominio.entities;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GrupoAcessoUsuario {

    @EqualsAndHashCode.Include
    private UUID idGrupoAcesso;

    private UUID idUsuario;
}
