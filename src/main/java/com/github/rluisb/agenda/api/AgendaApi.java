package com.github.rluisb.agenda.api;

import com.github.rluisb.agenda.api.dto.AgendaDto;
import com.github.rluisb.agenda.service.AgendaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
public class AgendaApi {

    private AgendaService agendaService;

    public AgendaApi(AgendaService agendaService) {
        this.agendaService = agendaService;
    }

    @GetMapping("/agendas/{id}")
    public ResponseEntity<?> getAgendaById(@PathVariable("id") String agendaId) {
        return Stream.of(agendaId)
                .filter(Objects::nonNull)
                .map(agendaService::findAgendaById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    @GetMapping("/agendas")
    public ResponseEntity<?> getAllAgendas() {
        return Stream.of(agendaService.findAllAgendas())
                .filter(Objects::nonNull)
                .filter(agendas -> !agendas.isEmpty())
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

    @PostMapping("/agendas")
    public ResponseEntity<?> createAgenda(@Valid @RequestBody AgendaDto agendaDto) {
        return Stream.of(agendaDto)
                .filter(Objects::nonNull)
                .map(agendaService::createNewAgenda)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }

}
