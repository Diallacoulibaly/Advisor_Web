package main.java.model.service;

import main.java.model.classes.Client;
import main.java.model.classes.Projet;

import java.sql.SQLException;
import java.util.List;

public interface RecommandationService {
    List<Projet> suggererProjets(Client client) throws SQLException;
}

