package com.github.rluisb.agenda;


import com.github.rluisb.agenda.cucumber.World;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoProperties;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = {
                AgendaServiceApplication.class,
                World.class
        })
public class TestConfig {

    EmbeddedMongoProperties embeddedMongoProperties = new EmbeddedMongoProperties();

    public EmbeddedMongoProperties getEmbeddedMongoProperties() {
        return embeddedMongoProperties;
    }
}
