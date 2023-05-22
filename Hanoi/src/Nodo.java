public class Nodo {
    private String dato;
    private Nodo arriba;
    private Nodo abajo;

    public String getDato() {
        return dato;
    }

    public void setDato(String dato) {
        this.dato = dato;
    }

    public Nodo getArriba() {
        return arriba;
    }

    public void setArriba(Nodo arriba) {
        this.arriba = arriba;
    }

    public Nodo getAbajo() {
        return abajo;
    }

    public void setAbajo(Nodo abajo) {
        this.abajo = abajo;
    }
}
