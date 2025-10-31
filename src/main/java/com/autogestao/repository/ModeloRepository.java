package com.autogestao.repository;

import com.autogestao.model.Modelo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ModeloRepository extends JpaRepository<Modelo, Long> {
    List<Modelo> findByMarcaId(Long marcaId);
    List<Modelo> findByNomeContainingIgnoreCase(String nome);
}