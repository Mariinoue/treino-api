package com.example.treino_api.controller;

import com.example.treino_api.dto.TreinoRequestDTO;
import com.example.treino_api.dto.TreinoResponseDTO;
import com.example.treino_api.service.TreinoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/treinos")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class TreinoController {

    private final TreinoService treinoService;

    @PostMapping
    public ResponseEntity<TreinoResponseDTO> criarTreino(@Valid @RequestBody TreinoRequestDTO requestDTO) {
        TreinoResponseDTO responseDTO = treinoService.criarTreino(requestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<TreinoResponseDTO>> listarTodosTreinos() {
        List<TreinoResponseDTO> treinos = treinoService.listarTodosTreinos();
        return ResponseEntity.ok(treinos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TreinoResponseDTO> buscarTreinoPorId(@PathVariable Integer id) {
        TreinoResponseDTO responseDTO = treinoService.buscarTreinoPorId(id);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TreinoResponseDTO> atualizarTreino(
            @PathVariable Integer id,
            @Valid @RequestBody TreinoRequestDTO requestDTO) {
        TreinoResponseDTO responseDTO = treinoService.atualizarTreino(id, requestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTreino(@PathVariable Integer id) {
        treinoService.deletarTreino(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<TreinoResponseDTO>> buscarPorModalidade(@RequestParam String modalidade) {
        List<TreinoResponseDTO> treinos = treinoService.buscarPorModalidade(modalidade);
        return ResponseEntity.ok(treinos);
    }

    @GetMapping("/buscar-por-duracao")
    public ResponseEntity<List<TreinoResponseDTO>> buscarPorFaixaDuracao(
            @RequestParam BigDecimal min,
            @RequestParam BigDecimal max) {
        List<TreinoResponseDTO> treinos = treinoService.buscarPorFaixaDuracao(min, max);
        return ResponseEntity.ok(treinos);
    }
}
