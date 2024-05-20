package tech.ada.banco.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_conta" , discriminatorType = DiscriminatorType.INTEGER)
@ToString
public abstract class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column(columnDefinition = "UUID", nullable = false)
    private UUID uuid;

    @Column(unique = true)
    private Integer numero;

    @Column
    private BigDecimal saldo;

    @Column
    private LocalDate dataCriacao;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cliente_id")
    private ClientePF cliente;

/*
    public Conta(Cliente cliente)  {
        this.numero = BancoDadosService.getNumeroConta();
        saldo = BigDecimal.ZERO;
        dataCriacao = LocalDate.now();
        uuid = UUID.randomUUID();
        this.cliente = cliente;
        this.cliente.setConta(this);
        BancoDadosService.incluirConta(this);
    }
*/
}
