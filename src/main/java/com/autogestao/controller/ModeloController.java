package com.autogestao.controller;

import com.autogestao.model.Modelo;
import com.autogestao.service.ModeloService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modelos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ModeloController {

    private final ModeloService modeloService;

    @GetMapping
    public ResponseEntity<List<Modelo>> listarTodos() {
        return ResponseEntity.ok(modeloService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modelo> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(modeloService.buscarPorId(id));
    }

    @GetMapping("/marca/{marcaId}")
    public ResponseEntity<List<Modelo>> buscarPorMarca(@PathVariable Long marcaId) {
        return ResponseEntity.ok(modeloService.buscarPorMarca(marcaId));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Modelo>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(modeloService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<Modelo> criar(@Valid @RequestBody Modelo modelo,
                                        @RequestParam Long marcaId) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(modeloService.criar(modelo, marcaId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modelo> atualizar(@PathVariable Long id,
                                            @Valid @RequestBody Modelo modelo) {
        return ResponseEntity.ok(modeloService.atualizar(id, modelo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        modeloService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}