package com.example.treino_api.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class TreinoResponseDTO {
    private Integer id;
    private String modalidade;
    private BigDecimal duracao;
    private LocalDateTime dataTreino;
}
