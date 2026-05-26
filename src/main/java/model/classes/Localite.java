package main.java.model.classes;

public class Localite {
    public Integer id;
    public String regionClient;

    public Localite(){

    }
    public Localite(Integer id, String regionClient){
        this.id = id;
        this.regionClient = regionClient;
    }

    public Integer getId(){
        return id;
    }

    public String getRegionClient() {
        return regionClient;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRegionClient(String regionClient) {
        this.regionClient = regionClient;
    }
}
