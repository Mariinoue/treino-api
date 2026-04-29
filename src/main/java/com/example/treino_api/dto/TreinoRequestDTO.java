package com.example.treino_api.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;
import java.math.BigDecimal;

@Data
public class TreinoRequestDTO {

    @NotBlank(message = "Modalidade é obrigatória")
    @Size(max = 255, message = "Modalidade deve ter no máximo 255 caracteres")
    private String modalidade;

    @DecimalMin(value = "0.0", inclusive = false, message = "Duração deve ser maior que zero")
    private BigDecimal duracao;

    private LocalDateTime dataTreino;
}