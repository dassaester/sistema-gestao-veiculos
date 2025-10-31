package com.autogestao.repository;

import com.autogestao.model.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByStatus(Veiculo.StatusVeiculo status);

    List<Veiculo> findByModeloId(Long modeloId);

    List<Veiculo> findByModeloMarcaId(Long marcaId);

    List<Veiculo> findByAnoFabricacao(Integer ano);

    List<Veiculo> findByPrecoBetween(BigDecimal precoMin, BigDecimal precoMax);

    List<Veiculo> findByAnoFabricacaoBetween(Integer anoMin, Integer anoMax);

    @Query("SELECT v FROM Veiculo v WHERE " +
            "(:marcaId IS NULL OR v.modelo.marca.id = :marcaId) AND " +
            "(:modeloId IS NULL OR v.modelo.id = :modeloId) AND " +
            "(:anoMin IS NULL OR v.anoFabricacao >= :anoMin) AND " +
            "(:anoMax IS NULL OR v.anoFabricacao <= :anoMax) AND " +
            "(:precoMin IS NULL OR v.preco >= :precoMin) AND " +
            "(:precoMax IS NULL OR v.preco <= :precoMax) AND " +
            "(:status IS NULL OR v.status = :status)")
    List<Veiculo> findByFiltros(
            @Param("marcaId") Long marcaId,
            @Param("modeloId") Long modeloId,
            @Param("anoMin") Integer anoMin,
            @Param("anoMax") Integer anoMax,
            @Param("precoMin") BigDecimal precoMin,
            @Param("precoMax") BigDecimal precoMax,
            @Param("status") Veiculo.StatusVeiculo status
    );
}