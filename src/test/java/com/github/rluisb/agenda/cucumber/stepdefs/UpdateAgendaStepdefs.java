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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class UpdateAgendaStepdefs extends TestConfig implements En {

    @LocalServerPort
    private int port;
    @Autowired
    private World world;
    @Autowired
    private RestTemplate restTemplate;

    public UpdateAgendaStepdefs() {
        When("^update the agenda status$", () -> {
            world.status = 200;

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<?> entity = new HttpEntity<>(headers);

            UriComponentsBuilder builder =
                    UriComponentsBuilder.fromHttpUrl(
                            String.format("http://localhost:%s/agenda-service/v1/agendas/%s", port, world.agendaId))
                            .queryParam("status", world.agendaStatus);

            try {
                world.agenda = restTemplate.exchange(
                        builder.toUriString(),
                        HttpMethod.PATCH,
                        entity, Agenda.class).getBody();
            } catch (HttpServerErrorException | HttpClientErrorException e) {
                world.errorMessage = e.getResponseBodyAsString();
                world.status = e.getRawStatusCode();
            }
        });
        And("^updated agenda$", () -> {
            assertNotNull(world.agenda);
        });
        And("^a new agenda's status equal to status used to update$", () -> {
            assertEquals(world.agendaStatus, world.agenda.getStatus());
        });
    }
}
