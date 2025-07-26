package LaboratorioAgenda;

import LaboratorioAgenda.dao.ContactoDB;
import laboratorioagenda.Contacto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioContacto extends JFrame {
    private JTextField txtNombres, txtApellidos, txtTelefono, txtEmail, txtDireccion;
    private JButton btnGuardar;

    public FormularioContacto() {
        setTitle("Nuevo Contacto");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 2));

        add(new JLabel("Nombres:"));
        txtNombres = new JTextField();
        add(txtNombres);

        add(new JLabel("Apellidos:"));
        txtApellidos = new JTextField();
        add(txtApellidos);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        add(txtTelefono);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Dirección:"));
        txtDireccion = new JTextField();
        add(txtDireccion);

        btnGuardar = new JButton("Guardar");
        add(btnGuardar);
        add(new JLabel()); // espacio vacío

        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                guardarContacto();
            }
        });
    }

    private void guardarContacto() {
        Contacto c = new Contacto();
        c.setNombres(txtNombres.getText());
        c.setApellidos(txtApellidos.getText());
        c.setTelefono(txtTelefono.getText());
        c.setEmail(txtEmail.getText());
        c.setDireccion(txtDireccion.getText());
        c.setGrupo(null); // por ahora sin grupo

        ContactoDB db = new ContactoDB();
        boolean exito = db.guardarContacto(c);

        if (exito) {
            JOptionPane.showMessageDialog(this, "Contacto guardado correctamente.");
            limpiarCampos();
        } else {
            JOptionPane.showMessageDialog(this, "Error al guardar contacto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void limpiarCampos() {
        txtNombres.setText("");
        txtApellidos.setText("");
        txtTelefono.setText("");
        txtEmail.setText("");
        txtDireccion.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormularioContacto().setVisible(true));
    }
}
