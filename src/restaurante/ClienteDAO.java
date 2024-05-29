package restaurante;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ClienteDAO {
    private static final String ARCHIVO_DATOS = "backup.txt";

    public ClienteDAO() {
    }

    public void agregarCliente(Cliente cliente) {
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");
        String var10001 = cliente.getId();
        lineas.add("CLIENTE;" + var10001 + ";" + cliente.getNombre() + ";" + cliente.getTelefono() + ";" + cliente.getCorreo());
        ArchivoHelper.guardarEnArchivo("backup.txt", lineas);
    }

    public Cliente obtenerCliente(String id) {
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");
        Iterator var3 = lineas.iterator();

        String[] partes;
        do {
            if (!var3.hasNext()) {
                return null;
            }

            String linea = (String)var3.next();
            partes = linea.split(";");
        } while(!partes[0].equals("CLIENTE") || !partes[1].equals(id));

        return new Cliente(partes[1], partes[2], partes[3], partes[4]);
    }

    public void actualizarCliente(Cliente clienteActualizado) {
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");

        for(int i = 0; i < lineas.size(); ++i) {
            String[] partes = ((String)lineas.get(i)).split(";");
            if (partes[0].equals("CLIENTE") && partes[1].equals(clienteActualizado.getId())) {
                String var10002 = clienteActualizado.getId();
                lineas.set(i, "CLIENTE;" + var10002 + ";" + clienteActualizado.getNombre() + ";" + clienteActualizado.getTelefono() + ";" + clienteActualizado.getCorreo());
                ArchivoHelper.guardarEnArchivo("backup.txt", lineas);
                return;
            }
        }

    }

    public void eliminarCliente(String id) {
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");
        lineas.removeIf((linea) -> {
            String[] partes = linea.split(";");
            return partes[0].equals("CLIENTE") && partes[1].equals(id);
        });
        ArchivoHelper.guardarEnArchivo("backup.txt", lineas);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList();
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");
        Iterator var3 = lineas.iterator();

        while(var3.hasNext()) {
            String linea = (String)var3.next();
            String[] partes = linea.split(";");
            if (partes[0].equals("CLIENTE")) {
                clientes.add(new Cliente(partes[1], partes[2], partes[3], partes[4]));
            }
        }

        return clientes;
    }
}
