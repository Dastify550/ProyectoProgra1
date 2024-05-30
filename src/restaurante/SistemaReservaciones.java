package restaurante;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SistemaReservaciones {
    private ClienteDAO clienteDAO;
    private ReservacionDAO reservacionDAO;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // Constructor de la clase SistemaReservaciones
    public SistemaReservaciones() {
        clienteDAO = new ClienteDAO();
        reservacionDAO = new ReservacionDAO();
    }

    // Métodos CRUD para clientes
    public void agregarCliente(Cliente cliente) {
        clienteDAO.agregarCliente(cliente);
    }

    public Cliente obtenerCliente(String id) {
        return clienteDAO.obtenerCliente(id);
    }

    public void actualizarCliente(Cliente cliente) {
        clienteDAO.actualizarCliente(cliente);
    }

    public void eliminarCliente(String id) {
        clienteDAO.eliminarCliente(id);
    }

    // Métodos CRUD para reservaciones
    public void agregarReservacion(Reservacion reservacion) {
        reservacionDAO.agregarReservacion(reservacion);
    }

    public Reservacion obtenerReservacion(String id) {
        return reservacionDAO.obtenerReservacion(id);
    }

    public void actualizarReservacion(Reservacion reservacion) {
        reservacionDAO.actualizarReservacion(reservacion);
    }

    public void eliminarReservacion(String id) {
        reservacionDAO.eliminarReservacion(id);
    }

    // Método para obtener todos los clientes
    public Collection<Cliente> obtenerTodosLosClientes() {
        return clienteDAO.obtenerTodosLosClientes();
    }

    // Método para obtener todas las reservaciones
    public Collection<Reservacion> obtenerTodasLasReservaciones() {
        return reservacionDAO.obtenerTodasLasReservaciones();
    }

    // Métodos para exportar e importar datos
    public void exportarDatos(String nombreArchivo) {
        List<String> lineas = new ArrayList<>();
        // Exportar todos los clientes al archivo
        for (Cliente cliente : obtenerTodosLosClientes()) {
            lineas.add("CLIENTE;" + cliente.getId() + ";" + cliente.getNombre() + ";" + cliente.getTelefono() + ";" + cliente.getCorreo());
        }
        // Exportar todas las reservaciones al archivo
        for (Reservacion reservacion : obtenerTodasLasReservaciones()) {
            lineas.add("RESERVACION;" + reservacion.getId() + ";" + reservacion.getClienteId() + ";" + sdf.format(reservacion.getFecha()) + ";" + reservacion.getNumeroPersonas());
        }
        // Guardar las líneas en el archivo
        ArchivoHelper.guardarEnArchivo(nombreArchivo + ".txt", lineas);
    }

    public void importarDatos(String nombreArchivo) {
        // Leer las líneas del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(nombreArchivo + ".txt");
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            // Importar clientes desde el archivo
            if (partes[0].equals("CLIENTE")) {
                Cliente cliente = new Cliente(partes[1], partes[2], partes[3], partes[4]);
                agregarCliente(cliente);
            } 
            // Importar reservaciones desde el archivo
            else if (partes[0].equals("RESERVACION")) {
                try {
                    Date fecha = sdf.parse(partes[3]);
                    Reservacion reservacion = new Reservacion(partes[1], partes[2], fecha, Integer.parseInt(partes[4]));
                    agregarReservacion(reservacion);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
