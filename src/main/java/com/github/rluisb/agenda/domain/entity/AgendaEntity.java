package com.github.rluisb.agenda.domain.entity;

import com.github.rluisb.agenda.domain.model.Agenda;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "agendas")
public class AgendaEntity {

    @Id
    private String id;
    private String title;
    private String subject;
    private String description;

    public AgendaEntity() {
    }

    private AgendaEntity(String title, String subject, String description) {
        this.title = title;
        this.subject = subject;
        this.description = description;
    }

    private AgendaEntity(String id, String title, String subject, String description) {
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

    public static AgendaEntity buildFrom(Agenda agenda) {
        if (Objects.isNull(agenda.getId())) {
            return new AgendaEntity(agenda.getTitle(), agenda.getSubject(), agenda.getDescription());
        }
        return new AgendaEntity(agenda.getId(), agenda.getTitle(), agenda.getSubject(),
                agenda.getDescription());
    }
}
