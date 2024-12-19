package rendu2.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import rendu2.Models.Salle;
import rendu2.PostgreSQLConnection;

public class SalleDAO implements GenericDAO<Salle> {
    public void add(Salle salle) {

        String sql = "INSERT INTO salle (id_salle, nom_salle, capacite) VALUES (?, ?, ?)";
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, salle.getId_salle());
            stmt.setString(2, salle.getNom_salle());
            stmt.setInt(3, salle.getCapacite());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Salle get(int id) {

        String sql = "SELECT * FROM salle WHERE id_salle = ?";
        Salle salle = null;
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                salle = new Salle();
                salle.setId_salle(rs.getInt("id_salle"));
                salle.setNom_salle(rs.getString("nom_salle"));
                salle.setCapacite(rs.getInt("capacite"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salle;
    }
    public List<Salle> getAll() {

        List<Salle> salles = new ArrayList<>();
        String sql = "SELECT * FROM salle";
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Salle salle = new Salle();
                salle.setId_salle(rs.getInt("id_salle"));
                salle.setNom_salle(rs.getString("nom_salle"));
                salle.setCapacite(rs.getInt("capacite"));
                salles.add(salle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salles;
    }
    public void update(Salle salle) {

        String sql = "UPDATE salle SET nom_salle = ?, capacite = ? WHERE id_salle = ?";
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, salle.getNom_salle());
            stmt.setInt(2, salle.getCapacite());
            stmt.setInt(3, salle.getId_salle());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {

        String sql = "DELETE FROM salle WHERE id_salle = ?";
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
