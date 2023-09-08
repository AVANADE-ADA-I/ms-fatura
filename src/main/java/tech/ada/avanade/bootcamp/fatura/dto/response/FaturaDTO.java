package tech.ada.avanade.bootcamp.fatura.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record FaturaDTO(LocalDate dataFaturaGerada, BigDecimal valor, LocalDate dataVencimento, List<CompraResponseDTO> resumoCompra) {
}
