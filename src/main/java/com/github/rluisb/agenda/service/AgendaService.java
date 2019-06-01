package com.github.rluisb.agenda.service;

import com.github.rluisb.agenda.api.dto.AgendaDto;
import com.github.rluisb.agenda.domain.model.Agenda;
import com.github.rluisb.agenda.domain.model.AgendaStatus;

import java.util.List;
import java.util.Optional;

public interface AgendaService {

    List<Agenda> findAllAgendas();

    List<Agenda> findAgendaByStatus(AgendaStatus status);

    Optional<Agenda> findAgendaById(String agendaId);

    Optional<Agenda> createNewAgenda(AgendaDto agendaDto);

    Optional<Agenda> updateAgendaStatus(String agendaId, AgendaStatus status);

}
