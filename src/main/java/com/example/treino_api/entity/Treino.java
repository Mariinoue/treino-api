package com.example.treino_api.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "treinos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treino {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String modalidade;

    @Column(precision = 10, scale = 2)
    private BigDecimal duracao;

    @Column(name = "data_treino")
    private LocalDateTime dataTreino;

    @PrePersist
    protected void onCreate() {
        if (dataTreino == null) {
            dataTreino = LocalDateTime.now();
        }
    }
}
