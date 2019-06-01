package com.github.rluisb.agenda.service.impl;

import com.github.rluisb.agenda.repository.AgendaRepository;
import com.github.rluisb.agenda.service.AgendaService;
import com.github.rluisb.agenda.api.dto.AgendaDto;
import com.github.rluisb.agenda.domain.entity.AgendaEntity;
import com.github.rluisb.agenda.domain.model.Agenda;
import com.github.rluisb.agenda.domain.model.AgendaStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AgendaServiceImpl implements AgendaService {

    private AgendaRepository agendaRepository;

    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public List<Agenda> findAllAgendas() {
        return agendaRepository.findAll()
                .stream()
                .map(Agenda::buildFrom)
                .collect(Collectors.toList());
    }

    @Override
    public List<Agenda> findAgendaByStatus(AgendaStatus status) {
        return agendaRepository.findAllByStatus(status)
                .stream()
                .map(Agenda::buildFrom)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Agenda> createNewAgenda(AgendaDto agendaDto) {
        return Stream.of(agendaDto)
                .filter(Objects::nonNull)
                .map(Agenda::buildFrom)
                .map(AgendaEntity::buildFrom)
                .map(agendaRepository::save)
                .map(Agenda::buildFrom)
                .findFirst();
    }

    @Override
    public Optional<Agenda> updateAgendaStatus(String agendaId, AgendaStatus status) {
        return Stream.of(agendaId)
                .filter(Objects::nonNull)
                .map(this::findAgendaById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(agenda -> agenda.updateStatus(status))
                .map(AgendaEntity::buildFrom)
                .map(agendaRepository::save)
                .map(Agenda::buildFrom)
                .findFirst();
    }

    @Override
    public Optional<Agenda> findAgendaById(String agendaId) {
        return Stream.of(agendaId)
                .filter(Objects::nonNull)
                .map(agendaRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(Agenda::buildFrom)
                .findFirst();
    }
}
