/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package adt;

/**
 *
 * Esempio semplificato di pila, realizzata come lista semplice
 * formata da nodi legati tra loro da un riferimento
 * Il nodo ha una struttura fissa e contiene solo una stringa
 * @author Cenacchi
 */
//implementazione pila con lista (push si aggiunge un elemento in testa, pop si elimina la testa)
public class Pila<T> {

private nodo ultimo;
//MODIFICA
public Pila(){
	//inizializzo la pila, il primo nodo è null
	ultimo=null;

}
//metodi della pila
public boolean isEmpty(){
	return ultimo==null;
}
public void push(T info){
	nodo n=new nodo();
	n.setInfo(info);
	n.setNext(ultimo);
	ultimo=n;
}
public void pop() throws Exception{
	//assert isEmpty() : "pop su pila vuota";
	if (this.isEmpty()) throw new Exception ("impossibile effettuare il pop perchè la pila è vuota");
	ultimo=ultimo.getNext();



}
public T top(){
	return (T)ultimo.getInfo();
}

}
