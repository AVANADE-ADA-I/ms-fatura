package tech.ada.avanade.bootcamp.fatura.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.ada.avanade.bootcamp.fatura.dto.request.EnderecoDTO;

@Embeddable
@Getter
@NoArgsConstructor
public class Endereco {
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;
    private String numero;

    public Endereco(EnderecoDTO dto) {
        this.cep = dto.cep();
        this.rua = dto.rua();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.numero = dto.numero();
        if (dto.complemento() != null) {
            this.complemento = dto.complemento();
        }
    }

    public void editar(EnderecoDTO dto) {
        if (dto.cep() != null) {
            this.cep = dto.cep();
        }
        if (dto.rua() != null) {
            this.rua = dto.rua();
        }
        if (dto.bairro() != null) {
            this.bairro = dto.bairro();
        }
        if (dto.cidade() != null) {
            this.cidade = dto.cidade();
        }
        if (dto.uf() != null) {
            this.uf = dto.uf();
        }
        if (dto.numero() != null) {
            this.numero = dto.numero();
        }
        this.complemento = dto.complemento();
    }
}
