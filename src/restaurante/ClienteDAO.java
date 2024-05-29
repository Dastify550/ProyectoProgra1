package restaurante;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO {
    private static final String ARCHIVO_DATOS = "backup.txt"; // Nombre del archivo definido como "backup.txt"

    // Agrega un cliente al archivo
    public void agregarCliente(Cliente cliente) {
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        // Añadir el nuevo cliente al final de la lista
        lineas.add("CLIENTE;" + cliente.getId() + ";" + cliente.getNombre() + ";" + cliente.getTelefono() + ";" + cliente.getCorreo());
        // Guardar la lista actualizada en el archivo
        ArchivoHelper.guardarEnArchivo(ARCHIVO_DATOS, lineas);
    }

    // Obtiene un cliente por su ID
    public Cliente obtenerCliente(String id) {
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            // Si se encuentra un cliente con el ID especificado, se crea y retorna el objeto Cliente
            if (partes[0].equals("CLIENTE") && partes[1].equals(id)) {
                return new Cliente(partes[1], partes[2], partes[3], partes[4]);
            }
        }
        return null; // Si no se encuentra el cliente, se retorna null
    }

    // Actualiza un cliente existente
    public void actualizarCliente(Cliente clienteActualizado) {
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        for (int i = 0; i < lineas.size(); i++) {
            String[] partes = lineas.get(i).split(";");
            // Si se encuentra el cliente a actualizar, se modifica su información
            if (partes[0].equals("CLIENTE") && partes[1].equals(clienteActualizado.getId())) {
                lineas.set(i, "CLIENTE;" + clienteActualizado.getId() + ";" + clienteActualizado.getNombre() + ";" + clienteActualizado.getTelefono() + ";" + clienteActualizado.getCorreo());
                ArchivoHelper.guardarEnArchivo(ARCHIVO_DATOS, lineas); // Guardar la lista actualizada en el archivo
                return;
            }
        }
    }

    // Elimina un cliente por su ID
    public void eliminarCliente(String id) {
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        // Remover el cliente con el ID especificado
        lineas.removeIf(linea -> {
            String[] partes = linea.split(";");
            return partes[0].equals("CLIENTE") && partes[1].equals(id);
        });
        // Guardar la lista actualizada en el archivo
        ArchivoHelper.guardarEnArchivo(ARCHIVO_DATOS, lineas);
    }

    // Obtiene todos los clientes
    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            // Si la línea representa un cliente, se crea y se añade a la lista de clientes
            if (partes[0].equals("CLIENTE")) {
                clientes.add(new Cliente(partes[1], partes[2], partes[3], partes[4]));
            }
        }
        return clientes; // Retornar la lista de clientes
    }
}
