package com.github.rluisb.agenda.cucumber.stepdefs;

import com.github.rluisb.agenda.TestConfig;
import com.github.rluisb.agenda.cucumber.World;
import com.github.rluisb.agenda.domain.model.Agenda;
import cucumber.api.java8.En;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.Assert.*;

public class FindAgendaStepdefs extends TestConfig implements En {

    @LocalServerPort
    private int port;
    @Autowired
    private World world;
    @Autowired
    private RestTemplate restTemplate;

    public FindAgendaStepdefs() {
        Given("^an id$", () -> {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ParameterizedTypeReference<List<Agenda>> agendaList = new ParameterizedTypeReference<List<Agenda>>() {
            };

            UriComponentsBuilder builder =
                    UriComponentsBuilder.fromHttpUrl(
                            String.format("http://localhost:%s/agenda-service/v1/agendas", port));

            world.agendas = restTemplate.exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity, agendaList).getBody();

            world.agendaId = world.agendas.get(0).getId();
        });
        Given("^an invalid id$", () -> {
            world.agendaId = UUID.randomUUID().toString();
        });
        When("^request all agendas$", () -> {
            world.status = 200;

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(headers);
            ParameterizedTypeReference<List<Agenda>> agendaList = new ParameterizedTypeReference<List<Agenda>>() {
            };

            UriComponentsBuilder builder =
                    UriComponentsBuilder.fromHttpUrl(
                            String.format("http://localhost:%s/agenda-service/v1/agendas", port));

            try {
                world.agendas = restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.GET,
                        entity, agendaList).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        When("^request an agenda filtering by it's id$", () -> {
            world.status = 200;

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            UriComponentsBuilder builder =
                    UriComponentsBuilder.fromHttpUrl(
                            String.format("http://localhost:%s/agenda-service/v1/agendas/%s", port, world.agendaId));

            try {
                world.agenda = restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.GET,
                        entity, Agenda.class).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        Then("^a list of agendas$", () -> {
            assertNotNull(world.agendas);
            assertFalse(world.agendas.isEmpty());
            assertNotNull(world.agendas.get(0));
        });
        Then("^an agenda with an id equal to the id used to filter$", () -> {
            assertEquals(world.agendaId, world.agenda.getId());
        });
        Then("^a message containing (.*)$", (String message) -> {
            assertTrue(world.errorMessage.contains(message));
        });

    }
}
