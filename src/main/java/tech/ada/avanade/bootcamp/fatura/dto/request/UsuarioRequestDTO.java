package tech.ada.avanade.bootcamp.fatura.dto.request;

import tech.ada.avanade.bootcamp.fatura.entities.DiaVencimento;

public record UsuarioRequestDTO(String nome, String numeroCartao, DiaVencimento diaVencimento, EnderecoDTO endereco) {
}
