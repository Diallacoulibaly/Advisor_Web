package main.java.model.classes;

public class Domaine {

    private int id;
    private  String domaine;
    public  Domaine(Integer id, String domaine){
        this.id=id;
        this.domaine=domaine;
    }
    public Domaine(){}
    public  Domaine(String domaine){
        this.domaine=domaine;

    }
    //les getters
    public int getId(){
        return id;
    }
    public String  getDomaine(){
        return domaine;
    }
    //les setters
    public void  setId(int id){
        this.id=id;
    }
    public  void setDomaine(String domaine){
        this.domaine=domaine;
    }
}
