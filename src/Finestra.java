import adt.Auto;

import javax.swing.*;
import java.awt.*;
import adt.*;
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
    private Pila <Auto> traghetto=new Pila<>();

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
                Auto toInsert=new Auto((String)inputFieldTarga.getText(),Integer.parseInt(inputFieldLunghezza.getText()));
                traghetto.push(toInsert);
                if (traghetto.getLunghezza())
                    //TODO fai controllo se il traghetto è pieno e disabilita l'input del tasto

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



