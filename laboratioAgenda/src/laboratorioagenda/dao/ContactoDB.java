package LaboratorioAgenda.dao;

import laboratorioagenda.Contacto;
import laboratorioagenda.Grupo;
import laboratorioagenda.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactoDB {

    public boolean guardarContacto(Contacto c) {
        String sql = "INSERT INTO contactos (nombres, apellidos, telefono, email, direccion, id_grupo) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNombres());
            stmt.setString(2, c.getApellidos());
            stmt.setString(3, c.getTelefono());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getDireccion());
            stmt.setObject(6, (c.getGrupo() != null) ? c.getGrupo().getIdGrupo() : null, Types.INTEGER);

            stmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            registrarError(e, "guardarContacto");
            return false;
        }
    }

    public List<Contacto> listarContactos() {
        List<Contacto> lista = new ArrayList<>();
        String sql = "SELECT c.*, g.nombre AS grupo_nombre FROM contactos c LEFT JOIN grupos g ON c.id_grupo = g.id_grupo";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Grupo grupo = new Grupo(
                        rs.getInt("id_grupo"),
                        rs.getString("grupo_nombre")
                );

                Contacto c = new Contacto(
                        rs.getInt("id_contacto"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("telefono"),
                        rs.getString("email"),
                        rs.getString("direccion"),
                        rs.getInt("id_grupo") != 0 ? grupo : null
                );

                lista.add(c);
            }

        } catch (SQLException e) {
            registrarError(e, "listarContactos");
        }

        return lista;
    }

    public boolean actualizarContacto(Contacto c) {
        String sql = "UPDATE contactos SET nombres=?, apellidos=?, telefono=?, email=?, direccion=?, id_grupo=? WHERE id_contacto=?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getNombres());
            stmt.setString(2, c.getApellidos());
            stmt.setString(3, c.getTelefono());
            stmt.setString(4, c.getEmail());
            stmt.setString(5, c.getDireccion());
            stmt.setObject(6, (c.getGrupo() != null) ? c.getGrupo().getIdGrupo() : null, Types.INTEGER);
            stmt.setInt(7, c.getIdContacto());

            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            registrarError(e, "actualizarContacto");
            return false;
        }
    }

    public boolean eliminarContacto(int idContacto) {
        String sql = "DELETE FROM contactos WHERE id_contacto=?";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idContacto);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            registrarError(e, "eliminarContacto");
            return false;
        }
    }

    private void registrarError(SQLException e, String metodo) {
        System.err.println("Error en " + metodo + ": " + e.getMessage());
        // Acá podrías insertar en la tabla errores_log si querés implementar esa parte.
    }
}

