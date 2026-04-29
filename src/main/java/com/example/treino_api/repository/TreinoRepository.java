package com.example.treino_api.repository;

import com.example.treino_api.entity.Treino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TreinoRepository extends JpaRepository<Treino, Integer> {

    List<Treino> findByModalidadeContainingIgnoreCase(String modalidade);

    List<Treino> findByDuracaoBetween(BigDecimal duracaoMin, BigDecimal duracaoMax);

    List<Treino> findByDataTreinoBetween(LocalDateTime dataInicio, LocalDateTime dataFim);

    @Query("SELECT t FROM Treino t ORDER BY t.dataTreino DESC")
    List<Treino> findAllOrderByDataTreinoDesc();
}
