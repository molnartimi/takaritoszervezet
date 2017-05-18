package takaritoszervezet;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;
import takaritoszervezet.Hely;

public class EpuletView extends GridWorldView {
	
	// GUI k�sleltet�se, hogy l�that� legyen, ki hol tev�kenykedik
	private long var = 800;
	
	enum teremtipus {
		nagyterem,
		kisterem,
		folyoso,
		mosdo
	}
	
	private List<Hely> helyek;
	
	private Epulet environment;
	
	private JPanel panel;
	private JLabel label;
	private JLabel whatsgoingon;
	
	public EpuletView(GridWorldModel model, String title, int windowSize, Epulet e) {
		super(model, title, windowSize);
			
		environment = e;
		
		setVisible(true);
        repaint();
	}
	
	@Override
    public void initComponents(int width) {		
		super.initComponents(width);
				
		// kezdeti v�ltoz�k inicializ�l�sa
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel pan= new JPanel();
		pan.setLayout(new GridLayout(10,10));
		label = new JLabel("");
		whatsgoingon = new JLabel("�zenet: ");		
        panel.add(whatsgoingon, BorderLayout.NORTH);
        panel.add(label);
        
		helyek = new ArrayList<Hely>();
		
        // helyek defini�l�sa    
        Hely folyoso = new Hely("foly.", teremtipus.folyoso);
        Hely ib028 = new Hely("ib028", teremtipus.nagyterem);
        Hely ib027 = new Hely("ib027", teremtipus.kisterem);
        Hely ib026 = new Hely("ib026", teremtipus.kisterem);
        Hely mosdok = new Hely("mosdok", teremtipus.mosdo);
        
        // termek hozz�ad�sa helyek list�hoz
        helyek.add(folyoso);
        helyek.add(ib028);
        helyek.add(ib027);
        helyek.add(ib026);  
        helyek.add(mosdok);
        
        // termek elhelyez�se a paneleken
        for (int j=1; j<=10; j++) {
        	for (int i=1; i<=10; i++) {
        		JPanel p = new JPanel();
        		
        		if (i >= 1 && i <= 6 && j >= 1 && j <= 3) {
        			ib028.addPanel(p);
        		} else if (i >= 8 && i <= 10 && j >= 3 && j <= 5) {
        			ib027.addPanel(p);
        		} else if (i >= 8 && i <= 10 && j >= 6 && j <= 8) {
        			ib026.addPanel(p);
        		} else if ((i == 1 && j == 6) || 
        				   (i == 1 && j == 7) ||
        				   (i == 10 && j == 1) ||
        				   (i == 10 && j == 2) ||
        				   (i == 10 && j == 9) ||
        				   (i == 10 && j == 10))
        		{
        			mosdok.addPanel(p);
        		} else {
        			folyoso.addPanel(p);
        		}
        		pan.add(p);        		
        	}
        	
        	panel.add(pan, BorderLayout.SOUTH);     	
        }
        
        // els� nap els� reggel�n minden terem tiszta
        for (int i=0; i<helyek.size(); i++) {
        	helyek.get(i).setPiszkos(false);
        }  
    	
    	// adunk a termekhez kuk�kat
    	ib028.addKuka(13);
    	ib028.addKuka(16);
    	ib027.addKuka(6);
    	ib026.addKuka(6);
    	
    	// kitessz�k az �genseket a folyos�ra
    	folyoso.ittEgyAgens(true);
    	folyoso.ittEgyAgens(true);
    	folyoso.ittEgyAgens(true);
    	folyoso.ittEgyAgens(true);
    	folyoso.ittEgyAgens(true);

        setContentPane(panel);
        
	}

	/**
	 * Mutatja az aktu�lis napot �s id�t
	 * @param nap	- 1., 2., 3. nap
	 * @param ido	- �ra, negyed �ra
	 */
	public void setIdo(String nap, String ido) {
		label.setText(nap + ". nap " + ido + " �ra");
		for (int i=0; i<helyek.size(); i++) {
			helyek.get(i).setAllapot1("");
			helyek.get(i).setAllapot2("");
			whatsgoingon.setText("�zenet: ");
		}
	}
	
