package com.github.rluisb.agenda.cucumber.stepdefs;

import com.github.rluisb.agenda.agenda.AgendaServiceApplication;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.test.context.ContextConfiguration;

@RunWith(Cucumber.class)
@ContextConfiguration(loader = SpringBootContextLoader.class, classes = AgendaServiceApplication.class)
@CucumberOptions(features = "src/test/resources/features", glue = "com.github.rluisb.agenda")
public class CucumberTestsRunner {
}

