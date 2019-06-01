package com.github.rluisb.agenda.api;

import com.github.rluisb.agenda.service.AgendaService;
import com.github.rluisb.agenda.api.dto.AgendaDto;
import com.github.rluisb.agenda.domain.model.AgendaStatus;
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
    public ResponseEntity<?> getAllAgendas(@RequestParam(value = "status", required = false) AgendaStatus status) {
        if (Objects.nonNull(status)) {
            return Stream.of(status)
                    .map(agendaService::findAgendaByStatus)
                    .filter(agendas -> !agendas.isEmpty())
                    .map(ResponseEntity::ok)
                    .findFirst()
                    .get();
        }
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

    @PatchMapping("/agendas/{id}")
    public ResponseEntity<?> updateAgendaStatus(@PathVariable("id") String id,
                                                @RequestParam("status") AgendaStatus newStatus) {
        return Stream.of(id)
                .filter(Objects::nonNull)
                .map(actualId -> agendaService.updateAgendaStatus(actualId, newStatus))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(ResponseEntity::ok)
                .findFirst()
                .get();
    }
}
