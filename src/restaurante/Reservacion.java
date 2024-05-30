package restaurante;


import java.io.Serializable;
import java.util.Date;

public class Reservacion implements Serializable {
    private static final long serialVersionUID = 1L;
    private String id;
    private String clienteId;
    private Date fecha;
    private int numeroPersonas;

    public Reservacion(String id, String clienteId, Date fecha, int numeroPersonas) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.numeroPersonas = numeroPersonas;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClienteId() {
        return this.clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getNumeroPersonas() {
        return this.numeroPersonas;
    }

    public void setNumeroPersonas(int numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public String toString() {
        String var10000 = this.id;
        return "Reservacion{id='" + var10000 + "', DPI='" + this.clienteId + "', fecha=" + String.valueOf(this.fecha) + ", Numero Personas=" + this.numeroPersonas + "}";
    }
}
