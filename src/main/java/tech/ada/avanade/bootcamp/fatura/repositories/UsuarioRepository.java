package tech.ada.avanade.bootcamp.fatura.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tech.ada.avanade.bootcamp.fatura.entities.Usuario;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository <Usuario, Long> {
    Optional<Usuario> findByNumeroCartao(String numeroCartao);
}
