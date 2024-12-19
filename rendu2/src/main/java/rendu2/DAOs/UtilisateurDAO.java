package rendu2.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import rendu2.Models.Utilisateur;
import rendu2.PostgreSQLConnection;

public class UtilisateurDAO implements GenericDAO<Utilisateur> {
    public void add(Utilisateur utilisateur) {

        String sql = "INSERT INTO utilisateur (id_user, nom, prenom, email, type) VALUES (?, ?, ?, ?, ?)";
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, utilisateur.getId_user());
            stmt.setString(2, utilisateur.getNom());
            stmt.setString(3, utilisateur.getPrenom());
            stmt.setString(4, utilisateur.getEmail());
            stmt.setString(5, utilisateur.getType());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public Utilisateur get(int id) {

        String sql = "SELECT * FROM utilisateur WHERE id_user = ?";
        Utilisateur utilisateur = null;
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                utilisateur = new Utilisateur();
                utilisateur.setId_user(rs.getInt("id_user"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setType(rs.getString("type"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateur;
    }
    public List<Utilisateur> getAll() {

        List<Utilisateur> utilisateurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur";
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setId_user(rs.getInt("id_user"));
                utilisateur.setNom(rs.getString("nom"));
                utilisateur.setPrenom(rs.getString("prenom"));
                utilisateur.setEmail(rs.getString("email"));
                utilisateur.setType(rs.getString("type"));
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return utilisateurs;
    }
    public void update(Utilisateur utilisateur) {

        String sql = "UPDATE utilisateur SET nom = ?, prenom = ?, email = ?, type = ? WHERE id_user = ?";
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getType());
            stmt.setInt(5, utilisateur.getId_user());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void delete(int id) {

        String sql = "DELETE FROM utilisateur WHERE id_user = ?";
         try (PreparedStatement stmt = PostgreSQLConnection.getConnection().prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
