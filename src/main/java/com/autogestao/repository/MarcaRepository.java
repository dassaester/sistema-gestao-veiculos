package com.autogestao.repository;

import com.autogestao.model.Marca;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MarcaRepository extends JpaRepository<Marca, Long> {
    Optional<Marca> findByNome(String nome);
    boolean existsByNome(String nome);
}