package com.autogestao.controller;

import com.autogestao.model.Marca;
import com.autogestao.service.MarcaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marcas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class MarcaController {

    private final MarcaService marcaService;

    @GetMapping
    public ResponseEntity<List<Marca>> listarTodas() {
        return ResponseEntity.ok(marcaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Marca> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(marcaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Marca> criar(@Valid @RequestBody Marca marca) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(marcaService.criar(marca));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marca> atualizar(@PathVariable Long id,
                                           @Valid @RequestBody Marca marca) {
        return ResponseEntity.ok(marcaService.atualizar(id, marca));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        marcaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}