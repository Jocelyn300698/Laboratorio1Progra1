package LaboratorioAgenda.dao;

import laboratorioagenda.Grupo;
import laboratorioagenda.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GrupoDB {

    public boolean insertarGrupo(Grupo g) {
        String sql = "INSERT INTO grupos (nombre) VALUES (?)";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, g.getNombre());
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            registrarError(e, "insertarGrupo");
            return false;
        }
    }

    public List<Grupo> listarGrupos() {
        List<Grupo> lista = new ArrayList<>();
        String sql = "SELECT id_grupo, nombre FROM grupos ORDER BY nombre";

        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Grupo g = new Grupo(
                    rs.getInt("id_grupo"),
                    rs.getString("nombre")
                );
                lista.add(g);
            }

        } catch (SQLException e) {
            registrarError(e, "listarGrupos");
        }

        return lista;
    }

    public boolean eliminarGrupo(int idGrupo) {
        String sql = "DELETE FROM grupos WHERE id_grupo = ?";
        try (Connection conn = ConexionBD.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idGrupo);
            stmt.executeUpdate();
            return true;

        } catch (SQLException e) {
            registrarError(e, "eliminarGrupo");
            return false;
        }
    }

    private void registrarError(SQLException e, String metodo) {
        System.err.println("Error en " + metodo + ": " + e.getMessage());
        // Aquí podrías insertar en la tabla errores_log si querés implementarla más adelante.
    }
}
