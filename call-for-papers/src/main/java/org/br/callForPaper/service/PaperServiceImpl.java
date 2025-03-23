package org.br.callForPaper.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.br.callForPaper.dto.PaperDetailsDTO;
import org.br.callForPaper.entity.PaperEntity;
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
    @Transactional
    public void createPaper(PaperDetailsDTO paperDetailsDTO) {

        PaperEntity paperEntity = new PaperEntity();
        paperEntity.setTitulo(paperDetailsDTO.getTitulo());
        paperEntity.setEmail(paperDetailsDTO.getEmail());
        paperEntity.setResumo(paperDetailsDTO.getResumo());
        paperEntity.setNomeDoAutor(paperDetailsDTO.getNomeDoAutor());

        try{

            paperRepository.persist( paperEntity );

        }catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException("Error creating paper: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void updatePaper(Long id, PaperDetailsDTO paperDetailsDTO) {

        PaperEntity paperEntity = paperRepository.findById(id);

        if(paperEntity != null){
            paperEntity.setTitulo(paperDetailsDTO.getTitulo());
            paperEntity.setEmail(paperDetailsDTO.getEmail());
            paperEntity.setResumo(paperDetailsDTO.getResumo());
            paperEntity.setNomeDoAutor(paperDetailsDTO.getNomeDoAutor());
            paperRepository.persist(paperEntity);
            paperRepository.flush();
        } else {
            throw new EntityNotFoundException();
        }

    }

    @Override
    public void deletePaper(Long id) {

    }
}
