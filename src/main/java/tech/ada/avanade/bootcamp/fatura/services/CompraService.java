package tech.ada.avanade.bootcamp.fatura.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import tech.ada.avanade.bootcamp.fatura.dto.request.CompraRequestDTO;
import tech.ada.avanade.bootcamp.fatura.dto.response.CompraResponseDTO;
import tech.ada.avanade.bootcamp.fatura.entities.Compra;
import tech.ada.avanade.bootcamp.fatura.entities.Usuario;
import tech.ada.avanade.bootcamp.fatura.repositories.CompraRepository;
import tech.ada.avanade.bootcamp.fatura.repositories.UsuarioRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CompraService {
    private CompraRepository compraRepository;
    private UsuarioRepository usuarioRepository;
    public CompraService(CompraRepository compraRepository, UsuarioRepository usuarioRepository) {
        this.compraRepository = compraRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public String executeCadastrar(CompraRequestDTO dto) {
        System.out.println(dto);
        var usuarioOptional = this.usuarioRepository.findByNumeroCartao(dto.numeroCartao());
        if (usuarioOptional.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        var compra = new Compra(dto, usuarioOptional.get());
        this.compraRepository.save(compra);
        return "Cadastrada";
    }

    @Transactional
    public String executeCancelar(CompraRequestDTO dto) {
        var usuarioOptional = this.usuarioRepository.findByNumeroCartao(dto.numeroCartao());
        if (usuarioOptional.isEmpty()) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        var compraOptional = this.compraRepository.findByUsuarioIdAndLojaAndDataCompraAndValor(usuarioOptional.get().getId(), dto.loja(), dto.dataCompra(), dto.valor());
        if (compraOptional.isEmpty()) {
            throw new EntityNotFoundException("Compra não encontrada");
        }
        compraOptional.get().cancelar();
        this.compraRepository.save(compraOptional.get());
        return "Cancelada";
    }

    protected List<CompraResponseDTO> buscarCompras(Usuario usuario, LocalDateTime inicio, LocalDateTime fim) {
        var listaDeCompra = compraRepository.findAllByUsuarioAndDataCompraBetween(usuario, inicio, fim);
        List<CompraResponseDTO> listaCompraResponse = new ArrayList<>();
        for (var compra : listaDeCompra) {
            listaCompraResponse.add(compra.dto());
        }
        return listaCompraResponse;
    }
}
