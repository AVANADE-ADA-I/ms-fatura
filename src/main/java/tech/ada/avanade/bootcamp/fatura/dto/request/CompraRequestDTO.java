package tech.ada.avanade.bootcamp.fatura.dto.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompraRequestDTO(String numeroCartao, LocalDateTime dataCompra, String loja, BigDecimal valor) {
}
