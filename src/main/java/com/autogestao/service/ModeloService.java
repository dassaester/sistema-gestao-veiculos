package com.autogestao.service;

import com.autogestao.model.Marca;
import com.autogestao.model.Modelo;
import com.autogestao.repository.ModeloRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ModeloService {

    private final ModeloRepository modeloRepository;
    private final MarcaService marcaService;

    @Transactional(readOnly = true)
    public List<Modelo> listarTodos() {
        return modeloRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Modelo buscarPorId(Long id) {
        return modeloRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Modelo não encontrado com ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<Modelo> buscarPorMarca(Long marcaId) {
        return modeloRepository.findByMarcaId(marcaId);
    }

    @Transactional(readOnly = true)
    public List<Modelo> buscarPorNome(String nome) {
        return modeloRepository.findByNomeContainingIgnoreCase(nome);
    }

    @Transactional
    public Modelo criar(Modelo modelo, Long marcaId) {
        Marca marca = marcaService.buscarPorId(marcaId);
        modelo.setMarca(marca);
        return modeloRepository.save(modelo);
    }

    @Transactional
    public Modelo atualizar(Long id, Modelo modeloAtualizado) {
        Modelo modelo = buscarPorId(id);
        modelo.setNome(modeloAtualizado.getNome());
        modelo.setDescricao(modeloAtualizado.getDescricao());
        return modeloRepository.save(modelo);
    }

    @Transactional
    public void deletar(Long id) {
        Modelo modelo = buscarPorId(id);
        modeloRepository.delete(modelo);
    }
}