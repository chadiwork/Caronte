package adt;

/**
 * Creato da Vlady il 28/01/2016.
 * in origine parte del progetto:
 * Pila_Case_Traghetto
 */
public class Traghetto<T> extends Pila{

int lunghMAX;
int lunghCORR =0;
int numeroAuto=0;

public Traghetto(int lunghezzaMassima) {
	this.lunghMAX = lunghezzaMassima;
}

public int getLunghMAX() {
	return lunghMAX;
}

public int getLunghCORR() {
	return lunghCORR;
}

public int getNumeroAuto() {
	return numeroAuto;
}

public void setNumeroAuto(int numeroAuto) {
	this.numeroAuto = numeroAuto;
}

public void addAuto(Auto autoDaAggiungere){
	int lunghAuto=autoDaAggiungere.getLunghezza();

	if (this.lunghMAX>this.lunghCORR+lunghAuto){
		this.lunghCORR = this.lunghCORR +lunghAuto;
		this.numeroAuto=this.numeroAuto+1;
		this.push(autoDaAggiungere);

	}
}

public int getSpazioRimanente(){
	return this.lunghMAX-this.lunghCORR;
}

public boolean isTraghettoPieno(){
	return getLunghMAX() <= getLunghCORR();
}
}
