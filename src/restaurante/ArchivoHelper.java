package restaurante;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoHelper {

    // Guarda una lista de líneas en un archivo
    public static void guardarEnArchivo(String archivo, List<String> lineas) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
            // Escribir cada línea en el archivo
            for (String linea : lineas) {
                writer.println(linea);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Lee las líneas desde un archivo y las devuelve como una lista
    public static List<String> leerDesdeArchivo(String archivo) {
        List<String> lineas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            // Leer cada línea del archivo y añadirla a la lista
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        } catch (FileNotFoundException e) {
            // El archivo no existe, se retorna una lista vacía
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lineas;
    }
}