	/** 
	 * �zenetsorban jelzi, hogy el�rt�k a nap v�g�t.
	 */
	public void napVege() {
		whatsgoingon.setText("�zenet: Nap v�ge.");
	}

	/** 
	 * �zenetsorban jelzi, hogy el�rt�k a h�t v�g�t.
	 */
	public void hetVege() {
		whatsgoingon.setText("�zenet: H�t v�ge.");
	}

	/** 
	 * �zenetsorban jelzi, hogy eb�dsz�net van.
	 */
	public void ebed() {
		whatsgoingon.setText("�zenet: Eb�dsz�net!");
	}

	
	/** 
	 * Ki�ratja, ha a teremben �ppen konferencia zajlik
	 * @param terem		- terem azonos�t�ja
	 */
	public void konferencia(String terem) {
		if (terem.equals("IB28")) {
			helyek.get(1).setAllapot1("konf.");
			helyek.get(1).setAllapot2("");
		} else if (terem.equals("IB27")) {
			helyek.get(2).setAllapot1("konf.");
			helyek.get(2).setAllapot2("");
		} else if (terem.equals("IB26")) {
			helyek.get(3).setAllapot1("konf.");
			helyek.get(3).setAllapot2("");
		}		
	}

	/** 
	 * Ki�ratja, h�ny f� tart�zkodik a teremben adott �ra alatt
	 * @param terem		- terem azonos�t�ja
	 * @param letszam	- l�tsz�m
	 */
	public void oraVan(String terem, int letszam){
		if (terem.equals("IB28")) {
			helyek.get(1).setAllapot1("�ra:");
			helyek.get(1).setAllapot2(Integer.toString(letszam));
		} else if (terem.equals("IB27")) {
			helyek.get(2).setAllapot1("�ra:");
			helyek.get(2).setAllapot2(Integer.toString(letszam));
		} else if (terem.equals("IB26")) {
			helyek.get(3).setAllapot1("�ra:");
			helyek.get(3).setAllapot2(Integer.toString(letszam));
		}
	}
	
