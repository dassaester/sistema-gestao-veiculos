package com.autogestao.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "modelos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Modelo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome do modelo é obrigatório")
    @Column(nullable = false)
    private String nome;

    @Column(length = 500)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "marca_id", nullable = false)
    @JsonIgnore
    private Marca marca;

    @OneToMany(mappedBy = "modelo", cascade = CascadeType.ALL)
    private List<Veiculo> veiculos = new ArrayList<>();
}