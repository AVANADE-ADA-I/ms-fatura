package tech.ada.avanade.bootcamp.fatura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.avanade.bootcamp.fatura.entities.Fatura;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface FaturaRepository extends JpaRepository<Fatura, Long> {
    Optional<Fatura> findByDataVencimentoAndUsuarioId(LocalDate dataVencimento, Long usuarioId);
}
