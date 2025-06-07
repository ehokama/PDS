package Areas;


// Ventas → Cobranzas → Impuestos → Embarque → Logística → Entrega → Seguimiento
// Las areas tambien implementan el Singleton aparte del Observer y del Chain of Responsability
public abstract class Area {
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}

