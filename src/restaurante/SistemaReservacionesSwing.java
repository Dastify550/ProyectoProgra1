package restaurante;
// aqui vamos importar nuestras librerias 
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public class SistemaReservacionesSwing extends JFrame {
    private SistemaReservaciones sistema; // Instancia o concecion de nuestra clase del sistema de reservaciones
    private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Formato de fecha

    // Componentes de la interfaz gráfica
    private JTextField idClienteField, nombreClienteField, telefonoClienteField, correoClienteField;
    private JTextField idReservacionField, clienteIdReservacionField, fechaReservacionField, numeroPersonasReservacionField;

    private JTable clientesTable, reservacionesTable;
    private DefaultTableModel clientesTableModel, reservacionesTableModel;

    // Constructor de la clase
    public SistemaReservacionesSwing() {
        sistema = new SistemaReservaciones(); // Crear instancia del sistema de reservaciones
        setTitle("Sistema de Reservaciones"); 
        setSize(1200, 800); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Establecer operación al cerrar
        setLayout(new BorderLayout()); // Establecer el layout de la ventana

        // Crear y configurar los paneles
        JPanel panelSuperior = crearPanelSuperior();
        JPanel panelInferior = crearPanelInferior();

        // Panel central con tabla de clientes
        clientesTableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Teléfono", "Correo"}, 0); // Modelo de la tabla de clientes
        clientesTable = new JTable(clientesTableModel); // Creamos tabla de clientes
        JScrollPane clientesScrollPane = new JScrollPane(clientesTable);

        // Panel central con tabla de reservaciones
        reservacionesTableModel = new DefaultTableModel(new String[]{"ID", "ID Cliente", "Fecha", "Número de Personas"}, 0); // Modelo de la tabla de reservaciones
        reservacionesTable = new JTable(reservacionesTableModel); // Crear tabla de reservaciones
        JScrollPane reservacionesScrollPane = new JScrollPane(reservacionesTable);
       
        // Añadir paneles al frame
        add(panelSuperior, BorderLayout.NORTH); 
        add(clientesScrollPane, BorderLayout.CENTER); 
        add(panelInferior, BorderLayout.SOUTH); 
        add(reservacionesScrollPane, BorderLayout.EAST);
    }

    // Crear panel superior con campos y botones para cliente
    private JPanel crearPanelSuperior() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5)); // Crear panel con grid layout

        // Campos de entrada de cliente
        idClienteField = new JTextField();
        nombreClienteField = new JTextField();
        telefonoClienteField = new JTextField(); 
        correoClienteField = new JTextField(); 

        // Nuestras etiquetas y campos para entrada cliente
        panel.add(new JLabel("ID Cliente:")); 
        panel.add(idClienteField); 
        panel.add(new JLabel("Nombre:")); 
        panel.add(nombreClienteField);
        panel.add(new JLabel("Teléfono:")); 
        panel.add(telefonoClienteField); 
        panel.add(new JLabel("Correo:")); 
        panel.add(correoClienteField); 

        // Botones para operaciones de cliente
        panel.add(crearBoton("Agregar Cliente", e -> agregarCliente()));
        panel.add(crearBoton("Ver Cliente", e -> verCliente()));
        panel.add(crearBoton("Actualizar Cliente", e -> actualizarCliente()));
        panel.add(crearBoton("Eliminar Cliente", e -> eliminarCliente())); 
        panel.add(crearBoton("Ver Todos los Clientes", e -> verTodosLosClientes())); 

        return panel; // Devolver panel configurado
    }

    // Crear panel inferior con campos y botones para reservación
    private JPanel crearPanelInferior() {
        JPanel panel = new JPanel(new GridLayout(8, 2, 5, 5));

        // Campos de entrada de reservación
        idReservacionField = new JTextField();
        clienteIdReservacionField = new JTextField(); 
        fechaReservacionField = new JTextField(); 
        numeroPersonasReservacionField = new JTextField(); 
        //etiqueta y campo de entrada
        panel.add(new JLabel("ID Reservación:"));
        panel.add(idReservacionField);
        panel.add(new JLabel("ID Cliente:")); 
        panel.add(clienteIdReservacionField); 
        panel.add(new JLabel("Fecha (dd-MM-yyyy):")); 
        panel.add(fechaReservacionField); 
        panel.add(new JLabel("Número de Personas:")); 
        panel.add(numeroPersonasReservacionField); 

        // Botones para operaciones de reservación
        panel.add(crearBoton("Agregar Reservación", e -> agregarReservacion())); 
        panel.add(crearBoton("Ver Reservación", e -> verReservacion())); 
        panel.add(crearBoton("Actualizar Reservación", e -> actualizarReservacion())); 
        panel.add(crearBoton("Eliminar Reservación", e -> eliminarReservacion())); 
        panel.add(crearBoton("Ver Todas las Reservaciones", e -> verTodasLasReservaciones())); 

        // Botones para exportar e importar datos
        panel.add(crearBoton("Exportar Datos", e -> exportarDatos())); 
        panel.add(crearBoton("Importar Datos", e -> importarDatos())); 

        return panel; // Devolver panel configurado
    }

    // Crear un botón con una acción asociada
    private JButton crearBoton(String texto, ActionListener accion) {
        JButton boton = new JButton(texto);
        boton.addActionListener(accion); 
        return boton;
    }

    // Limpiar campos de entrada de cliente aquiiiiii para que no se me olvide
    private void limpiarCamposCliente() {
        idClienteField.setText(""); 
        nombreClienteField.setText(""); 
        telefonoClienteField.setText(""); 
        correoClienteField.setText(""); 
    }

    // Limpiar campos de entrada de reservación aquiiiiiiiiiii para que no se me olvide
    private void limpiarCamposReservacion() {
        idReservacionField.setText(""); 
        clienteIdReservacionField.setText("");
        fechaReservacionField.setText(""); 
        numeroPersonasReservacionField.setText(""); 
    }

    // Agregar un cliente
    private void agregarCliente() {
        String id = idClienteField.getText(); // Obtener ID de cliente

        // Verificar si el cliente ya existe
        if (sistema.obtenerCliente(id) != null) {
            JOptionPane.showMessageDialog(this, "El ID del cliente ya existe."); 
            return; // Salir del método si el cliente ya existe
        }

        // Crear y agregar cliente
        Cliente cliente = new Cliente(id, nombreClienteField.getText(), telefonoClienteField.getText(), correoClienteField.getText()); // Crear nuevo cliente
        sistema.agregarCliente(cliente); // Agregar cliente al sistema
        JOptionPane.showMessageDialog(this, "Cliente agregado exitosamente."); // Mostrar mensaje de éxito
        limpiarCamposCliente(); // llamamos el metodo limpiar campos de entrada de cliente
        verTodosLosClientes(); // Actualizar tabla de clientes
    }

    // Ver un cliente
    private void verCliente() {
        String id = idClienteField.getText(); // Obtener ID de cliente
        Cliente cliente = sistema.obtenerCliente(id); // Buscar cliente en el sistema
        if (cliente != null) {
            nombreClienteField.setText(cliente.getNombre()); // Mostrar nombre del cliente
            telefonoClienteField.setText(cliente.getTelefono()); // Mostrar teléfono del cliente
            correoClienteField.setText(cliente.getCorreo()); // Mostrar correo del cliente
        } else {
            JOptionPane.showMessageDialog(this, "Cliente no encontrado."); // Mostrar mensaje de error si el cliente no existe
        }
    }

    // Actualizar un cliente
    private void actualizarCliente() {
        Cliente cliente = new Cliente(idClienteField.getText(), nombreClienteField.getText(), telefonoClienteField.getText(), correoClienteField.getText()); // Crear cliente actualizado
        sistema.actualizarCliente(cliente); // Actualizar cliente en el sistema
        JOptionPane.showMessageDialog(this, "Cliente actualizado exitosamente."); // Mostrar mensaje de éxito
        limpiarCamposCliente(); // Limpiar campos de entrada de cliente
        verTodosLosClientes(); // Actualizar tabla de clientes
    }

    // Eliminar un cliente
    private void eliminarCliente() {
        sistema.eliminarCliente(idClienteField.getText()); // Eliminar cliente del sistema
        JOptionPane.showMessageDialog(this, "Cliente eliminado exitosamente."); // Mostrar mensaje de éxito
        limpiarCamposCliente(); // Limpiar campos de entrada de cliente
        verTodosLosClientes(); // Actualizar tabla de clientes
    }

    // Ver todos los clientes
    private void verTodosLosClientes() {
        Collection<Cliente> clientes = sistema.obtenerTodosLosClientes(); // Obtener todos los clientes del sistema
        clientesTableModel.setRowCount(0); // Limpiar tabla de clientes
        for (Cliente cliente : clientes) {
            clientesTableModel.addRow(new Object[]{cliente.getId(), cliente.getNombre(), cliente.getTelefono(), cliente.getCorreo()}); // Añadir cliente a la tabla
        }
    }

    // Agregar una reservación
    private void agregarReservacion() {
        String id = idReservacionField.getText(); // Obtener ID de reservación

        // Verificar si la reservación ya existe
        if (sistema.obtenerReservacion(id) != null) {
            JOptionPane.showMessageDialog(this, "El ID de la reservación ya existe."); // Mostrar mensaje de error si la reservación ya existe
            return; // Salir del método si la reservación ya existe
        }

        // Verificar si el cliente existe
        String clienteId = clienteIdReservacionField.getText(); // Obtener ID de cliente
        if (sistema.obtenerCliente(clienteId) == null) {
            JOptionPane.showMessageDialog(this, "El ID del cliente no existe. Primero debe agregar el cliente."); // Mostrar mensaje de error si el cliente no existe
            return; // Salir del método si el cliente no existe
        }

        // Crear y agregar reservación
        try {
            Date fecha = sdf.parse(fechaReservacionField.getText()); // Parsear fecha de reservación
            Reservacion reservacion = new Reservacion(id, clienteId, fecha, Integer.parseInt(numeroPersonasReservacionField.getText())); // Crear nueva reservación
            sistema.agregarReservacion(reservacion); // Agregar reservación al sistema
            JOptionPane.showMessageDialog(this, "Reservación agregada exitosamente."); // Mostrar mensaje de éxito
            limpiarCamposReservacion(); // Limpiar campos de entrada de reservación
            verTodasLasReservaciones(); // Actualizar tabla de reservaciones
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto."); // Mostrar mensaje de error si la fecha es incorrecta
        }
    }

    // Ver una reservación
    private void verReservacion() {
        String id = idReservacionField.getText(); // Obtener ID de reservación
        Reservacion reservacion = sistema.obtenerReservacion(id); // Buscar reservación en el sistema
        if (reservacion != null) {
            clienteIdReservacionField.setText(reservacion.getClienteId()); // Mostrar ID de cliente en reservación
            fechaReservacionField.setText(sdf.format(reservacion.getFecha())); // Mostrar fecha de reservación
            numeroPersonasReservacionField.setText(String.valueOf(reservacion.getNumeroPersonas())); // Mostrar número de personas en reservación
        } else {
            JOptionPane.showMessageDialog(this, "Reservación no encontrada."); // Mostrar mensaje de error si la reservación no existe
        }
    }

    // Actualizar una reservación
    private void actualizarReservacion() {
        try {
            Date fecha = sdf.parse(fechaReservacionField.getText()); // Parsear fecha de reservación
            Reservacion reservacion = new Reservacion(idReservacionField.getText(), clienteIdReservacionField.getText(), fecha, Integer.parseInt(numeroPersonasReservacionField.getText())); // Crear reservación actualizada
            sistema.actualizarReservacion(reservacion); // Actualizar reservación en el sistema
            JOptionPane.showMessageDialog(this, "Reservación actualizada exitosamente."); // Mostrar mensaje de éxito
            limpiarCamposReservacion(); // Limpiar campos de entrada de reservación
            verTodasLasReservaciones(); // Actualizar tabla de reservaciones
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(this, "Formato de fecha incorrecto."); // Mostrar mensaje de error si la fecha es incorrecta
        }
    }

    // Eliminar una reservación
    private void eliminarReservacion() {
        sistema.eliminarReservacion(idReservacionField.getText()); // Eliminar reservación del sistema
        JOptionPane.showMessageDialog(this, "Reservación eliminada exitosamente."); // Mostrar mensaje de éxito
        limpiarCamposReservacion(); // Limpiar campos de entrada de reservación
        verTodasLasReservaciones(); // Actualizar tabla de reservaciones
    }

    // Ver todas las reservaciones
    private void verTodasLasReservaciones() {
        Collection<Reservacion> reservaciones = sistema.obtenerTodasLasReservaciones(); // Obtener todas las reservaciones del sistema
        reservacionesTableModel.setRowCount(0); // Limpiar tabla de reservaciones
        for (Reservacion reservacion : reservaciones) {
            reservacionesTableModel.addRow(new Object[]{reservacion.getId(), reservacion.getClienteId(), sdf.format(reservacion.getFecha()), reservacion.getNumeroPersonas()}); // Añadir reservación a la tabla
        }
    }

    // Exportar datos a un archivo
    private void exportarDatos() {
        sistema.exportarDatos("backup"); // Exportar datos a un archivo llamado "backup"
        JOptionPane.showMessageDialog(this, "Datos exportados exitosamente a backup.txt."); // Mostrar mensaje de éxito
    }

    // Importar datos desde un archivo
    private void importarDatos() {
        sistema.importarDatos("backup"); // Importar datos desde un archivo llamado "backup"
        JOptionPane.showMessageDialog(this, "Datos importados exitosamente desde backup.txt."); // Mostrar mensaje de éxito
        verTodosLosClientes(); // Actualizar tabla de clientes
        verTodasLasReservaciones(); // Actualizar tabla de reservaciones
    }

    // Método principal para ejecutar la aplicación
    public static void main(String[] args) {
        SistemaReservacionesSwing frame = new SistemaReservacionesSwing(); // Crear instancia de la ventana principal
        frame.setVisible(true); // Hacer visible la ventana principal
    }
}
