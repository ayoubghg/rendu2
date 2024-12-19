package rendu2.DAOs;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import rendu2.PostgreSQLConnection;
import rendu2.Models.Evenement;

public class EvenementDAO implements GenericDAO<Evenement>{
    
    public void add(Evenement evenement){
        String sql = "INSERT INTO evenement (id_event, nom_event, date_event, description, id_user) VALUES (?, ?, ?, ?, ?)";
             try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
                stmt.setString(1, evenement.getId_event());
            stmt.setString(2, evenement.getNom_event());
            stmt.setString(3, evenement.getDate_event());
            stmt.setString(4, evenement.getDescription());
            stmt.setString(5, evenement.getId_user());
            stmt.executeUpdate();
             }catch(SQLException e){
                e.printStackTrace();
             }


    }
    public Evenement get(int id){
    
        
        String sql="SELECT * FROM evenement WHERE id_event = ?";
        Evenement evenement = null;
        try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                evenement = new Evenement();
                evenement.setId_event(rs.getString("id_event"));
                evenement.setNom_event(rs.getString("nom_event"));
                evenement.setDate_event(rs.getString("date_event"));
                evenement.setDescription(rs.getString("description"));
                evenement.setId_user(rs.getString("id_user"));
            }
         }catch(SQLException e){
            e.printStackTrace();
         }
         return evenement; 
    }
    public List<Evenement> getAll() {
        List<Evenement> events = new ArrayList<>();
        String sql = "SELECT * FROM evenement";
        
        try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) { // ResultSet is initialized here
    
            while (rs.next()) {
                Evenement evenement = new Evenement();
                evenement.setId_event(rs.getString("id_event"));
                evenement.setNom_event(rs.getString("nom_event"));
                evenement.setDate_event(rs.getString("date_event"));
                evenement.setDescription(rs.getString("description"));
                evenement.setId_user(rs.getString("id_user"));
                events.add(evenement);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return events;
    }
    public void update(Evenement evenement) {
    
        String sql = "UPDATE evenement SET nom_event = ?, date_event = ?, description = ?, id_user = ? WHERE id_event = ?";
    
        try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, evenement.getNom_event());
            stmt.setString(2, evenement.getDate_event());
            stmt.setString(3, evenement.getDescription());
            stmt.setString(4, evenement.getId_user());
            stmt.setString(5, evenement.getId_event()); // This is the condition for which row to update
    
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {
    
    String sql = "DELETE FROM evenement WHERE id_event = ?";
    try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)){
        stmt.setInt(1, id);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
