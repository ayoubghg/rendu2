package rendu2.Models;

public class Terrain {
    private int id_terrain;
    private String nom_terrain;
    private String type;

    public int getId_terrain() {
        return id_terrain;
    }

    public void setId_terrain(int id_terrain) {
        this.id_terrain = id_terrain;
    }

    public String getNom_terrain() {
        return nom_terrain;
    }

    public void setNom_terrain(String nom_terrain) {
        this.nom_terrain = nom_terrain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
