import adt.Auto;
import adt.Traghetto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Creato da Vlady il 15/01/2016.
 * in origine parte del progetto:
 * PilaProjYakovenko
 */
public class Finestra extends JFrame {

<<<<<<< HEAD
    //componenti GUI
    private JPanel rootPanel;
    private JTextField inputField;
    private JPanel pnlStoriaViaggi;
    private JButton btnCaricaAuto;
    private JLabel lblViaggi;
    private JPanel pnlCenter;
    private JPanel pnlDX;
    private JTextArea txtAreaUscita;
    private JPanel pnlTarga;
    private JTextField inputFieldTarga;
    private JTextField inputFieldLunghezza;
    private JPanel pnlLunghezza;
    private JPanel pnlRiempimentoTraghetto;
    private JPanel pnlStoriaInserimenti;
    private JLabel lblUltimoInserito;
    private JButton btnEsciAuto;
    private JTextArea txtAreaInseriti;
    private JPanel pnlTasti;
    private JProgressBar progressCapienzaTraghetto;
    private JButton btnAutoCasuale;
    private JPanel pnlSemaforo;
    private JLabel lblGrandezzaTraghetto;
    private JLabel lblSogliaViaggio;
    private JPanel panelLabel;
    private JLabel lblNumeroGrandezza;
    private JLabel lblNumeroSoglia;
    private JLabel lblEseguite;

    //inizio variabili d'appoggio
    //private String semaforo; //può essere solo R,Y,G (red,yellow,green), l'ho usato per avere più possibilità rispetto al
    // booleano
    private int dimensioneTraghetto=100;//tarata per essere 100 ma modificabile
    private int caratteriDiTarga=7;//tarata per 7 ma modificabile
    private int sogliaViaggioMinimo=75;//tarata per 75 ma modificabile
    private boolean arrADest =false;

