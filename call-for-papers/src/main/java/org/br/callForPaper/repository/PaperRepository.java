package org.br.callForPaper.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.br.callForPaper.entity.PaperEntity;

@ApplicationScoped
public class PaperRepository implements PanacheRepository<PaperEntity> {

}
