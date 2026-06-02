package main.java.model.service;

import main.java.model.classes.ClientCompetence;

import java.util.List;

public interface ClientCompetenceService {

    void add(ClientCompetence clientCompetence);

    List<ClientCompetence> getAll();

    void update(ClientCompetence cc, int oldIdClient, int oldIdCompetence);

    void delete(int idClient, int idCompetence);

}
