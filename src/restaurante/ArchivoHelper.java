package restaurante;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArchivoHelper {
    public ArchivoHelper() {
    }

    public static void guardarEnArchivo(String archivo, List<String> lineas) {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter(archivo));

            try {
                Iterator var3 = lineas.iterator();

                while(var3.hasNext()) {
                    String linea = (String)var3.next();
                    writer.println(linea);
                }
            } catch (Throwable var6) {
                try {
                    writer.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            writer.close();
        } catch (IOException var7) {
            IOException e = var7;
            e.printStackTrace();
        }

    }

    public static List<String> leerDesdeArchivo(String archivo) {
        List<String> lineas = new ArrayList();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));

            String linea;
            try {
                while((linea = reader.readLine()) != null) {
                    lineas.add(linea);
                }
            } catch (Throwable var6) {
                try {
                    reader.close();
                } catch (Throwable var5) {
                    var6.addSuppressed(var5);
                }

                throw var6;
            }

            reader.close();
        } catch (FileNotFoundException var7) {
        } catch (IOException var8) {
            IOException e = var8;
            e.printStackTrace();
        }

        return lineas;
    }
}