    private Color coloreSuccesso =new Color(0, 132, 0);
    private Color coloreMain =new Color(0, 146, 255);

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
        this.setResizable(false);
    }

    public Finestra() {

        btnCaricaAuto.setBackground(coloreMain);
        btnCaricaAuto.setForeground(Color.white);

        setSemaforo();
        coloraBottoneAndProgredisci();

        lblNumeroGrandezza.setBackground(Color.green);
        lblNumeroGrandezza.setText(String.valueOf(traghAttuale.getLunghMAX()));

        lblNumeroSoglia.setBackground(Color.green);
        lblNumeroSoglia.setText(String.valueOf(sogliaViaggioMinimo));

        btnCaricaAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                setSemaforo();

                //inizializzazione estetica: magia, non toccare
                if (txtAreaInseriti.getText().equals("Ancora nulla qui...")||txtAreaInseriti.getText().equals("Traghetto " +
                        "vuoto")){
                    txtAreaInseriti.setText("");
                }

                if (arrADest){
                    lblViaggi.setForeground(Color.white);
                    lblViaggi.setText("...");
                }

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

                            //effettive aggiunte alla pila e azioni nella GUI

                            if (traghAttuale.addAuto(toInsert)) {
                                //tutto apposto, posso finalmente inserire i dati nella pila

                                //riattivo se c'è qualcosina e pulisco sotto
                                if (!traghAttuale.isEmpty()){
                                    btnEsciAuto.setEnabled(true);
                                    txtAreaUscita.setText("Traghetto in riempimento...");
                                }

                                txtAreaInseriti.append("Auto: "+toInsert.getTarga()+" lunga "+toInsert.getLunghezza()+" caricata"+"\n");
                                txtAreaInseriti.append("Totale mezzi: "+ traghAttuale.getNumeroAuto()+" | spazio rimanente:"+ traghAttuale
                                        .getSpazioRimanente()+"\n");

                                //pulisco campo inserimento
                                inputFieldTarga.setText("");
                                inputFieldLunghezza.setText("");
                                lblUltimoInserito.setForeground(coloreSuccesso);
                                lblUltimoInserito.setText("Auto "+traghAttuale.getNumeroAuto()+" aggiunta correttamente");

                                lblViaggi.setForeground(coloreMain);
                                lblViaggi.setText("In attesa degli inserimenti, traghetto fermo");


                                coloraBottoneAndProgredisci();

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
                coloraBottoneAndProgredisci();
                setSemaforo();
            }
        });

        btnEsciAuto.setBackground(coloreMain);
        btnEsciAuto.setForeground(Color.white);

        btnEsciAuto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                btnEsciAuto.setBackground(coloreMain);

                //inizializzazione estetica
                if (txtAreaUscita.getText().equals("Ancora nulla qui...")||txtAreaUscita.getText().equals("Traghetto in riempimento...")){
                    txtAreaUscita.setText("");
                }

                //se sono arrivato allora
                if (arrADest){
                    //vedo se sta uscendo qualcosa

                    btnEsciAuto.setText("Esci l'auto");
                    btnEsciAuto.setBackground(coloreSuccesso);

                    Auto inUscita=(Auto)traghAttuale.top();

                    txtAreaUscita.append("Esce auto: "+inUscita.getTarga()+"\n");

                    lblViaggi.setForeground(coloreSuccesso);
                    lblViaggi.setText("Liberato "+inUscita.getLunghezza()+" di spazio");

                    try {
                        //esco EFFETTIVAMENTE l'auto

                        traghAttuale.pop();

                        txtAreaUscita.append("Mezzi ancora dentro: "+ traghAttuale.getNumeroAuto()+" spazio " +
                                "disponibile:"+traghAttuale.getSpazioRimanente()+"\n");

                        //soliti colori
                        coloraBottoneAndProgredisci();

                        //da cancellare penso, però anche no
                        if (traghAttuale.top()==null){
                            txtAreaUscita.append("Tutti le auto sono ora fuori."+"\n");
                            arrADest =false;
                            btnEsciAuto.setText("Esegui viaggio");

                            System.out.println("if giusto scatenato");
                        }

                    } catch (Exception e1) {
//					se è vuota allora:
                        txtAreaUscita.append("Non ci sono più auto da far uscire"+"\n");
                        arrADest =false;
                        btnEsciAuto.setText("Esegui viaggio");
                        txtAreaInseriti.setText("Traghetto vuoto");
                        coloraBottoneAndProgredisci();
                        btnCaricaAuto.setEnabled(true);
                    }
                }

                //controllo se è abbastanza pieno, sempre se non sono ancora a destinazione
                if (traghAttuale.getPercentualePieno()>=sogliaViaggioMinimo && !arrADest) {
                    lblViaggi.setForeground(Color.blue);
                    lblViaggi.setText("Viaggio in corso...");

                    btnEsciAuto.setText("Esci l'auto");

                    //rimetto apposto il campo sopra
                    txtAreaInseriti.setText("Inserimenti completati...");

                    arrADest =true;

                    //disabilito bottone inserimenti e dico che è successo
                    btnCaricaAuto.setEnabled(false);
                    lblUltimoInserito.setForeground(coloreMain);
                    lblUltimoInserito.setText("Inserimento disabilitato: auto in uscita");

                    coloraBottoneAndProgredisci();

                } else if (traghAttuale.getPercentualePieno()<sogliaViaggioMinimo && !arrADest){
                    lblViaggi.setForeground(Color.red);
                    lblViaggi.setText("Il traghetto non è ancora stato riempito a sufficienza!");

                    //rimette appostso robe
                    if(traghAttuale.isEmpty()){
                        lblViaggi.setForeground(coloreMain);
                        lblViaggi.setText("In attesa degli inserimenti, traghetto fermo");

                        lblUltimoInserito.setForeground(coloreSuccesso);
                        lblUltimoInserito.setText("Traghetto in attesa");
                        btnEsciAuto.setEnabled(false);
                    }
                }

                coloraBottoneAndProgredisci();
                setSemaforo();
            }
        });
        btnAutoCasuale.setBackground(coloreSuccesso);
        btnAutoCasuale.setForeground(Color.white);

        btnAutoCasuale.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                inputFieldLunghezza.setText(String.valueOf(ThreadLocalRandom.current().nextInt(1, 20 + 1)));

                inputFieldTarga.setText(new RandomString(7).nextString());
                btnAutoCasuale.setBackground(new Color(ThreadLocalRandom.current().nextInt(1, 255 + 1),
                        ThreadLocalRandom.current().nextInt(1, 255 + 1),ThreadLocalRandom.current().nextInt(1, 255 + 1)));
            }
        });
    }

    public static void main(String[] args) throws Exception {
        //main
        Finestra f = new Finestra("Caronte 1.1 | By Hopeless13",750,400);
    }
    public void coloraBottoneAndProgredisci(){
        //rimetto apposto il progresso
        progressCapienzaTraghetto.setValue(traghAttuale.getPercentualePieno());

        btnCaricaAuto.setBackground(new Color(traghAttuale.getPercentualePieno()*2, 135-traghAttuale.getPercentualePieno
                (), 150));
    }

    public void setSemaforo(){

        if ((traghAttuale.isEmpty()||!traghAttuale.isTraghettoPieno())&&!arrADest){
            pnlSemaforo.setBackground(Color.green);
        }

        if (traghAttuale.getPercentualePieno()>70){
            pnlSemaforo.setBackground(Color.yellow);
        }

        if (traghAttuale.isTraghettoPieno()||arrADest){
            pnlSemaforo.setBackground(Color.red);
        }

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
=======
//componenti GUI
private JPanel rootPanel;
private JTextField inputField;
private JPanel pnlStoriaViaggi;
private JButton btnCaricaAuto;
private JLabel lblViaggi;
private JPanel pnlCenter;
private JPanel pnlDX;
private JTextArea txtAreaUscita;
private JPanel pnlTarga;
private JTextField inputFieldTarga;
private JTextField inputFieldLunghezza;
private JPanel pnlLunghezza;
private JPanel pnlRiempimentoTraghetto;
private JPanel pnlStoriaInserimenti;
private JLabel lblUltimoInserito;
private JButton btnEsciAuto;
private JTextArea txtAreaInseriti;
private JPanel pnlTasti;
private JProgressBar progressCapienzaTraghetto;
private JButton btnAutoCasuale;
private JLabel lblEseguite;

//inizio variabili d'appoggio
private String semaforo; //può essere solo R,Y,G (red,yellow,green), l'ho usato per avere più possibilità rispetto al
// booleano
private int dimensioneTraghetto=100;//tarata per essere 100 ma modificabile
private int caratteriDiTarga=7;//tarata per 7 ma modificabile
private int sogliaViaggioMinimo=75;//tarata per 75 ma modificabile
private boolean arrivatoADestinazione=false;

private Color coloreSuccesso =new Color(0, 132, 0);
private Color coloreMain =new Color(0, 146, 255);

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

	btnCaricaAuto.setBackground(coloreMain);
	btnCaricaAuto.setForeground(Color.white);

	btnCaricaAuto.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			//inizializzazione estetica: magia, non toccare
			if (txtAreaInseriti.getText().equals("Ancora nulla qui...")||txtAreaInseriti.getText().equals("Traghetto " +
					"vuoto")|| traghAttuale.getPercentualePieno()<sogliaViaggioMinimo && !arrivatoADestinazione){
				txtAreaInseriti.setText("");
			}

			if (arrivatoADestinazione){
				lblViaggi.setForeground(Color.white);
				lblViaggi.setText("...");
			}

			//riattivo se c'è qualcosina
			if (!traghAttuale.isEmpty()){
				btnEsciAuto.setEnabled(true);
			}

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


						//effettive aggiunte alla pila e azioni nella GUI

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

							coloraBottoneAndProgredisci();

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
			coloraBottoneAndProgredisci();
		}
	});

	btnEsciAuto.setBackground(coloreMain);
	btnEsciAuto.setForeground(Color.white);

	btnEsciAuto.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

			btnEsciAuto.setBackground(coloreMain);
			//inizializzazione estetica
			if (txtAreaUscita.getText().equals("Ancora nulla qui...")){
				txtAreaUscita.setText("");
			}

			//se sono arrivato allora
			if (arrivatoADestinazione){
				//vedo se sta uscendo qualcosa

				btnEsciAuto.setText("Esci l'auto");
				btnEsciAuto.setBackground(coloreSuccesso);


				Auto inUscita=(Auto)traghAttuale.top();

				txtAreaUscita.append("Esce auto: "+inUscita.getTarga()+"\n");

				lblViaggi.setForeground(coloreSuccesso);
				lblViaggi.setText("Liberato "+inUscita.getLunghezza()+" di spazio");

				try {
					//esco EFFETTIVAMENTE l'auto

					traghAttuale.pop();

					txtAreaUscita.append("Mezzi ancora dentro: "+ traghAttuale.getNumeroAuto()+" spazio " +
							"disponibile:"+traghAttuale.getSpazioRimanente()+"\n");

					//soliti colori
					coloraBottoneAndProgredisci();

					//da cancellare penso, però anche no
					if (traghAttuale.top()==null){
						txtAreaUscita.append("Tutti le auto sono ora fuori."+"\n");
						arrivatoADestinazione=false;
						btnEsciAuto.setText("Esegui viaggio");

						System.out.println("if giusto scatenato");
					}

				} catch (Exception e1) {
//					se è vuota allora:
					txtAreaUscita.append("Non ci sono auto da far uscire"+"\n");
					arrivatoADestinazione=false;
					btnEsciAuto.setText("Esegui viaggio");
					txtAreaInseriti.setText("Traghetto vuoto");
					coloraBottoneAndProgredisci();
					btnCaricaAuto.setEnabled(true);

				}
			}

			//controllo se è abbastanza pieno, sempre se non sono ancora a destinazione
			if (traghAttuale.getPercentualePieno()>=sogliaViaggioMinimo && !arrivatoADestinazione) {
				lblViaggi.setForeground(Color.blue);
				lblViaggi.setText("Viaggio in corso...");

				btnEsciAuto.setText("Esci l'auto");

				arrivatoADestinazione=true;

				//disabilito bottone inserimenti e dico che è successo
				btnCaricaAuto.setEnabled(false);
				lblUltimoInserito.setForeground(coloreMain);
				lblUltimoInserito.setText("Inserimento disabilitato: auto in uscita");

				coloraBottoneAndProgredisci();

			} else if (traghAttuale.getPercentualePieno()<sogliaViaggioMinimo && !arrivatoADestinazione){
				lblViaggi.setForeground(Color.red);
				lblViaggi.setText("Il traghetto non è ancora stato riempito a sufficienza!");


				//rimette appostso robe
				if(traghAttuale.isEmpty()){
					lblViaggi.setForeground(coloreMain);
					lblViaggi.setText("In attesa degli inserimenti, traghetto fermo");

					lblUltimoInserito.setForeground(coloreSuccesso);
					lblUltimoInserito.setText("Traghetto in attesa");
					btnEsciAuto.setEnabled(false);
				}
			}
		}
	});
	btnAutoCasuale.setBackground(coloreSuccesso);
	btnAutoCasuale.setForeground(Color.white);

	btnAutoCasuale.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

				inputFieldLunghezza.setText(String.valueOf(ThreadLocalRandom.current().nextInt(1, 20 + 1)));

				inputFieldTarga.setText(new RandomString(7).nextString());
				btnAutoCasuale.setBackground(new Color(ThreadLocalRandom.current().nextInt(1, 255 + 1),
						ThreadLocalRandom.current().nextInt(1, 255 + 1),ThreadLocalRandom.current().nextInt(1, 255 + 1)));
		}
	});
}

public static void main(String[] args) throws Exception {

	//main
	Finestra f = new Finestra("Caronte 1.0 | By Hopeless13",600,350);

}
public void coloraBottoneAndProgredisci(){
	//rimetto apposto il progresso
	progressCapienzaTraghetto.setValue(traghAttuale.getPercentualePieno());

	btnCaricaAuto.setBackground(new Color(traghAttuale.getPercentualePieno()*2, 150-traghAttuale.getPercentualePieno
			(), 150));
>>>>>>> origin/master
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