	/**
	 * �gens felmos�sra k�ld�se.
	 * @param ember		- �gens azonos�t�ja
	 * @param terem		- terem azonos�t�ja
	 */
	public void felmos(String ember, String terem) {
		try {
			
			// megjelen�tend� n�v
			String nev = "";
			if(ember.equals("tak_Bea"))
				nev="Bea";
			if(ember.equals("tak_Izolda"))
				nev="Iza";
			if (ember.equals("tak_Lali"))
				nev="Lali";
			if(ember.equals("tak_Mari"))
				nev="Mari";
			if(ember.equals("tak_Zsombor"))
				nev="Zs.";
			
			if (terem.equals("IB28")) {
				helyek.get(0).ittEgyAgens(false);	// bemegy a folyos�r�l
				helyek.get(1).ittEgyAgens(true);	// a terembe
				helyek.get(1).setAllapot1("mos:");
				helyek.get(1).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(1).setPiszkos(false);
				helyek.get(1).ittEgyAgens(false);	// kimegy a teremb�l
				helyek.get(0).ittEgyAgens(true);	// vissza a folyos�ra
			} else if (terem.equals("IB27")) {
				helyek.get(0).ittEgyAgens(false);
				helyek.get(2).ittEgyAgens(true);
				helyek.get(2).setAllapot1("mos:");
				helyek.get(2).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(2).setPiszkos(false);
				helyek.get(2).ittEgyAgens(false);
				helyek.get(0).ittEgyAgens(true);
			} else if (terem.equals("IB26")) {
				helyek.get(0).ittEgyAgens(false);
				helyek.get(3).ittEgyAgens(true);
				helyek.get(3).setAllapot1("mos:");
				helyek.get(3).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(3).setPiszkos(false);
				helyek.get(3).ittEgyAgens(false);
				helyek.get(0).ittEgyAgens(true);
			} else if (terem.equals("folyoso")) {
				helyek.get(0).setAllapot1("mos:");
				helyek.get(0).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(0).setPiszkos(false);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * �gens kuka�r�t�sre k�ld�se.
	 * @param ember		- �gens azonos�t�ja
	 * @param terem		- terem azonos�t�ja
	 */
	public void kukaturit(String ember, String terem) {
		try {	
			String nev = "";
			if(ember.equals("tak_Bea"))
				nev="Bea";
			if(ember.equals("tak_Izolda"))
				nev="Iza";
			if (ember.equals("tak_Lali"))
				nev="Lali";
			if(ember.equals("tak_Mari"))
				nev="Mari";
			if(ember.equals("tak_Zsombor"))
				nev="Zs.";		
			
			if (terem.equals("IB28")) {
				helyek.get(0).ittEgyAgens(false);
				helyek.get(1).ittEgyAgens(true);
				helyek.get(1).setAllapot1("kuka:");
				helyek.get(1).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(1).teleKuka(false);
				helyek.get(1).ittEgyAgens(false);
				helyek.get(0).ittEgyAgens(true);
			} else if (terem.equals("IB27")) {
				helyek.get(0).ittEgyAgens(false);
				helyek.get(2).ittEgyAgens(true);
				helyek.get(2).setAllapot1("kuka:");
				helyek.get(2).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(2).teleKuka(false);
				helyek.get(2).ittEgyAgens(false);
				helyek.get(0).ittEgyAgens(true);
			} else if (terem.equals("IB26")) {
				helyek.get(0).ittEgyAgens(false);
				helyek.get(3).ittEgyAgens(true);
				helyek.get(3).setAllapot1("kuka:");
				helyek.get(3).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(3).teleKuka(false);
				helyek.get(3).ittEgyAgens(false);
				helyek.get(0).ittEgyAgens(true);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * �gens mosd� tiszt�t�sra k�ld�se.
	 * @param ember		- �gens azonos�t�ja
	 */
	public void mosdottisztit(String ember) {		
		try {
			helyek.get(0).ittEgyAgens(false);	// elhagyja a folyos�t
			helyek.get(4).ittEgyAgens(true);	// bemegy egy mosd�ba
			Thread.sleep(var);
			helyek.get(4).ittEgyAgens(false);	// kimegy a mosd�b�l
			helyek.get(0).ittEgyAgens(true);	// vissza a folyos�ra
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Terem koszosod�s�t val�s�tja meg.
	 * @param terem		- terem azonos�t�ja
	 */
	public void koszoslett(String terem) {
		if (terem.equals("IB28")) {
			helyek.get(1).setPiszkos(true);
		} else if (terem.equals("IB27")) {
			helyek.get(2).setPiszkos(true);
		} else if (terem.equals("IB26")) {
			helyek.get(3).setPiszkos(true);
		} else if (terem.equals("folyoso")) {
			helyek.get(0).setPiszkos(true);
		}
	}
	
	/**
	 * Kuk�k megfelel� megjelen�t�s��rt felel.
	 * @param terem		- terem azonos�t�ja
	 */
	public void telekuka(String terem) {
		if (terem.equals("IB28")) {
			helyek.get(1).teleKuka(true);
		} else if (terem.equals("IB27")) {
			helyek.get(2).teleKuka(true);
		} else if (terem.equals("IB26")) {
			helyek.get(3).teleKuka(true);
		}
	}
	
	/**
	 * �zenetsoron jelez, ha egy �gens �jra szabad.
	 * @param agName	- �gens azonos�t�ja
	 */
	public void szabad(String agName) {
		whatsgoingon.setText("�zenet: "+agName+" �gens �jra szabad.");
	}
}
