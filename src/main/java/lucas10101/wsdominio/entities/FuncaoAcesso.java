package lucas10101.wsdominio.entities;

import java.util.UUID;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class FuncaoAcesso {

    @EqualsAndHashCode.Include
    private UUID id;

    private String funcao;

    private UUID descricao;
}
