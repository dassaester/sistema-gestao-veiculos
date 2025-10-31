package com.autogestao.dto;

import com.autogestao.model.Veiculo;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoDTO {

    private Long id;

    @NotNull(message = "ID do modelo é obrigatório")
    private Long modeloId;

    private String marcaNome;
    private String modeloNome;

    @NotNull(message = "Ano de fabricação é obrigatório")
    @Min(value = 1900, message = "Ano deve ser maior que 1900")
    @Max(value = 2100, message = "Ano deve ser menor que 2100")
    private Integer anoFabricacao;

    @NotBlank(message = "Cor é obrigatória")
    private String cor;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    private BigDecimal preco;

    @NotNull(message = "Quilometragem é obrigatória")
    @Min(value = 0, message = "Quilometragem não pode ser negativa")
    private Integer quilometragem;

    @NotNull(message = "Status é obrigatório")
    private Veiculo.StatusVeiculo status;
}