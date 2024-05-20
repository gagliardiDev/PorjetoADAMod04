package tech.ada.banco.dto;

import lombok.*;

import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContaDTO {

    private UUID Uuid;

    @NonNull
    private Integer numero;

    @Positive(message = "Saldo deve ser maior que zero")
    private BigDecimal saldo;

    @Positive(message = "Valor deve ser maior que zero")
    private BigDecimal valorOperacao;

    private UUID contaDestinoUuid;

    private UUID clienteUuid;

   // private Cliente cliente;

}
