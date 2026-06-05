package main.java.model.service;

import main.java.model.classes.CompetenceProjet;
import java.util.List;

public interface CompetenceProjetService {
    boolean ajouter(CompetenceProjet competenceProjet);
    CompetenceProjet rechercher(int idProjet, int idCompetence);
    List<Integer> listerIdsCompetencesParProjet(int idProjet);
    List<CompetenceProjet> lister();
    boolean miseAjour(int ancienIdProjet, int ancienIdCompetence, CompetenceProjet nouvelleAssociation);
    boolean supprimer(int idProjet, int idCompetence);
}
