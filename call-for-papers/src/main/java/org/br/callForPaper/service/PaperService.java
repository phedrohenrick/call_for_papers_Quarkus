package org.br.callForPaper.service;

import org.br.callForPaper.dto.PaperDetailsDTO;

import java.util.List;

public interface PaperService {

    List<PaperDetailsDTO> listPapers();

    void createPaper(PaperDetailsDTO paperDetailsDTO);

    void updatePaper(Long id, PaperDetailsDTO paperDetailsDTO);

    void deletePaper(Long id);
}
