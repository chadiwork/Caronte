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
private JLabel lblEseguite;
private boolean semaforo;

//costruisco il traghetto con dimensione massima a piacere(200)
private Traghetto <Auto> traghAttuale =new Traghetto<>(200);

public Finestra(String title, int larghezza, int altezza) throws HeadlessException {
	super(title);
	this.setContentPane(new Finestra().rootPanel);

	this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	this.pack();
	this.setSize(larghezza, altezza);
	this.setPosizioneCentro();
	this.setVisible(true);
	this.setResizable(false);

}


public Finestra() {

	btnCaricaAuto.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			//aggiunge auto con dati da field(parsati) al traghetto

			//controllo che sta aggiungendo qualcosa di sensato
			if (inputFieldLunghezza.getText().matches("^[-+]?\\d+(\\.\\d+)?$")) {

				//creo auto da aggiungere alla pila
				Auto toInsert;

				String targaTMP=inputFieldTarga.getText();
				int lungezzaTMP=Integer.parseInt(inputFieldLunghezza.getText());

				toInsert = new Auto(targaTMP,lungezzaTMP);

				if (!traghAttuale.isTraghettoPieno()){

					//inizializzazione estetica
					if (txtAreaInseriti.getText().equals("Ancora nulla qui...")){
						txtAreaInseriti.setText("");
					}
					//effettive aggiunte alla pila e azioni nella GUI
					traghAttuale.addAuto(toInsert);
					txtAreaInseriti.append("Auto: "+toInsert.getTarga()+" lunga "+toInsert.getLunghezza()+" caricata"+"\n");
					txtAreaInseriti.append("Totale mezzi: "+ traghAttuale.getNumeroAuto()+" | spazio rimanente:"+ traghAttuale
							.getSpazioRimanente()+"\n");

					//pulisco campo inserimento
					inputFieldTarga.setText("");
					inputFieldLunghezza.setText("");

				} else {
					lblUltimoInserito.setText("Il traghetto è già pieno!");
				}
			} else {
				lblUltimoInserito.setText("Inserisci solo numeri (interi) nel campo lunghezza!");
			}


		}
	});
}

public static void main(String[] args) throws Exception {

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



