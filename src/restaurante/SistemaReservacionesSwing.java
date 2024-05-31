package restaurante;



import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SistemaReservacionesSwing extends JFrame {
    private SistemaReservaciones sistema = new SistemaReservaciones();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
    private JTextField idClienteField;
    private JTextField nombreClienteField;
    private JTextField telefonoClienteField;
    private JTextField correoClienteField;
    private JTextField idReservacionField;
    private JTextField clienteIdReservacionField;
    private JTextField fechaReservacionField;
    private JTextField numeroPersonasReservacionField;
    private JTable clientesTable;
    private JTable reservacionesTable;
    private DefaultTableModel clientesTableModel;
    private DefaultTableModel reservacionesTableModel;

    public SistemaReservacionesSwing() {
        this.setTitle("Sistema de Reservaciones");
        this.setSize(1200, 800);
        this.setDefaultCloseOperation(3);
        this.setLayout(new BorderLayout());
     setIconImage(new ImageIcon(getClass().getResource("/restaurante/icono.png")).getImage());

        JPanel panelSuperior = this.crearPanelSuperior();
        JPanel panelInferior = this.crearPanelInferior();
        this.clientesTableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Teléfono", "Correo"}, 0);
        this.clientesTable = new JTable(this.clientesTableModel);
        JScrollPane clientesScrollPane = new JScrollPane(this.clientesTable);
        this.reservacionesTableModel = new DefaultTableModel(new String[]{"ID", "ID Cliente", "Fecha", "Número de Personas"}, 0);
        this.reservacionesTable = new JTable(this.reservacionesTableModel);
        JScrollPane reservacionesScrollPane = new JScrollPane(this.reservacionesTable);
        this.add(panelSuperior, "North");
        this.add(clientesScrollPane, "Center");
        this.add(panelInferior, "South");
        this.add(reservacionesScrollPane, "East");
    }

    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        this.idClienteField = new JTextField();
        this.nombreClienteField = new JTextField();
        this.telefonoClienteField = new JTextField();
        this.correoClienteField = new JTextField();
        panel.add(new JLabel("ID Cliente:"));
        panel.add(this.idClienteField);
        panel.add(new JLabel("Nombre:"));
        panel.add(this.nombreClienteField);
        panel.add(new JLabel("Teléfono:"));
        panel.add(this.telefonoClienteField);
        panel.add(new JLabel("Correo:"));
        panel.add(this.correoClienteField);
        panel.add(this.crearBoton("Agregar Cliente", (e) -> {
            this.agregarCliente();
        }));
        panel.add(this.crearBoton("Ver Cliente", (e) -> {
            this.verCliente();
        }));
        panel.add(this.crearBoton("Actualizar Cliente", (e) -> {
            this.actualizarCliente();
        }));
        panel.add(this.crearBoton("Eliminar Cliente", (e) -> {
            this.eliminarCliente();
        }));
        panel.add(this.crearBoton("Ver Todos los Clientes", (e) -> {
            this.verTodosLosClientes();
        }));
        return panel;
    }

    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));
        this.idReservacionField = new JTextField();
        this.clienteIdReservacionField = new JTextField();
        this.fechaReservacionField = new JTextField();
        this.numeroPersonasReservacionField = new JTextField();
        panel.add(new JLabel("ID Reservación:"));
        panel.add(this.idReservacionField);
        panel.add(new JLabel("ID Cliente:"));
        panel.add(this.clienteIdReservacionField);
        panel.add(new JLabel("Fecha (dd-MM-yyyy):"));
        panel.add(this.fechaReservacionField);
        panel.add(new JLabel("Número de Personas:"));
        panel.add(this.numeroPersonasReservacionField);
        panel.add(this.crearBoton("Agregar Reservación", (e) -> {
            this.agregarReservacion();
        }));
        panel.add(this.crearBoton("Ver Reservación", (e) -> {
            this.verReservacion();
        }));
        panel.add(this.crearBoton("Actualizar Reservación", (e) -> {
            this.actualizarReservacion();
        }));
        panel.add(this.crearBoton("Eliminar Reservación", (e) -> {
            this.eliminarReservacion();
        }));
        panel.add(this.crearBoton("Ver Todas las Reservaciones", (e) -> {
            this.verTodasLasReservaciones();
        }));
        panel.add(this.crearBoton("Exportar Datos", (e) -> {
            this.exportarDatos();
        }));
        panel.add(this.crearBoton("Importar Datos", (e) -> {
            this.importarDatos();
        }));
        return panel;
    }

    private JButton crearBoton(String texto, ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(accion);
        return boton;
    }

    private void limpiarCamposCliente() {
        this.idClienteField.setText("");
        this.nombreClienteField.setText("");
        this.telefonoClienteField.setText("");
        this.correoClienteField.setText("");
    }

    private void limpiarCamposReservacion() {
        this.idReservacionField.setText("");
        this.clienteIdReservacionField.setText("");
        this.fechaReservacionField.setText("");
        this.numeroPersonasReservacionField.setText("");
    }

    private void agregarCliente() {
        String id = this.idClienteField.getText();
        if (this.sistema.obtenerCliente(id) != null) {
            JOptionPane.showMessageDialog(this, "El ID del cliente ya existe.");
        } else {
            Cliente cliente = new Cliente(id, this.nombreClienteField.getText(), this.telefonoClienteField.getText(), this.correoClienteField.getText());
            this.sistema.agregarCliente(cliente);
            JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente.");
            this.limpiarCamposCliente();
            this.verTodosLosClientes();
        }
    }

    private void verCliente() {
        String id = this.idClienteField.getText();
        Cliente cliente = this.sistema.obtenerCliente(id);
        if (cliente != null) {
            this.nombreClienteField.setText(cliente.getNombre());
            this.telefonoClienteField.setText(cliente.getTelefono());
            this.correoClienteField.setText(cliente.getCorreo());
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
        }

    }

    private void actualizarCliente() {
        Cliente cliente = new Cliente(this.idClienteField.getText(), this.nombreClienteField.getText(), this.telefonoClienteField.getText(), this.correoClienteField.getText());
        this.sistema.actualizarCliente(cliente);
        JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente.");
        this.limpiarCamposCliente();
        this.verTodosLosClientes();
    }

    private void eliminarCliente() {
        this.sistema.eliminarCliente(this.idClienteField.getText());
        JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente.");
        this.limpiarCamposCliente();
        this.verTodosLosClientes();
    }

    private void verTodosLosClientes() {
        Collection<Cliente> clientes = this.sistema.obtenerTodosLosClientes();
        this.clientesTableModel.setRowCount(0);
        Iterator var2 = clientes.iterator();

        while(var2.hasNext()) {
            Cliente cliente = (Cliente)var2.next();
            this.clientesTableModel.addRow(new Object[]{cliente.getId(), cliente.getNombre(), cliente.getTelefono(), cliente.getCorreo()});
        }

    }

    private void agregarReservacion() {
        String id = this.idReservacionField.getText();
        if (this.sistema.obtenerReservacion(id) != null) {
            JOptionPane.showMessageDialog(this, "El ID de la reservación ya existe.");
        } else {
            String clienteId = this.clienteIdReservacionField.getText();
            if (this.sistema.obtenerCliente(clienteId) == null) {
                JOptionPane.showMessageDialog(this, "El ID del cliente no existe. Primero debe agregar el cliente.");
            } else {
                try {
                    Date fecha = this.sdf.parse(this.fechaReservacionField.getText());
                    Reservacion reservacion = new Reservacion(id, clienteId, fecha, Integer.parseInt(this.numeroPersonasReservacionField.getText()));
                    this.sistema.agregarReservacion(reservacion);
                    JOptionPane.showMessageDialog(this, "Reservación agregada exitosamente.");
                    this.limpiarCamposReservacion();
                    this.verTodasLasReservaciones();
                } catch (ParseException var5) {
                    JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto.");
                }

            }
        }
    }

    private void verReservacion() {
        String id = this.idReservacionField.getText();
        Reservacion reservacion = this.sistema.obtenerReservacion(id);
        if (reservacion != null) {
            this.clienteIdReservacionField.setText(reservacion.getClienteId());
            this.fechaReservacionField.setText(this.sdf.format(reservacion.getFecha()));
            this.numeroPersonasReservacionField.setText(String.valueOf(reservacion.getNumeroPersonas()));
        } else {
            JOptionPane.showMessageDialog(this, "Reservación no encontrada.");
        }

    }

    private void actualizarReservacion() {
        try {
            Date fecha = this.sdf.parse(this.fechaReservacionField.getText());
            Reservacion reservacion = new Reservacion(this.idReservacionField.getText(), this.clienteIdReservacionField.getText(), fecha, Integer.parseInt(this.numeroPersonasReservacionField.getText()));
            this.sistema.actualizarReservacion(reservacion);
            JOptionPane.showMessageDialog(this, "Reservación actualizada exitosamente.");
            this.limpiarCamposReservacion();
            this.verTodasLasReservaciones();
        } catch (ParseException var3) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto.");
        }

    }

    private void eliminarReservacion() {
        this.sistema.eliminarReservacion(this.idReservacionField.getText());
        JOptionPane.showMessageDialog(this, "Reservación eliminada exitosamente.");
        this.limpiarCamposReservacion();
        this.verTodasLasReservaciones();
    }

    private void verTodasLasReservaciones() {
        Collection<Reservacion> reservaciones = this.sistema.obtenerTodasLasReservaciones();
        this.reservacionesTableModel.setRowCount(0);
        Iterator var2 = reservaciones.iterator();

        while(var2.hasNext()) {
            Reservacion reservacion = (Reservacion)var2.next();
            this.reservacionesTableModel.addRow(new Object[]{reservacion.getId(), reservacion.getClienteId(), this.sdf.format(reservacion.getFecha()), reservacion.getNumeroPersonas()});
        }

    }

    private void exportarDatos() {
        this.sistema.exportarDatos("backup");
        JOptionPane.showMessageDialog(this, "Datos exportados exitosamente a backup.txt.");
    }

    private void importarDatos() {
        this.sistema.importarDatos("backup");
        JOptionPane.showMessageDialog(this, "Datos importados exitosamente desde backup.txt.");
        this.verTodosLosClientes();
        this.verTodasLasReservaciones();
    }

    public static void main(String[] args) {
        SistemaReservacionesSwing frame = new SistemaReservacionesSwing();
        frame.setVisible(true);
    }
}
