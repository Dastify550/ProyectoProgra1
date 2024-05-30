package restaurante;


import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

public class MenuPrincipal {
    private static Scanner scanner;
    private static SistemaReservaciones sistema;
    private static SimpleDateFormat sdf;

    public MenuPrincipal() {
    }

    public static void main(String[] args) {
        boolean salir = false;

        while(!salir) {
            imprimirMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 0:
                    salir = true;
                    break;
                case 1:
                    agregarCliente();
                    break;
                case 2:
                    verCliente();
                    break;
                case 3:
                    actualizarCliente();
                    break;
                case 4:
                    eliminarCliente();
                    break;
                case 5:
                    agregarReservacion();
                    break;
                case 6:
                    verReservacion();
                    break;
                case 7:
                    actualizarReservacion();
                    break;
                case 8:
                    eliminarReservacion();
                    break;
                case 9:
                    verTodosLosClientes();
                    break;
                case 10:
                    verTodasLasReservaciones();
                    break;
                case 11:
                    exportarDatos();
                    break;
                case 12:
                    importarDatos();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }

    }

    private static void imprimirMenu() {
        System.out.println("------ Sistema de Reservaciones ------");
        System.out.println("1. Agregar Cliente");
        System.out.println("2. Ver Cliente");
        System.out.println("3. Actualizar Cliente");
        System.out.println("4. Eliminar Cliente");
        System.out.println("5. Agregar Reservacion");
        System.out.println("6. Ver Reservacion");
        System.out.println("7. Actualizar Reservacion");
        System.out.println("8. Eliminar Reservacion");
        System.out.println("9. Ver Todos los Clientes");
        System.out.println("10. Ver Todas las Reservaciones");
        System.out.println("11. Exportar Datos");
        System.out.println("12. Importar Datos");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opcion: ");
    }

    private static void agregarCliente() {
        System.out.print("ID: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Telefono: ");
        String telefono = scanner.nextLine();
        System.out.print("Correo: ");
        String correo = scanner.nextLine();
        Cliente cliente = new Cliente(id, nombre, telefono, correo);
        sistema.agregarCliente(cliente);
        System.out.println("Cliente agregado.");
    }

    private static void verCliente() {
        System.out.print("ID del Cliente: ");
        String id = scanner.nextLine();
        Cliente cliente = sistema.obtenerCliente(id);
        if (cliente != null) {
            System.out.println(cliente);
        } else {
            System.out.println("Cliente no encontrado.");
        }

    }

    private static void actualizarCliente() {
        System.out.print("ID del Cliente: ");
        String id = scanner.nextLine();
        Cliente cliente = sistema.obtenerCliente(id);
        if (cliente != null) {
            System.out.print("Nuevo Nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Nuevo Telefono: ");
            String telefono = scanner.nextLine();
            System.out.print("Nuevo Correo: ");
            String correo = scanner.nextLine();
            cliente.setNombre(nombre);
            cliente.setTelefono(telefono);
            cliente.setCorreo(correo);
            sistema.actualizarCliente(cliente);
            System.out.println("Cliente actualizado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }

    }

    private static void eliminarCliente() {
        System.out.print("ID del Cliente: ");
        String id = scanner.nextLine();
        Cliente cliente = sistema.obtenerCliente(id);
        if (cliente != null) {
            sistema.eliminarCliente(id);
            System.out.println("Cliente eliminado.");
        } else {
            System.out.println("Cliente no encontrado.");
        }

    }

    private static void agregarReservacion() {
        System.out.print("ID de la Reservacion: ");
        String id = scanner.nextLine();
        System.out.print("ID del Cliente: ");
        String clienteId = scanner.nextLine();
        System.out.print("Fecha (yyyy-MM-dd): ");
        String fechaString = scanner.nextLine();
        Date fecha = null;

        try {
            fecha = sdf.parse(fechaString);
        } catch (Exception var6) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }

        System.out.print("Numero de Personas: ");
        int numeroPersonas = scanner.nextInt();
        scanner.nextLine();
        Reservacion reservacion = new Reservacion(id, clienteId, fecha, numeroPersonas);
        sistema.agregarReservacion(reservacion);
        System.out.println("Reservacion agregada.");
    }

    private static void verReservacion() {
        System.out.print("ID de la Reservacion: ");
        String id = scanner.nextLine();
        Reservacion reservacion = sistema.obtenerReservacion(id);
        if (reservacion != null) {
            System.out.println(reservacion);
        } else {
            System.out.println("Reservación no encontrada.");
        }

    }

    private static void actualizarReservacion() {
        System.out.print("ID de la Reservacion: ");
        String id = scanner.nextLine();
        Reservacion reservacion = sistema.obtenerReservacion(id);
        if (reservacion != null) {
            System.out.print("Nuevo ID del Cliente: ");
            String clienteId = scanner.nextLine();
            System.out.print("Nueva Fecha (yyyy-MM-dd): ");
            String fechaString = scanner.nextLine();
            Date fecha = null;

            try {
                fecha = sdf.parse(fechaString);
            } catch (Exception var6) {
                System.out.println("Formato de fecha incorrecto.");
                return;
            }

            System.out.print("Nuevo Número de Personas: ");
            int numeroPersonas = scanner.nextInt();
            scanner.nextLine();
            reservacion.setClienteId(clienteId);
            reservacion.setFecha(fecha);
            reservacion.setNumeroPersonas(numeroPersonas);
            sistema.actualizarReservacion(reservacion);
            System.out.println("Reservacion actualizada.");
        } else {
            System.out.println("Reservación no encontrada.");
        }

    }

    private static void eliminarReservacion() {
        System.out.print("ID de la Reservacion: ");
        String id = scanner.nextLine();
        Reservacion reservacion = sistema.obtenerReservacion(id);
        if (reservacion != null) {
            sistema.eliminarReservacion(id);
            System.out.println("Reservación eliminada.");
        } else {
            System.out.println("Reservación no encontrada.");
        }

    }

    private static void verTodosLosClientes() {
        Collection<Cliente> clientes = sistema.obtenerTodosLosClientes();
        Iterator var1 = clientes.iterator();

        while(var1.hasNext()) {
            Cliente cliente = (Cliente)var1.next();
            System.out.println(cliente);
        }

    }

    private static void verTodasLasReservaciones() {
        Collection<Reservacion> reservaciones = sistema.obtenerTodasLasReservaciones();
        Iterator var1 = reservaciones.iterator();

        while(var1.hasNext()) {
            Reservacion reservacion = (Reservacion)var1.next();
            System.out.println(reservacion);
        }

    }

    private static void exportarDatos() {
        sistema.exportarDatos("backup");
        System.out.println("Datos exportados exitosamente a backup.txt");
    }

    private static void importarDatos() {
        sistema.importarDatos("backup");
        System.out.println("Datos importados exitosamente desde backup.txt");
    }

    static {
        scanner = new Scanner(System.in);
        sistema = new SistemaReservaciones();
        sdf = new SimpleDateFormat("yyyy-MM-dd");
    }
}
