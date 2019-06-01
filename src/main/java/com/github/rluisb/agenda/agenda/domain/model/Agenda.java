package com.github.rluisb.agenda.agenda.domain.model;

import com.github.rluisb.agenda.agenda.api.dto.AgendaDto;
import com.github.rluisb.agenda.agenda.domain.entity.AgendaEntity;

import java.io.Serializable;

public class Agenda implements Serializable {

    private String id;
    private String title;
    private String subject;
    private String description;
    private AgendaStatus status;


    public Agenda() {
    }

    private Agenda(AgendaDto agendaDto) {
        this.title = agendaDto.getTitle();
        this.subject = agendaDto.getSubject();
        this.description = agendaDto.getDescription();
        this.status = AgendaStatus.WAITING_FOR_VOTING;
    }

    private Agenda(Agenda agenda, AgendaStatus newStatus) {
        this.id = agenda.getId();
        this.title = agenda.getTitle();
        this.subject = agenda.getSubject();
        this.description = agenda.getDescription();
        this.status = newStatus;
    }

    private Agenda(String id, String title, String subject, String description, AgendaStatus status) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.description = description;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSubject() {
        return subject;
    }

    public String getDescription() {
        return description;
    }

    public AgendaStatus getStatus() {
        return status;
    }

    public Agenda updateStatus(AgendaStatus agendaStatus) {
        return new Agenda(this, agendaStatus);
    }

    public static Agenda buildFrom(AgendaDto agendaDto) {
        return new Agenda(agendaDto);
    }

    public static Agenda buildFrom(AgendaEntity agendaEntity) {
        return new Agenda(agendaEntity.getId(), agendaEntity.getTitle(), agendaEntity.getSubject(),
                agendaEntity.getDescription(), agendaEntity.getStatus());
    }
}
