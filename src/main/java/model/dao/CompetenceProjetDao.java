package main.java.model.dao;

import main.java.model.classes.CompetenceProjet;
import java.util.List;

public interface CompetenceProjetDao {
    boolean add_CP(CompetenceProjet competenceProjet);
    CompetenceProjet rech_CP(int idProjet, int idCompetence);
    List<Integer> rech_CP(int idProjet);
    List<CompetenceProjet> ListeCP();
    boolean mise_a_jour_CP(int ancienIdProjet, int ancienIdCompetence, CompetenceProjet nouvelleAssociation);
    boolean suppr_CP (int idProjet, int idCompetence);
}
