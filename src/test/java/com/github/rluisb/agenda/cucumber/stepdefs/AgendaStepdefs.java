package com.github.rluisb.agenda.cucumber.stepdefs;

import com.github.rluisb.agenda.TestConfig;
import com.github.rluisb.agenda.api.dto.AgendaDto;
import com.github.rluisb.agenda.cucumber.World;
import com.github.rluisb.agenda.domain.model.Agenda;
import com.github.rluisb.agenda.domain.model.AgendaStatus;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.Assert.*;

public class AgendaStepdefs extends TestConfig implements En {

    @LocalServerPort
    private int port;
    @Autowired
    private World world;
    @Autowired
    private RestTemplate restTemplate;

    public AgendaStepdefs() {
        Given("^a title (.*)$", (String title) -> {
            world.map.put("title", title);
        });
        Given("^a description (.*)$", (String description) -> {
            world.map.put("description", description);
        });
        Given("^a subject (.*)$", (String subject) -> {
            world.map.put("subject", subject);
        });
        Given("^create a new agenda$", () -> {
            world.status = 200;

            String title = (String) world.map.get("title");
            String description = (String) world.map.get("description");
            String subject = (String) world.map.get("subject");
            AgendaDto agendaDto = new AgendaDto(title, subject, description);

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<AgendaDto> entity = new HttpEntity<>(agendaDto, headers);

            try {
                world.agenda = restTemplate.exchange(String.format("http://localhost:%s/agenda-service/v1/agendas", port),
                        HttpMethod.POST,
                        entity, Agenda.class).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        Then("^should return status (\\d+)$", (Integer status) -> {
            assertEquals(status, world.status);
        });
        Then("^should return a new agenda$", () -> {
            assertNotNull(world.agenda);
        });
        Then("^this agenda should have an id$", () -> {
            assertNotNull(world.agenda.getId());
        });
        Then("^should have a status (.*)$", (AgendaStatus status) -> {
            assertEquals(status, world.agenda.getStatus());
        });
        Then("^should contain a message equal (.*)$", (String errorMessage) -> {
            assertTrue(world.errorMessage.contains(errorMessage));
        });
    }
}
