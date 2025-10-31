package com.autogestao.controller;

import com.autogestao.dto.VeiculoDTO;
import com.autogestao.model.Veiculo;
import com.autogestao.service.VeiculoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/veiculos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class VeiculoController {

    private final VeiculoService veiculoService;

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> listarTodos() {
        return ResponseEntity.ok(veiculoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VeiculoDTO> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(veiculoService.buscarPorId(id));
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<VeiculoDTO>> buscarPorStatus(@PathVariable Veiculo.StatusVeiculo status) {
        return ResponseEntity.ok(veiculoService.buscarPorStatus(status));
    }

    @GetMapping("/marca/{marcaId}")
    public ResponseEntity<List<VeiculoDTO>> buscarPorMarca(@PathVariable Long marcaId) {
        return ResponseEntity.ok(veiculoService.buscarPorMarca(marcaId));
    }

    @GetMapping("/modelo/{modeloId}")
    public ResponseEntity<List<VeiculoDTO>> buscarPorModelo(@PathVariable Long modeloId) {
        return ResponseEntity.ok(veiculoService.buscarPorModelo(modeloId));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<VeiculoDTO>> buscarComFiltros(
            @RequestParam(required = false) Long marcaId,
            @RequestParam(required = false) Long modeloId,
            @RequestParam(required = false) Integer anoMin,
            @RequestParam(required = false) Integer anoMax,
            @RequestParam(required = false) BigDecimal precoMin,
            @RequestParam(required = false) BigDecimal precoMax,
            @RequestParam(required = false) Veiculo.StatusVeiculo status) {
        return ResponseEntity.ok(
                veiculoService.buscarComFiltros(marcaId, modeloId, anoMin, anoMax,
                        precoMin, precoMax, status)
        );
    }

    @PostMapping
    public ResponseEntity<VeiculoDTO> criar(@Valid @RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(veiculoService.criar(veiculoDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VeiculoDTO> atualizar(@PathVariable Long id,
                                                @Valid @RequestBody VeiculoDTO veiculoDTO) {
        return ResponseEntity.ok(veiculoService.atualizar(id, veiculoDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        veiculoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}