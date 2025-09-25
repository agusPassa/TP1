package Ordenamiento;

public class Elemento implements Comparable<Elemento> {
    private int clave;
    private String etiqueta;

    public Elemento(int clave, String etiqueta) {
        this.clave = clave;
        this.etiqueta = etiqueta;
    }

    public int getClave() {
        return clave;
    }

    public String getEtiqueta() {
        return etiqueta;
    }

    @Override
    public int compareTo(Elemento otro) {
        return Integer.compare(this.clave, otro.clave);
    }

    @Override
    public String toString() {
        return clave + "(" + etiqueta + ")";
    }
}