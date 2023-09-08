package tech.ada.avanade.bootcamp.fatura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.avanade.bootcamp.fatura.entities.Compra;
import tech.ada.avanade.bootcamp.fatura.entities.Usuario;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CompraRepository extends JpaRepository<Compra, Long> {
    Optional<Compra> findByUsuarioIdAndLojaAndDataCompraAndValor(Long usuarioId, String loja, LocalDateTime dataCompra, BigDecimal valor);
    List<Compra> findAllByUsuarioAndDataCompraBetween(Usuario usuario, LocalDateTime inicio, LocalDateTime fim);
}
