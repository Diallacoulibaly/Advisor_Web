package main.java.model.classes;

import java.time.LocalDate;

public class Historique {

    int id;
    LocalDate date;
    int budgetApporte;

    public Historique() {
    }

    public Historique(int id, LocalDate date, int budgetApporte) {
        this.id = id;
        this.date = date;
        this.budgetApporte = budgetApporte;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public int getBudgetApporte(){
        return budgetApporte;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setBudgetApporte(int budgetApporte){
        this.budgetApporte = budgetApporte;
    }

}