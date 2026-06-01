package main.java.model.classes;

import java.time.LocalDate;

public class Historique {

    int id;
    LocalDate date;
    //int budgetApporte;
    String descriptionAction;

    public Historique() {
    }

    public Historique(int id, LocalDate date, int budgetApporte, String descriptionAction ) {
        this.id = id;
        this.date = date;
        //this.budgetApporte = budgetApporte;
        this.descriptionAction = descriptionAction;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    /*public int getBudgetApporte(){
        return budgetApporte;
    }*/

    public String getDescriptionAction(){return descriptionAction; }

    public void setId(int id){
        this.id = id;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    /*public void setBudgetApporte(int budgetApporte){
        this.budgetApporte = budgetApporte;
    }*/

    public void setDescriptionAction(String descriptionAction ) {this.descriptionAction = descriptionAction;}

}