package com.autogestao.service;

import com.autogestao.dto.VeiculoDTO;
import com.autogestao.model.Modelo;
import com.autogestao.model.Veiculo;
import com.autogestao.repository.VeiculoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VeiculoService {

    private final VeiculoRepository veiculoRepository;
    private final ModeloService modeloService;

    @Transactional(readOnly = true)
    public List<VeiculoDTO> listarTodos() {
        return veiculoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public VeiculoDTO buscarPorId(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com ID: " + id));
        return convertToDTO(veiculo);
    }

    @Transactional(readOnly = true)
    public List<VeiculoDTO> buscarPorStatus(Veiculo.StatusVeiculo status) {
        return veiculoRepository.findByStatus(status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VeiculoDTO> buscarPorMarca(Long marcaId) {
        return veiculoRepository.findByModeloMarcaId(marcaId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VeiculoDTO> buscarPorModelo(Long modeloId) {
        return veiculoRepository.findByModeloId(modeloId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<VeiculoDTO> buscarComFiltros(Long marcaId, Long modeloId,
                                             Integer anoMin, Integer anoMax,
                                             BigDecimal precoMin, BigDecimal precoMax,
                                             Veiculo.StatusVeiculo status) {
        return veiculoRepository.findByFiltros(marcaId, modeloId, anoMin, anoMax,
                        precoMin, precoMax, status).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public VeiculoDTO criar(VeiculoDTO dto) {
        Veiculo veiculo = convertToEntity(dto);
        Modelo modelo = modeloService.buscarPorId(dto.getModeloId());
        veiculo.setModelo(modelo);
        Veiculo veiculoSalvo = veiculoRepository.save(veiculo);
        return convertToDTO(veiculoSalvo);
    }

    @Transactional
    public VeiculoDTO atualizar(Long id, VeiculoDTO dto) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com ID: " + id));

        if (dto.getModeloId() != null && !dto.getModeloId().equals(veiculo.getModelo().getId())) {
            Modelo modelo = modeloService.buscarPorId(dto.getModeloId());
            veiculo.setModelo(modelo);
        }

        veiculo.setAnoFabricacao(dto.getAnoFabricacao());
        veiculo.setCor(dto.getCor());
        veiculo.setPreco(dto.getPreco());
        veiculo.setQuilometragem(dto.getQuilometragem());
        veiculo.setStatus(dto.getStatus());

        Veiculo veiculoAtualizado = veiculoRepository.save(veiculo);
        return convertToDTO(veiculoAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        Veiculo veiculo = veiculoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado com ID: " + id));
        veiculoRepository.delete(veiculo);
    }

    private VeiculoDTO convertToDTO(Veiculo veiculo) {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setId(veiculo.getId());
        dto.setModeloId(veiculo.getModelo().getId());
        dto.setModeloNome(veiculo.getModelo().getNome());
        dto.setMarcaNome(veiculo.getModelo().getMarca().getNome());
        dto.setAnoFabricacao(veiculo.getAnoFabricacao());
        dto.setCor(veiculo.getCor());
        dto.setPreco(veiculo.getPreco());
        dto.setQuilometragem(veiculo.getQuilometragem());
        dto.setStatus(veiculo.getStatus());
        return dto;
    }

    private Veiculo convertToEntity(VeiculoDTO dto) {
        Veiculo veiculo = new Veiculo();
        veiculo.setAnoFabricacao(dto.getAnoFabricacao());
        veiculo.setCor(dto.getCor());
        veiculo.setPreco(dto.getPreco());
        veiculo.setQuilometragem(dto.getQuilometragem());
        veiculo.setStatus(dto.getStatus());
        return veiculo;
    }
}