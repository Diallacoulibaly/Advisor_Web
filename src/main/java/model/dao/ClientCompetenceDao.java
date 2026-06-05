package main.java.model.dao;

import main.java.model.classes.ClientCompetence;

import java.sql.SQLException;
import java.util.List;

public interface ClientCompetenceDao {
    void add(ClientCompetence clientCompetence);
    List<ClientCompetence> getAll();
    void update(ClientCompetence cc, int oldIdClient, int oldIdCompetence);
    void delete(int idClient, int idCompetence);
    List<Integer> getSkillsByClient(int idClient) throws SQLException;
}