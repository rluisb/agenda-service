package com.github.rluisb.agenda.agenda.api.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public class AgendaDto {

    @NotNull(message = "The title must be filled.")
    @NotBlank(message = "The title must be filled.")
    private String title;
    @NotNull(message = "The subject must be filled.")
    @NotBlank(message = "The subject must be filled.")
    private String subject;
    private String description;

    public AgendaDto() {
    }

    public AgendaDto(String title, String subject, String description) {
        this.title = title;
        this.subject = subject;
        this.description = description;
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
}
