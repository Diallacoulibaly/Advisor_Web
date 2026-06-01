package main.java.model.classes;

import org.w3c.dom.Text;

import java.util.Date;

public class Commentaire {
    private int id;
    private String message;
    private Date date_cmt;
    public  Commentaire(Integer id,String message,Date date_cmt){
        this.id=id;
        this.message=message;
        this.date_cmt=date_cmt;
    }
    public  Commentaire(Integer id,String message){
        this.id=id;
        this.message=message;

    }
    public Commentaire(){

    }

    public  Commentaire(String message){
        this.message=message;

    }
    //les getters
    public int getId(){
        return id;
    }

    public String getMessage(){
        return message;
    }

    public Date getDate_cmt() {
        return date_cmt;
    }

    //les setters
    public void  setId(int id){
        this.id=id;
    }
    public  void setMessage(String message){
        this.message=message;
    }
    public  void setDate_cmt(Date date_cmt){this.date_cmt=date_cmt;}
}
