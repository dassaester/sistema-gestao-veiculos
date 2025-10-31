package com.autogestao.service;

import com.autogestao.model.Marca;
import com.autogestao.repository.MarcaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MarcaService {

    private final MarcaRepository marcaRepository;

    @Transactional(readOnly = true)
    public List<Marca> listarTodas() {
        return marcaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Marca buscarPorId(Long id) {
        return marcaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Marca não encontrada com ID: " + id));
    }

    @Transactional
    public Marca criar(Marca marca) {
        if (marcaRepository.existsByNome(marca.getNome())) {
            throw new RuntimeException("Já existe uma marca com este nome");
        }
        return marcaRepository.save(marca);
    }

    @Transactional
    public Marca atualizar(Long id, Marca marcaAtualizada) {
        Marca marca = buscarPorId(id);

        if (!marca.getNome().equals(marcaAtualizada.getNome()) &&
                marcaRepository.existsByNome(marcaAtualizada.getNome())) {
            throw new RuntimeException("Já existe uma marca com este nome");
        }

        marca.setNome(marcaAtualizada.getNome());
        marca.setDescricao(marcaAtualizada.getDescricao());

        return marcaRepository.save(marca);
    }

    @Transactional
    public void deletar(Long id) {
        Marca marca = buscarPorId(id);
        marcaRepository.delete(marca);
    }
}