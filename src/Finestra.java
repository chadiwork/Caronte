import adt.Auto;
import adt.Traghetto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creato da Vlady il 15/01/2016.
 * in origine parte del progetto:
 * PilaProjYakovenko
 */
public class Finestra extends JFrame {

//componenti GUI
private JPanel rootPanel;
private JTextField inputField;
private JPanel pnlStoriaViaggi;
private JButton btnCaricaAuto;
private JLabel lblConteggio;
private JPanel pnlCenter;
private JPanel pnlDX;
private JTextArea txtAreaViaggi;
private JPanel pnlTarga;
private JTextField inputFieldTarga;
private JTextField inputFieldLunghezza;
private JPanel pnlLunghezza;
private JPanel pnlRiempimentoTraghetto;
private JPanel pnlStoriaInserimenti;
private JLabel lblUltimoInserito;
private JButton btnEseguiViaggio;
private JTextArea txtAreaInseriti;
private JPanel pnlTasti;
private JProgressBar progressCapienzaTraghetto;
private JLabel lblEseguite;

//inizio variabili d'appoggio
private String semaforo; //può essere solo R,Y,G (red,yellow,green), l'ho usato per avere più possibilità rispetto al
// booleano
private int dimensioneTraghetto=100;//tarata per essere 100 ma modificabile
private int caratteriDiTarga=7;//tarata per 7 ma modificabile
private Color coloreSuccesso =new Color(0, 132, 0);

//costruisco il traghetto con dimensione massima a piacere(200)
private Traghetto <Auto> traghAttuale =new Traghetto<>(dimensioneTraghetto);

public Finestra(String title, int larghezza, int altezza) throws HeadlessException {
	//setup iniziale finestra
	super(title);
	this.setContentPane(new Finestra().rootPanel);
	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.pack();
	this.setSize(larghezza, altezza);
	this.setPosizioneCentro();
	this.setVisible(true);
//	this.setResizable(false);

}


public Finestra() {

	btnCaricaAuto.setBackground(new Color(0, 146, 255));
	btnCaricaAuto.setForeground(Color.white);

	btnCaricaAuto.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			//colore default
			btnCaricaAuto.setBackground(new Color(0, 255, 60));

			String targaTMP=inputFieldTarga.getText();

			//aggiunge auto con dati da field(parsati) al traghetto

			//controllo che sta aggiungendo qualcosa di sensato
			if (targaTMP.length()==caratteriDiTarga) {
				if (inputFieldLunghezza.getText().matches("^[-+]?\\d+(\\.\\d+)?$")) {
					//
					//creo auto da aggiungere alla pila
					Auto toInsert;

					int lungezzaTMP=Integer.parseInt(inputFieldLunghezza.getText());

					toInsert = new Auto(targaTMP,lungezzaTMP);

					if (!traghAttuale.isTraghettoPieno()){

						//inizializzazione estetica
						if (txtAreaInseriti.getText().equals("Ancora nulla qui...")){
							txtAreaInseriti.setText("");
						}
						//effettive aggiunte alla pila e azioni nella GUI
//						traghAttuale.addAuto(toInsert);

						if (traghAttuale.addAuto(toInsert)) {
							//tutto apposto, posso finalmente inserire i dati nella pila

							txtAreaInseriti.append("Auto: "+toInsert.getTarga()+" lunga "+toInsert.getLunghezza()+" caricata"+"\n");
							txtAreaInseriti.append("Totale mezzi: "+ traghAttuale.getNumeroAuto()+" | spazio rimanente:"+ traghAttuale
									.getSpazioRimanente()+"\n");

							//pulisco campo inserimento
							inputFieldTarga.setText("");
							inputFieldLunghezza.setText("");
							lblUltimoInserito.setForeground(coloreSuccesso);
							lblUltimoInserito.setText("Auto "+traghAttuale.getNumeroAuto()+" aggiunta correttamente");

							progressCapienzaTraghetto.setValue(traghAttuale.getPercentualePieno());

							//coloro bottone come un semaforo
							if(traghAttuale.getSpazioRimanente()<traghAttuale.getLunghMAX()/5){
								btnCaricaAuto.setBackground(new Color(205, 55, 0));
							}else if(traghAttuale.getSpazioRimanente()<traghAttuale.getLunghMAX()/4){
								btnCaricaAuto.setBackground(new Color(205, 164, 0));
							}else if(traghAttuale.getSpazioRimanente()<traghAttuale.getLunghMAX()/3){
								btnCaricaAuto.setBackground(new Color(178, 205, 0));
							}else if(traghAttuale.getSpazioRimanente()<traghAttuale.getLunghMAX()/2){
								btnCaricaAuto.setBackground(new Color(57, 205, 0));
							}

						} else {
							lblUltimoInserito.setForeground(Color.red);
							lblUltimoInserito.setText("Il traghetto supererebbe la capienza massima. Auto non " +
									"aggiunta");
						}
					} else {
						lblUltimoInserito.setForeground(Color.red);
						lblUltimoInserito.setText("Il traghetto è già pieno!");
					}
				} else {
					lblUltimoInserito.setForeground(Color.red);
					lblUltimoInserito.setText("Inserisci solo numeri (interi) nel campo lunghezza! Ricontrolla...");
				}
			} else {
				lblUltimoInserito.setForeground(Color.red);
				lblUltimoInserito.setText("Il campo targa deve contenere esattamente " +caratteriDiTarga +" caratteri! Ricontrolla...");
			}
		}
	});
}

public static void main(String[] args) throws Exception {

	//main
	Finestra f = new Finestra("Thall | By Hopeless13",600,350);

}


public void setPosizioneCentro() {
	// valuta le dimensioni della finestra
	int larg;
	int alt;
	alt = this.getHeight();
	larg = this.getWidth();
	// serve per la risoluzione dello schermo
	final Toolkit kit = Toolkit.getDefaultToolkit();
	final Dimension dimensione = kit.getScreenSize();
	final int x = dimensione.width / 2 - larg / 2;
	final int y = dimensione.height / 2 - alt / 2;
	this.setLocation(x, y);
}
}



