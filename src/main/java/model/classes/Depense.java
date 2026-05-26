//package classes;
//
//import java.sql.Date;
//
//public class Depense {
//
//    private Integer idDepense;
//    private double montant;
//    private String description;
//    private Date date;
//    private Activite activite;
//
//    public Depense(Integer idDepense, double montant, String description, Date date,Activite activite) {
//        this.idDepense = idDepense;
//        this.montant = montant;
//        this.description = description;
//        this.date = date;
//        this.activite = activite;
//    }
//    public Depense() {}
//
//    public Integer getIdDepense() {
//        return idDepense;
//    }
//
//    public double getMontant() {
//        return montant;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//    public Activite getActivite(){
//        return activite;
//    }
//
//    public void setMontant(double montant) {
//        if (montant >= 0) {
//            this.montant = montant;
//        } else {
//            System.out.println("montant negatif");
//        }
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//    public void setActivite(Activite activite){
//        this.activite=activite;
//    }
//    public void setDate(Date date) {
//        this.date = date;
//    }
//
//
//   /* public void afficherDepense() {
//
//        System.out.println("ID : " + idDepense);
//        System.out.println("Montant : " + montant);
//        System.out.println("Description : " + description);
//        System.out.println("Date : " + date);
//    }*/
//
//
//
//}