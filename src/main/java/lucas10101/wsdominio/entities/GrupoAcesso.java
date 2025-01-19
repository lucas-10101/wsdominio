package lucas10101.wsdominio.entities;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class GrupoAcesso {

    @EqualsAndHashCode.Include
    private UUID id;

    private String name;

    private UUID idEmpresa;
}
