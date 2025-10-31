package com.autogestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.math.BigDecimal;

@Entity
@Table(name = "veiculos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "modelo_id", nullable = false)
    @JsonIgnore
    private Modelo modelo;

    @NotNull(message = "Ano de fabricação é obrigatório")
    @Min(value = 1900, message = "Ano deve ser maior que 1900")
    @Max(value = 2100, message = "Ano deve ser menor que 2100")
    @Column(nullable = false)
    private Integer anoFabricacao;

    @NotBlank(message = "Cor é obrigatória")
    @Column(nullable = false)
    private String cor;

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false, message = "Preço deve ser maior que zero")
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal preco;

    @NotNull(message = "Quilometragem é obrigatória")
    @Min(value = 0, message = "Quilometragem não pode ser negativa")
    @Column(nullable = false)
    private Integer quilometragem;

    @NotNull(message = "Status é obrigatório")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusVeiculo status;

    public enum StatusVeiculo {
        DISPONIVEL,
        VENDIDO,
        RESERVADO,
        EM_MANUTENCAO
    }
}