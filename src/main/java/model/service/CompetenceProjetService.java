package main.java.model.service;

import main.java.model.classes.CompetenceProjet;
import java.util.List;

public interface CompetenceProjetService {

    boolean ajouter(CompetenceProjet competenceProjet);
    List<Integer> rechercher(int id);
    List<CompetenceProjet> lister();
    boolean mettre_a_jour(CompetenceProjet competenceProjet);
    boolean supprimer(int id);
    boolean verifier(int id);
}