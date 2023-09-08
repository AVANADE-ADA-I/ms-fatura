package tech.ada.avanade.bootcamp.fatura.services;

import org.springframework.stereotype.Service;
import tech.ada.avanade.bootcamp.fatura.dto.response.CompraResponseDTO;
import tech.ada.avanade.bootcamp.fatura.dto.response.FaturaDTO;
import tech.ada.avanade.bootcamp.fatura.entities.Fatura;
import tech.ada.avanade.bootcamp.fatura.entities.Usuario;
import tech.ada.avanade.bootcamp.fatura.repositories.FaturaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class FaturaService {
    private FaturaRepository faturaRepository;
    private CompraService compraService;
    private UsuarioService usuarioService;
    public FaturaService(FaturaRepository faturaRepository, CompraService compraService, UsuarioService usuarioService) {
        this.faturaRepository = faturaRepository;
        this.compraService = compraService;
        this.usuarioService = usuarioService;
    }
    private int diasParaSubtrair = 7;

    public FaturaDTO executeBuscar(String numeroCartao, Integer mes, Integer ano) {
        var usuario = usuarioService.buscarUsuario(numeroCartao);
        var dataVencimento = LocalDate.of(ano, mes, usuario.getDiaVencimento().getDia());
        var faturaOptional = faturaRepository.findByDataVencimentoAndUsuarioId(dataVencimento, usuario.getId());
        if(faturaOptional.isEmpty()) {
            return this.criarFatura(usuario, dataVencimento);
        }
        List<CompraResponseDTO> compras = this.pegarCompras(usuario, dataVencimento);
        return faturaOptional.get().dto(compras);
    }

    private FaturaDTO criarFatura(Usuario usuario,  LocalDate dataVencimento) {

        List<CompraResponseDTO> compras = this.pegarCompras(usuario, dataVencimento);
        BigDecimal totalCompras = compras.stream()
                .map(CompraResponseDTO::valor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        BigDecimal valor = totalCompras.add(this.balancoFaturaAnterior(usuario, dataVencimento));

        LocalDate dataProcessamento = dataVencimento.minusDays(diasParaSubtrair);

        var novaFatura = new Fatura(dataVencimento, dataProcessamento, valor, BigDecimal.ZERO, usuario );
        faturaRepository.save(novaFatura);
        return novaFatura.dto(compras);
    }

    private BigDecimal balancoFaturaAnterior(Usuario usuario, LocalDate dataVencimento) {
        LocalDate dataVencimentoAnterior = dataVencimento.minusMonths(1);
        var faturaAnterior = faturaRepository.findByDataVencimentoAndUsuarioId(dataVencimentoAnterior, usuario.getId());

        return faturaAnterior.map(fatura -> fatura.getValor().subtract(fatura.getValorPago())).orElse(BigDecimal.ZERO);
    }

    private List<CompraResponseDTO> pegarCompras(Usuario usuario, LocalDate dataVencimento){
        LocalDate dataFinal = dataVencimento.minusDays(diasParaSubtrair);
        LocalDate dataInicial = dataFinal.minusMonths(1).plusDays(1);

        return compraService.buscarCompras(usuario, dataInicial.atStartOfDay(), dataFinal.atTime(LocalTime.MAX));
    }
}
