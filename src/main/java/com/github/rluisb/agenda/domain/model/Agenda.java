package com.github.rluisb.agenda.domain.model;

import com.github.rluisb.agenda.api.dto.AgendaDto;
import com.github.rluisb.agenda.domain.entity.AgendaEntity;

import java.io.Serializable;

public class Agenda implements Serializable {

    private String id;
    private String title;
    private String subject;
    private String description;


    public Agenda() {
    }

    private Agenda(AgendaDto agendaDto) {
        this.title = agendaDto.getTitle();
        this.subject = agendaDto.getSubject();
        this.description = agendaDto.getDescription();
    }

    private Agenda(String id, String title, String subject, String description) {
        this.id = id;
        this.title = title;
        this.subject = subject;
        this.description = description;
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

    public static Agenda buildFrom(AgendaDto agendaDto) {
        return new Agenda(agendaDto);
    }

    public static Agenda buildFrom(AgendaEntity agendaEntity) {
        return new Agenda(agendaEntity.getId(), agendaEntity.getTitle(), agendaEntity.getSubject(),
                agendaEntity.getDescription());
    }
}
