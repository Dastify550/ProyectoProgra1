package restaurante;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReservacionDAO {
    private static final String ARCHIVO_DATOS = "backup.txt"; // Nombre del archivo definido como "backup.txt"
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // Agrega una reservación al archivo
    public void agregarReservacion(Reservacion reservacion) {
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        // Añadir la nueva reservación al final de la lista
        lineas.add("RESERVACION;" + reservacion.getId() + ";" + reservacion.getClienteId() + ";" + sdf.format(reservacion.getFecha()) + ";" + reservacion.getNumeroPersonas());
        // Guardar la lista actualizada en el archivo
        ArchivoHelper.guardarEnArchivo(ARCHIVO_DATOS, lineas);
    }

    // Obtiene una reservación por su ID
    public Reservacion obtenerReservacion(String id) {
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            // Si se encuentra una reservación con el ID especificado, se crea y retorna el objeto Reservacion
            if (partes[0].equals("RESERVACION") && partes[1].equals(id)) {
                try {
                    Date fecha = sdf.parse(partes[3]);
                    return new Reservacion(partes[1], partes[2], fecha, Integer.parseInt(partes[4]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null; // Si no se encuentra la reservación, se retorna null
    }

    // Actualiza una reservación existente
    public void actualizarReservacion(Reservacion reservacionActualizada) {
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        for (int i = 0; i < lineas.size(); i++) {
            String[] partes = lineas.get(i).split(";");
            // Si se encuentra la reservación a actualizar, se modifica su información
            if (partes[0].equals("RESERVACION") && partes[1].equals(reservacionActualizada.getId())) {
                lineas.set(i, "RESERVACION;" + reservacionActualizada.getId() + ";" + reservacionActualizada.getClienteId() + ";" + sdf.format(reservacionActualizada.getFecha()) + ";" + reservacionActualizada.getNumeroPersonas());
                ArchivoHelper.guardarEnArchivo(ARCHIVO_DATOS, lineas); // Guardar la lista actualizada en el archivo
                return;
            }
        }
    }

    // Elimina una reservación por su ID
    public void eliminarReservacion(String id) {
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        // Remover la reservación con el ID especificado
        lineas.removeIf(linea -> {
            String[] partes = linea.split(";");
            return partes[0].equals("RESERVACION") && partes[1].equals(id);
        });
        // Guardar la lista actualizada en el archivo
        ArchivoHelper.guardarEnArchivo(ARCHIVO_DATOS, lineas);
    }

    // Obtiene todas las reservaciones
    public List<Reservacion> obtenerTodasLasReservaciones() {
        List<Reservacion> reservaciones = new ArrayList<>();
        // Leer las líneas existentes del archivo
        List<String> lineas = ArchivoHelper.leerDesdeArchivo(ARCHIVO_DATOS);
        for (String linea : lineas) {
            String[] partes = linea.split(";");
            // Si la línea representa una reservación, se crea y se añade a la lista de reservaciones
            if (partes[0].equals("RESERVACION")) {
                try {
                    Date fecha = sdf.parse(partes[3]);
                    reservaciones.add(new Reservacion(partes[1], partes[2], fecha, Integer.parseInt(partes[4])));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return reservaciones; // Retornar la lista de reservaciones
    }
}
