package lucas10101.wsdominio.entities;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FuncaoUsuario {

    @EqualsAndHashCode.Include
    private UUID idFuncaoAcesso;

    private UUID idUsuario;

    private UUID idEmpresa;
}
