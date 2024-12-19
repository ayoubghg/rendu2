package rendu2.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rendu2.PostgreSQLConnection;
import rendu2.Models.Terrain;

public class TerrainDAO implements GenericDAO<Terrain> {
    public void add(Terrain terrain){
        String sql = "INSERT INTO terrain ( id_terrain , nom_terrain,type) VALUES (?, ?, ?)";
             try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
                stmt.setInt(1, terrain.getId_terrain());
                stmt.setString(1, terrain.getNom_terrain());
                stmt.setString(1, terrain.getType());
                stmt.executeUpdate();
            }catch(SQLException e){
                e.printStackTrace();
                }}
    public Terrain get(int id){

     String sql="SELECT * FROM evenement WHERE id_terrain=? ";
     Terrain terrain = null;
        try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                terrain = new Terrain();
                terrain.setId_terrain(rs.getInt("id_terrain"));
                terrain.setNom_terrain(rs.getString("nom_terrain"));
                terrain.setType(rs.getString("type"));
                
            }
         }catch(SQLException e){
            e.printStackTrace();
         }
         return terrain; 
    }
    public List<Terrain> getAll(){
        List<Terrain> terrains = new ArrayList<>();
        String sql = "SELECT * FROM terrain";
        
        try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { 
    
            while (rs.next()) {
                Terrain terrain = new Terrain();
                terrain.setId_terrain(rs.getInt("id_terrain"));
                terrain.setNom_terrain(rs.getString("nom_terrain"));
                terrain.setType(rs.getString("type"));
                terrains.add(terrain);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return terrains;
    }
    public void update(Terrain terrain){
        String sql = "UPDATE terraint SET nom_terrain = ?, type = ? WHERE id_terrain = ?";
        
        try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, terrain.getNom_terrain());
            stmt.setString(2, terrain.getType() );
            stmt.setInt(3, terrain.getId_terrain()); // This is the condition for which row to update
            
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id){
        String sql = "DELETE FROM terrain WHERE id_terrain= ?";
    try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)){
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
}}

