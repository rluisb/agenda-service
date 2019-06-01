package com.github.rluisb.agenda.repository;

import com.github.rluisb.agenda.domain.entity.AgendaEntity;
import com.github.rluisb.agenda.domain.model.AgendaStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends MongoRepository<AgendaEntity, String> {
    List<AgendaEntity> findAllByStatus(AgendaStatus agendaStatus);

}
