package restaurante;
import java.text.SimpleDateFormat;
import java.util.*;

public class MenuPrincipal {
    private static Scanner scanner = new Scanner(System.in);
    private static SistemaReservaciones sistema = new SistemaReservaciones();
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    public static void main(String[] args) {
        boolean salir = false;

        while (!salir) {
            imprimirMenu();
            int opcion = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (opcion) {
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
                case 0:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Imprimir el menú principal
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

    // Agregar un nuevo cliente
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

    // Ver un cliente por su ID
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

    // Actualizar un cliente existente
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

    // Eliminar un cliente por su ID
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

    // Agregar una nueva reservación
    private static void agregarReservacion() {
        System.out.print("ID de la Reservacion: ");
        String id = scanner.nextLine();
        System.out.print("ID del Cliente: ");
        String clienteId = scanner.nextLine();
        System.out.print("Fecha (dd-MM-yyyy): ");
        String fechaString = scanner.nextLine();
        Date fecha = null;
        try {
            fecha = sdf.parse(fechaString);
        } catch (Exception e) {
            System.out.println("Formato de fecha incorrecto.");
            return;
        }
        System.out.print("Numero de Personas: ");
        int numeroPersonas = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Reservacion reservacion = new Reservacion(id, clienteId, fecha, numeroPersonas);
        sistema.agregarReservacion(reservacion);
        System.out.println("Reservacion agregada.");
    }

    // Ver una reservación por su ID
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

    // Actualizar una reservación existente
    private static void actualizarReservacion() {
        System.out.print("ID de la Reservacion: ");
        String id = scanner.nextLine();
        Reservacion reservacion = sistema.obtenerReservacion(id);
        if (reservacion != null) {
            System.out.print("Nuevo ID del Cliente: ");
            String clienteId = scanner.nextLine();
            System.out.print("Nueva Fecha (dd-MM-yyyy): ");
            String fechaString = scanner.nextLine();
            Date fecha = null;
            try {
                fecha = sdf.parse(fechaString);
            } catch (Exception e) {
                System.out.println("Formato de fecha incorrecto.");
                return;
            }
            System.out.print("Nuevo Número de Personas: ");
            int numeroPersonas = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            reservacion.setClienteId(clienteId);
            reservacion.setFecha(fecha);
            reservacion.setNumeroPersonas(numeroPersonas);
            sistema.actualizarReservacion(reservacion);
            System.out.println("Reservacion actualizada.");
        } else {
            System.out.println("Reservación no encontrada.");
        }
    }

    // Eliminar una reservación por su ID
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

    // Ver todos los clientes
    private static void verTodosLosClientes() {
        Collection<Cliente> clientes = sistema.obtenerTodosLosClientes();
        for (Cliente cliente : clientes) {
            System.out.println(cliente);
        }
    }

    // Ver todas las reservaciones
    private static void verTodasLasReservaciones() {
        Collection<Reservacion> reservaciones = sistema.obtenerTodasLasReservaciones();
        for (Reservacion reservacion : reservaciones) {
            System.out.println(reservacion);
        }
    }

    // Exportar los datos a un archivo
    private static void exportarDatos() {
        sistema.exportarDatos("backup");
        System.out.println("Datos exportados exitosamente a backup.txt");
    }

    // Importar los datos desde un archivo
    private static void importarDatos() {
        sistema.importarDatos("backup");
        System.out.println("Datos importados exitosamente desde backup.txt");
    }
}
