package tech.ada.avanade.bootcamp.fatura.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tech.ada.avanade.bootcamp.fatura.dto.request.UsuarioRequestDTO;

@Entity
@Table(name = "usuarios")
@Getter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String numeroCartao;
    private DiaVencimento diaVencimento;
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Usuario(UsuarioRequestDTO dto) {
        this.nome = dto.nome();
        this.numeroCartao = dto.numeroCartao();
        this.diaVencimento = dto.diaVencimento();
        this.ativo = true;
        this.endereco = new Endereco(dto.endereco());
    }

    public void desativar() {
        this.ativo = false;
    }

    public void editar(UsuarioRequestDTO dto) {
        if (dto.nome() != null) {
            this.nome = dto.nome();
        }
        if (dto.endereco() != null) {
            this.endereco.editar(dto.endereco());
        }
    }
}
