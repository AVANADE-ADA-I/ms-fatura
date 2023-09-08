package tech.ada.avanade.bootcamp.fatura.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tech.ada.avanade.bootcamp.fatura.dto.response.CompraResponseDTO;
import tech.ada.avanade.bootcamp.fatura.dto.response.FaturaDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "faturas")
@NoArgsConstructor
@Getter
@Setter
public class Fatura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dataVencimento;
    private LocalDate dataProcessamento;
    private BigDecimal valor;
    private BigDecimal valorPago;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Fatura(LocalDate dataVencimento, LocalDate dataProcessamento, BigDecimal valor, BigDecimal valorPago, Usuario usuario) {
        this.dataVencimento = dataVencimento;
        this.dataProcessamento = dataProcessamento;
        this.valor = valor;
        this.valorPago = valorPago;
        this.usuario = usuario;
    }

    public FaturaDTO dto(List<CompraResponseDTO> compras) {
        return new FaturaDTO(this.dataProcessamento, this.valor, this.dataVencimento, compras);
    }
}
