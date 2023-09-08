package tech.ada.avanade.bootcamp.fatura.dto.response;

import tech.ada.avanade.bootcamp.fatura.entities.StatusCompra;

import java.math.BigDecimal;

public record CompraResponseDTO(String numeroCartao, String loja, BigDecimal valor, StatusCompra statusCompra) {
}
