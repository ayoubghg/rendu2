package rendu2.DAOs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import rendu2.PostgreSQLConnection;
import rendu2.Models.Reservation;

public class ReservationDAO implements GenericDAO<Reservation> {
    private static final String INSERT_SQL = "INSERT INTO reservation (id_reservation, id_user, id_event, id_salle, id_terrain, date_reservation) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM reservation WHERE id_reservation = ?";
    private static final String SELECT_ALL_SQL = "SELECT * FROM reservation";
    private static final String UPDATE_SQL = "UPDATE reservation SET id_user = ?, id_event = ?, id_salle = ?, id_terrain = ?, date_reservation = ? WHERE id_reservation = ?";
    private static final String DELETE_SQL = "DELETE FROM reservation WHERE id_reservation = ?";

    @Override
    public void add(Reservation reservation) {
        try (Connection conn = PostgreSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SQL)) {

            stmt.setInt(1, reservation.getId_reservation());
            stmt.setInt(2, reservation.getId_user());
            stmt.setInt(3, reservation.getId_event());
            stmt.setInt(4, reservation.getId_salle());
            stmt.setInt(5, reservation.getId_terrain());
            stmt.setString(6, reservation.getDate_reservation());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Reservation get(int id) {
        Reservation reservation = null;
        try (Connection conn = PostgreSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_BY_ID_SQL)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    reservation = new Reservation();
                    reservation.setId_reservation(rs.getInt("id_reservation"));
                    reservation.setId_user(rs.getInt("id_user"));
                    reservation.setId_event(rs.getInt("id_event"));
                    reservation.setId_salle(rs.getInt("id_salle"));
                    reservation.setId_terrain(rs.getInt("id_terrain"));
                    reservation.setDate_reservation(rs.getString("date_reservation"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservation;
    }

    @Override
    public List<Reservation> getAll() {
        List<Reservation> reservations = new ArrayList<>();
        try (Connection conn = PostgreSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_SQL);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reservation reservation = new Reservation();
                reservation.setId_reservation(rs.getInt("id_reservation"));
                reservation.setId_user(rs.getInt("id_user"));
                reservation.setId_event(rs.getInt("id_event"));
                reservation.setId_salle(rs.getInt("id_salle"));
                reservation.setId_terrain(rs.getInt("id_terrain"));
                reservation.setDate_reservation(rs.getString("date_reservation"));
                reservations.add(reservation);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    @Override
    public void update(Reservation reservation) {
        try (Connection conn = PostgreSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(UPDATE_SQL)) {

            stmt.setInt(1, reservation.getId_user());
            stmt.setInt(2, reservation.getId_event());
            stmt.setInt(3, reservation.getId_salle());
            stmt.setInt(4, reservation.getId_terrain());
            stmt.setString(5, reservation.getDate_reservation());
            stmt.setInt(6, reservation.getId_reservation());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection conn = PostgreSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SQL)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
