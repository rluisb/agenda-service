package com.github.rluisb.agenda.service;

import com.github.rluisb.agenda.api.dto.AgendaDto;
import com.github.rluisb.agenda.domain.model.Agenda;

import java.util.List;
import java.util.Optional;

public interface AgendaService {

    List<Agenda> findAllAgendas();

    Optional<Agenda> findAgendaById(String agendaId);

    Optional<Agenda> createNewAgenda(AgendaDto agendaDto);


}
