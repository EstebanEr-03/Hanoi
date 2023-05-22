public class Pila {
    private int contNodo=0;
    private Nodo cabeza;

    public int getContNodo() {
        return contNodo;
    }

    public Nodo getCabeza() {
        return cabeza;
    }

    public void push(Nodo n){
        contNodo++;
        if(cabeza==null){
            cabeza=n;
        }else {
            n.setAbajo(cabeza);
            cabeza.setArriba(n);

            cabeza=n;
        }

    }
    public void pop(){
        if(contNodo>0){
            contNodo--;

            cabeza=cabeza.getAbajo();
        }
    }
    public String peek(){
        return cabeza.getDato();
    }
}
