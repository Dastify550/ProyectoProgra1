package restaurante;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ReservacionDAO {
    private static final String ARCHIVO_DATOS = "backup.txt";
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public ReservacionDAO() {
    }

    public void agregarReservacion(Reservacion reservacion) {
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");
        String var10001 = reservacion.getId();
        lineas.add("RESERVACION;" + var10001 + ";" + reservacion.getClienteId() + ";" + sdf.format(reservacion.getFecha()) + ";" + reservacion.getNumeroPersonas());
        ArchivoHelper.guardarEnArchivo("backup.txt", lineas);
    }

    public Reservacion obtenerReservacion(String id) {
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");
        Iterator var3 = lineas.iterator();

        while(true) {
            String[] partes;
            do {
                do {
                    if (!var3.hasNext()) {
                        return null;
                    }

                    String linea = (String)var3.next();
                    partes = linea.split(";");
                } while(!partes[0].equals("RESERVACION"));
            } while(!partes[1].equals(id));

            try {
                Date fecha = sdf.parse(partes[3]);
                return new Reservacion(partes[1], partes[2], fecha, Integer.parseInt(partes[4]));
            } catch (Exception var7) {
                Exception e = var7;
                e.printStackTrace();
            }
        }
    }

    public void actualizarReservacion(Reservacion reservacionActualizada) {
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");

        for(int i = 0; i < lineas.size(); ++i) {
            String[] partes = ((String)lineas.get(i)).split(";");
            if (partes[0].equals("RESERVACION") && partes[1].equals(reservacionActualizada.getId())) {
                String var10002 = reservacionActualizada.getId();
                lineas.set(i, "RESERVACION;" + var10002 + ";" + reservacionActualizada.getClienteId() + ";" + sdf.format(reservacionActualizada.getFecha()) + ";" + reservacionActualizada.getNumeroPersonas());
                ArchivoHelper.guardarEnArchivo("backup.txt", lineas);
                return;
            }
        }

    }

    public void eliminarReservacion(String id) {
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");
        lineas.removeIf((linea) -> {
            String[] partes = linea.split(";");
            return partes[0].equals("RESERVACION") && partes[1].equals(id);
        });
        ArchivoHelper.guardarEnArchivo("backup.txt", lineas);
    }

    public List<Reservacion> obtenerTodasLasReservaciones() {
        List<Reservacion> reservaciones = new ArrayList();
        List<String> lineas = ArchivoHelper.leerDesdeArchivo("backup.txt");
        Iterator var3 = lineas.iterator();

        while(var3.hasNext()) {
            String linea = (String)var3.next();
            String[] partes = linea.split(";");
            if (partes[0].equals("RESERVACION")) {
                try {
                    Date fecha = sdf.parse(partes[3]);
                    reservaciones.add(new Reservacion(partes[1], partes[2], fecha, Integer.parseInt(partes[4])));
                } catch (Exception var7) {
                    Exception e = var7;
                    e.printStackTrace();
                }
            }
        }

        return reservaciones;
    }
}
