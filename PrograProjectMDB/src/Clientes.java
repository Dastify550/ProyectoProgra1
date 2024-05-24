public class Clientes {
    private String nombre;
    private String telefono;
    private String correoElectronico;
    private List<Reserva> historialReservas;

    public Clientes(String nombre, String telefono, String correoElectronico) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.historialReservas = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public List<Reserva> getHistorialReservas() {
        return historialReservas;
    }

    public void agregarReserva(Reserva reserva) {
        historialReservas.add(reserva);
    }

    public boolean cancelarReserva(Reserva reserva) {
        return historialReservas.remove(reserva);
    }

    public void modificarReserva(Reserva reservaAntigua, Reserva reservaNueva) {
        int index = historialReservas.indexOf(reservaAntigua);
        if (index != -1) {
            historialReservas.set(index, reservaNueva);
        }
    }
}