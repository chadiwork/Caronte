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
	public JTextField inputField;
	private JPanel pnlStoriaViaggi;
	public JButton btnCaricaAuto;
	public JLabel lblConteggio;
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

	}

    public static void main(String[] args) throws Exception {

        Finestra f = new Finestra("Traghettami il pene| By Hopeless13",600,350);

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



