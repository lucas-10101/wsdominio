package lucas10101.wsdominio.entities;

import java.util.UUID;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Dominio {

    @EqualsAndHashCode.Include
    private UUID id;

    private String nome;
}
