package org.br.callForPaper.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
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
    public PaperDetailsDTO createPaper(PaperDetailsDTO paperDetailsDTO) {

        try{
            PaperEntity PaperEntityReturn = buildAndSaveNewPaper(paperDetailsDTO);
            return PaperDetailsDTO.builder()
                   .id(PaperEntityReturn.getId())
                   .titulo(PaperEntityReturn.getTitulo())
                   .email(PaperEntityReturn.getEmail())
                   .nomeDoAutor(PaperEntityReturn.getNomeDoAutor())
                   .resumo(PaperEntityReturn.getResumo()).build();

        }catch (Exception e){
            throw new RuntimeException("Error creating paper: " + e.getMessage(), e);
        }
    }

    @Override
    @Transactional
    public void updatePaper(Long id, PaperDetailsDTO paperDetailsDTO) {

        PaperEntity paperEntity = paperRepository.findById(id);

        if(paperEntity != null){
            paperEntity.setTitulo(paperDetailsDTO.getTitulo());
            paperEntity.setNomeDoAutor(paperDetailsDTO.getNomeDoAutor());
            paperEntity.setResumo(paperDetailsDTO.getResumo());
            paperEntity.setEmail(paperDetailsDTO.getEmail());

            paperRepository.persist(paperEntity);
        } else {
            throw new WebApplicationException("A submissão que deseja editar não existe", Response.Status.NOT_FOUND);
        }

    }

    @Override
    @Transactional
    public void deletePaper(Long id) {
        PaperEntity paperEntity = paperRepository.findById(id);

        if (paperEntity == null) {
            throw new WebApplicationException("O id " + id + " nao existe.", Response.Status.NOT_FOUND);
        }

        paperRepository.deleteById(id);
    }

    private PaperEntity buildAndSaveNewPaper(PaperDetailsDTO proposalDetailsDTO) {
        try{
            PaperEntity paper = new PaperEntity();

            paper.setTitulo(proposalDetailsDTO.getTitulo());
            paper.setNomeDoAutor(proposalDetailsDTO.getNomeDoAutor());
            paper.setResumo(proposalDetailsDTO.getResumo());
            paper.setEmail(proposalDetailsDTO.getEmail());
            paperRepository.persist(paper);
            return paper;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
