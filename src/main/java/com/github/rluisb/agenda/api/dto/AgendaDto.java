package com.github.rluisb.agenda.api.dto;

import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Validated
public class AgendaDto {

    @NotNull(message = "The title can't be null.")
    @NotBlank(message = "The title can't be empty.")
    private String title;
    @NotNull(message = "The subject can't be null.")
    @NotBlank(message = "The subject can't be empty.")
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
