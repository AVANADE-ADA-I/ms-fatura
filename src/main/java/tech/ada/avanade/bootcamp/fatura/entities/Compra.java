package tech.ada.avanade.bootcamp.fatura.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.ada.avanade.bootcamp.fatura.dto.request.CompraRequestDTO;
import tech.ada.avanade.bootcamp.fatura.dto.response.CompraResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "compras")
@Getter
@NoArgsConstructor
public class Compra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime dataCompra;
    private String loja;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Compra(CompraRequestDTO dto, Usuario usuario) {
        this.dataCompra = dto.dataCompra();
        this.loja = dto.loja();
        this.valor = dto.valor();
        this.statusCompra = dto.statusCompra();
        this.usuario = usuario;
    }
    public CompraResponseDTO dto() {
        return new CompraResponseDTO(this.usuario.getNumeroCartao(), this.loja, this.valor, this.statusCompra);
    }
    public void cancelar() {
        this.statusCompra = StatusCompra.CANCELADA;
    }
}
