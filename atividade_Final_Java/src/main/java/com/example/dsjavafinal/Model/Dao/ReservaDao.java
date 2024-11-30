package com.example.dsjavafinal.Model.Dao;

import com.example.dsjavafinal.Model.Reserva;
import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReservaDao {
    private Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public Boolean inserir(Reserva reserva) {
        String sql = "INSERT INTO reservas(numeroSala, curso, disciplina, professor, data, hrEntrada, hrSaida, informatica, turno) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, reserva.getNumeroSala());
            stmt.setString(2, reserva.getCurso());
            stmt.setString(3, reserva.getDisciplina());
            stmt.setString(4, reserva.getProfessor());
            stmt.setString(5, reserva.getData());
            stmt.setString(6, reserva.getHrEntrada());
            stmt.setString(7, reserva.getHrSaida());
            stmt.setBoolean(8, reserva.getInformatica());
            stmt.setString(9, reserva.getTurno());
            stmt.execute();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public Reserva getReservaById(int id) {
        String sql = "SELECT * FROM reservas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();

            if (resultado.next()) {
                return new Reserva(
                        resultado.getInt("id"),
                        resultado.getString("numeroSala"),
                        resultado.getString("curso"),
                        resultado.getString("disciplina"),
                        resultado.getString("professor"),
                        resultado.getString("data"),
                        resultado.getString("hrEntrada"),
                        resultado.getString("hrSaida"),
                        resultado.getBoolean("informatica"),
                        resultado.getString("turno")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Boolean delete(int id) {
        String sql = "DELETE FROM reservas WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public List<Reserva> getReservas() {
        String sql = "SELECT * FROM reservas";
        List<Reserva> listReservas = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet resultado = stmt.executeQuery()) {

            while (resultado.next()) {
                Reserva reserva = new Reserva(
                        resultado.getInt("id"),
                        resultado.getString("numeroSala"),
                        resultado.getString("curso"),
                        resultado.getString("disciplina"),
                        resultado.getString("professor"),
                        resultado.getString("data"),
                        resultado.getString("hrEntrada"),
                        resultado.getString("hrSaida"),
                        resultado.getBoolean("informatica"),
                        resultado.getString("turno")
                );
                listReservas.add(reserva);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ReservaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listReservas;
    }
}


