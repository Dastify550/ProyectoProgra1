package restaurante;
import java.io.Serializable;
import java.util.Date;

public class Reservacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String id;
    private String clienteId;
    private Date fecha;
    private int numeroPersonas;

    // Constructor de la clase Reservacion
    public Reservacion(String id, String clienteId, Date fecha, int numeroPersonas) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
    }

    // Métodos Getters y Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }
    
    @Override
    public String toString() {
        return "Reservacion{" +
                "id='" + id + '\'' +
                ", DPI='" + clienteId + '\'' +
                ", fecha=" + fecha +
                ", Numero Personas=" + numeroPersonas +
                '}';
    }
}
