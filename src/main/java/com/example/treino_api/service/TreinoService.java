package com.example.treino_api.service;

import com.example.treino_api.dto.TreinoRequestDTO;
import com.example.treino_api.dto.TreinoResponseDTO;
import com.example.treino_api.entity.Treino;
import com.example.treino_api.exception.TreinoNotFoundException;
import com.example.treino_api.repository.TreinoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TreinoService {

    private final TreinoRepository treinoRepository;

    @Transactional
    public TreinoResponseDTO criarTreino(TreinoRequestDTO requestDTO) {
        Treino treino = new Treino();
        treino.setModalidade(requestDTO.getModalidade());
        treino.setDuracao(requestDTO.getDuracao());
        treino.setDataTreino(requestDTO.getDataTreino() != null ? requestDTO.getDataTreino() : LocalDateTime.now());

        Treino treinoSalvo = treinoRepository.save(treino);
        return convertToResponseDTO(treinoSalvo);
    }

    @Transactional(readOnly = true)
    public List<TreinoResponseDTO> listarTodosTreinos() {
        return treinoRepository.findAllOrderByDataTreinoDesc()
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TreinoResponseDTO buscarTreinoPorId(Integer id) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new TreinoNotFoundException("Treino não encontrado com ID: " + id));
        return convertToResponseDTO(treino);
    }

    @Transactional
    public TreinoResponseDTO atualizarTreino(Integer id, TreinoRequestDTO requestDTO) {
        Treino treino = treinoRepository.findById(id)
                .orElseThrow(() -> new TreinoNotFoundException("Treino não encontrado com ID: " + id));

        treino.setModalidade(requestDTO.getModalidade());
        treino.setDuracao(requestDTO.getDuracao());
        treino.setDataTreino(requestDTO.getDataTreino());

        Treino treinoAtualizado = treinoRepository.save(treino);
        return convertToResponseDTO(treinoAtualizado);
    }

    @Transactional
    public void deletarTreino(Integer id) {
        if (!treinoRepository.existsById(id)) {
            throw new TreinoNotFoundException("Treino não encontrado com ID: " + id);
        }
        treinoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<TreinoResponseDTO> buscarPorModalidade(String modalidade) {
        return treinoRepository.findByModalidadeContainingIgnoreCase(modalidade)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<TreinoResponseDTO> buscarPorFaixaDuracao(BigDecimal duracaoMin, BigDecimal duracaoMax) {
        return treinoRepository.findByDuracaoBetween(duracaoMin, duracaoMax)
                .stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    private TreinoResponseDTO convertToResponseDTO(Treino treino) {
        TreinoResponseDTO responseDTO = new TreinoResponseDTO();
        responseDTO.setId(treino.getId());
        responseDTO.setModalidade(treino.getModalidade());
        responseDTO.setDuracao(treino.getDuracao());
        responseDTO.setDataTreino(treino.getDataTreino());
        return responseDTO;
    }
}
