package adt;
/**
 * Esempio semplificato di nodo per lista semplice concatenata
 * formata da nodi legati tra loro da un riferimento
 * Contiene solo una stringa
 * Implementa l'interfaccia Comparable anche se nel caso specifico
 * non sarebbe indispensabili
 * @author peroni
 */
public class nodo<T>{
    /**
     * Rappresenta una versione semplificate dell'elemento contenuto nella lista
     */
    private T info;
    /**
     * Riferimento all'elemento successivo
     */
    private nodo next;
    /**
     * Costruttore di default inizializza a null sia l'informazione che il
     * puntatore all'elemento successivo
     */
    public nodo(){
        info=null;
        next=null;
    }
    /**
     * Costruttore parametrico inizializza l'informazione e il riferimento all'
     * elemento successivo in base ai parametri ricevuti in ingresso
     * @param info Rappresentazione semplificata dell'informazione
     * @param next riferimento all'elemento successivo (null per l'ultimo)
     */
    public nodo(T info,nodo next){
        this.info=info;
        this.next=next;
    }
    /**
     * Restituisce l'elemento di informazione contenuto nel nodo
     * @return informazione contenuta nel nodo
     */
    public T getInfo(){
        return info;
    }
    /**
     * Restituisce il riferimento al nodo successivo
     * @return riferimento al nodo successivo (null se è l'ultimo)
     */
    public nodo getNext(){
        return next;
    }
    /**
     * Modifica l'informazione in base al parametro di ingresso
     * @param info informazione da inserire nel nodo
     */
    public void setInfo(T info){
        this.info=info;
    }
    /**
     * Modifica il riferimento al nodo successivo in base al parametro di ingresso
     * @param next riferimento al nodo successivo
     */
    public void setNext(nodo next){
        this.next=next;
    }
    /**
     * scavalca il metodo di default restituendo una rappresentazione
     * testuale del nodo
     * @return rappresentazione testuale del nodo
     */
    // @Override
    // public String toString(){
    //    return info;
    //}
    /**
     * Implementa il metodo compareTo dell'interfaccia Comparable
     * confronta due nodi secondo un criterio specifico relativo
     * all'informazione contenuta
     * Nel caso semplificato il confronto è banale perchè il nodo contiene
     * solo una stringa
     * @param o riferimento all'oggetto da confrontare
     * @return 1 se questo oggetto è maggiore dell'oggetto da confrontare
     * 0 se sono uguali, -1 se l'oggetto è minore
     */
    /** public int compareTo(Object o) {
     int ris=0;
     nodo n=(nodo) o;
     ris=this.getInfo().compareTo(n.getInfo());
     return ris;
     }*/
}
