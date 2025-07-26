package LaboratorioAgenda.dao;

import laboratorioagenda.Evento;
import laboratorioagenda.Contacto;
import laboratorioagenda.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EventoDB {

    public boolean guardarEvento(Evento e) {
        String sql = "INSERT INTO eventos (id_contacto, fecha, hora, descripcion, ubicacion) " +
                     "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, e.getContacto().getIdContacto());
            stmt.setDate(2, Date.valueOf(e.getFecha()));
            stmt.setTime(3, Time.valueOf(e.getHora()));
            stmt.setString(4, e.getDescripcion());
            stmt.setString(5, e.getUbicacion());

            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            registrarError(ex, "guardarEvento");
            return false;
        }
    }

    public List<Evento> listarEventos() {
        List<Evento> lista = new ArrayList<>();
        String sql = "SELECT * FROM eventos";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Evento evento = new Evento();
                evento.setIdEvento(rs.getInt("id_evento"));
                evento.setFecha(rs.getDate("fecha").toLocalDate());
                evento.setHora(rs.getTime("hora").toLocalTime());
                evento.setDescripcion(rs.getString("descripcion"));
                evento.setUbicacion(rs.getString("ubicacion"));

                // Cargar contacto asociado solo con ID (opcionalmente podés cargar el nombre si hacés JOIN)
                Contacto c = new Contacto();
                c.setIdContacto(rs.getInt("id_contacto"));
                evento.setContacto(c);

                lista.add(evento);
            }

        } catch (SQLException ex) {
            registrarError(ex, "listarEventos");
        }

        return lista;
    }

    public boolean actualizarEvento(Evento e) {
        String sql = "UPDATE eventos SET id_contacto=?, fecha=?, hora=?, descripcion=?, ubicacion=? WHERE id_evento=?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, e.getContacto().getIdContacto());
            stmt.setDate(2, Date.valueOf(e.getFecha()));
            stmt.setTime(3, Time.valueOf(e.getHora()));
            stmt.setString(4, e.getDescripcion());
            stmt.setString(5, e.getUbicacion());
            stmt.setInt(6, e.getIdEvento());

            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            registrarError(ex, "actualizarEvento");
            return false;
        }
    }

    public boolean eliminarEvento(int idEvento) {
        String sql = "DELETE FROM eventos WHERE id_evento=?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEvento);
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {
            registrarError(ex, "eliminarEvento");
            return false;
        }
    }

    private void registrarError(SQLException e, String metodo) {
        System.err.println("Error en " + metodo + ": " + e.getMessage());
        // Aquí podrías registrar errores en la tabla errores_log
    }
}

