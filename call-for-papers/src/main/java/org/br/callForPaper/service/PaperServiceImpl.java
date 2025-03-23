package org.br.callForPaper.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.br.callForPaper.dto.PaperDetailsDTO;
import org.br.callForPaper.repository.PaperRepository;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class PaperServiceImpl  implements PaperService{

    @Inject
    PaperRepository paperRepository;

    @Override
    public List<PaperDetailsDTO> listPapers() {
        var paperEntities = paperRepository.findAll().list(); //verificar mais necessidades de porteger contra listas vazias

        return paperEntities.stream()
                .map(paperEntity -> PaperDetailsDTO.builder()
                        .id(paperEntity.getId())
                        .titulo(paperEntity.getTitulo())
                        .email(paperEntity.getEmail())
                        .nomeDoAutor(paperEntity.getNomeDoAutor())
                        .resumo(paperEntity.getResumo())
                        .build())
                .collect(Collectors.toList());
    }


    @Override
    public void createPaper(PaperDetailsDTO paperDetailsDTO) {

    }

    @Override
    public void updatePaper(Long id, PaperDetailsDTO paperDetailsDTO) {

    }

    @Override
    public void deletePaper(Long id) {

    }
}
