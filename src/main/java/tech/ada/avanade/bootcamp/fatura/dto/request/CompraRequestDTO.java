package tech.ada.avanade.bootcamp.fatura.dto.request;

import tech.ada.avanade.bootcamp.fatura.entities.StatusCompra;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CompraRequestDTO(String numeroCartao, LocalDateTime dataCompra, String loja, BigDecimal valor, StatusCompra statusCompra) {
}
