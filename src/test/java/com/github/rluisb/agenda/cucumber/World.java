package com.github.rluisb.agenda.cucumber;

import com.github.rluisb.agenda.domain.model.Agenda;
import com.google.common.collect.Maps;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@Scope("cucumber-glue")
public class World {

    public Map<String, Object> map = Maps.newHashMap();
    public Integer status;
    public String errorMessage;
    public Agenda agenda;
    public List<Agenda> agendas;
    public String agendaId;
}
