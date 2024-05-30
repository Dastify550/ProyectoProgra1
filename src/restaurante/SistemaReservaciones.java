package restaurante;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class SistemaReservaciones {
    private ClienteDAO clienteDAO = new ClienteDAO();
    private ReservacionDAO reservacionDAO = new ReservacionDAO();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public SistemaReservaciones() {
    }

    public void agregarCliente(Cliente cliente) {
        this.clienteDAO.agregarCliente(cliente);
    }

    public Cliente obtenerCliente(String id) {
        return this.clienteDAO.obtenerCliente(id);
    }

    public void actualizarCliente(Cliente cliente) {
        this.clienteDAO.actualizarCliente(cliente);
    }

    public void eliminarCliente(String id) {
        this.clienteDAO.eliminarCliente(id);
    }

    public void agregarReservacion(Reservacion reservacion) {
        this.reservacionDAO.agregarReservacion(reservacion);
    }

    public Reservacion obtenerReservacion(String id) {
        return this.reservacionDAO.obtenerReservacion(id);
    }

    public void actualizarReservacion(Reservacion reservacion) {
        this.reservacionDAO.actualizarReservacion(reservacion);
    }

    public void eliminarReservacion(String id) {
        this.reservacionDAO.eliminarReservacion(id);
    }

    public Collection<Cliente> obtenerTodosLosClientes() {
        return this.clienteDAO.obtenerTodosLosClientes();
    }

    public Collection<Reservacion> obtenerTodasLasReservaciones() {
        return this.reservacionDAO.obtenerTodasLasReservaciones();
    }

    public void exportarDatos(String nombreArchivo) {
        List<String> lineas = new ArrayList();
        Iterator var3 = this.obtenerTodosLosClientes().iterator();

        String var10001;
        while(var3.hasNext()) {
            Cliente cliente = (Cliente)var3.next();
            var10001 = cliente.getId();
            lineas.add("CLIENTE;" + var10001 + ";" + cliente.getNombre() + ";" + cliente.getTelefono() + ";" + cliente.getCorreo());
        }

        var3 = this.obtenerTodasLasReservaciones().iterator();

        while(var3.hasNext()) {
            Reservacion reservacion = (Reservacion)var3.next();
            var10001 = reservacion.getId();
            lineas.add("RESERVACION;" + var10001 + ";" + reservacion.getClienteId() + ";" + sdf.format(reservacion.getFecha()) + ";" + reservacion.getNumeroPersonas());
        }

        ArchivoHelper.guardarEnArchivo(nombreArchivo + ".txt", lineas);
    }

    public void importarDatos(String nombreArchivo) {
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(nombreArchivo + ".txt");
        Iterator var3 = lineas.iterator();

        while(var3.hasNext()) {
            String linea = (String)var3.next();
            String[] partes = linea.split(";");
            if (partes[0].equals("CLIENTE")) {
                Cliente cliente = new Cliente(partes[1], partes[2], partes[3], partes[4]);
                this.agregarCliente(cliente);
            } else if (partes[0].equals("RESERVACION")) {
                try {
                    Date fecha = sdf.parse(partes[3]);
                    Reservacion reservacion = new Reservacion(partes[1], partes[2], fecha, Integer.parseInt(partes[4]));
                    this.agregarReservacion(reservacion);
                } catch (Exception var8) {
                    Exception e = var8;
                    e.printStackTrace();
                }
            }
        }

    }
}
