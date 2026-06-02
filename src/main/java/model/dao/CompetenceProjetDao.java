package main.java.model.dao;

import main.java.model.classes.CompetenceProjet;
import java.util.List;

public interface CompetenceProjetDao {
    boolean add_CP(CompetenceProjet competenceProjet);
    List<Integer> rech_CP(int idProjet);
    List<CompetenceProjet> ListeCP();
    boolean mise_a_jour_CP(CompetenceProjet cp);
    boolean suppr_CP(int id);
    boolean verif_CP(int id);
    boolean suppr_CP(int idCompetence, int idProjet);
}
