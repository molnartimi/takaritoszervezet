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
	
	// GUI késleltetése, hogy látható legyen, ki hol tevékenykedik
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
				
		// kezdeti változók inicializálása
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel pan= new JPanel();
		pan.setLayout(new GridLayout(10,10));
		label = new JLabel("");
		whatsgoingon = new JLabel("Üzenet: ");		
        panel.add(whatsgoingon, BorderLayout.NORTH);
        panel.add(label);
        
		helyek = new ArrayList<Hely>();
		
        // helyek definiálása    
        Hely folyoso = new Hely("foly.", teremtipus.folyoso);
        Hely ib028 = new Hely("ib028", teremtipus.nagyterem);
        Hely ib027 = new Hely("ib027", teremtipus.kisterem);
        Hely ib026 = new Hely("ib026", teremtipus.kisterem);
        Hely mosdok = new Hely("mosdok", teremtipus.mosdo);
        
        // termek hozzáadása helyek listához
        helyek.add(folyoso);
        helyek.add(ib028);
        helyek.add(ib027);
        helyek.add(ib026);  
        helyek.add(mosdok);
        
        // termek elhelyezése a paneleken
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
        
        // elsõ nap elsõ reggelén minden terem tiszta
        for (int i=0; i<helyek.size(); i++) {
        	helyek.get(i).setPiszkos(false);
        }  
    	
    	// adunk a termekhez kukákat
    	ib028.addKuka(13);
    	ib028.addKuka(16);
    	ib027.addKuka(6);
    	ib026.addKuka(6);
    	
    	// kitesszük az ágenseket a folyosóra
    	folyoso.ittEgyAgens(true);
    	folyoso.ittEgyAgens(true);
    	folyoso.ittEgyAgens(true);
    	folyoso.ittEgyAgens(true);
    	folyoso.ittEgyAgens(true);

        setContentPane(panel);
        
	}

	/**
	 * Mutatja az aktuális napot és idõt
	 * @param nap	- 1., 2., 3. nap
	 * @param ido	- óra, negyed óra
	 */
	public void setIdo(String nap, String ido) {
		label.setText(nap + ". nap " + ido + " óra");
		for (int i=0; i<helyek.size(); i++) {
			helyek.get(i).setAllapot1("");
			helyek.get(i).setAllapot2("");
			whatsgoingon.setText("Üzenet: ");
		}
	}
	
	/** 
	 * Üzenetsorban jelzi, hogy elértük a nap végét.
	 */
	public void napVege() {
		whatsgoingon.setText("Üzenet: Nap vége.");
	}

	/** 
	 * Üzenetsorban jelzi, hogy elértük a hét végét.
	 */
	public void hetVege() {
		whatsgoingon.setText("Üzenet: Hét vége.");
	}

	/** 
	 * Üzenetsorban jelzi, hogy ebédszünet van.
	 */
	public void ebed() {
		whatsgoingon.setText("Üzenet: Ebédszünet!");
	}

	
	/** 
	 * Kiíratja, ha a teremben éppen konferencia zajlik
	 * @param terem		- terem azonosítója
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
	 * Kiíratja, hány fó tartózkodik a teremben adott óra alatt
	 * @param terem		- terem azonosítója
	 * @param letszam	- létszám
	 */
	public void oraVan(String terem, int letszam){
		if (terem.equals("IB28")) {
			helyek.get(1).setAllapot1("óra:");
			helyek.get(1).setAllapot2(Integer.toString(letszam));
		} else if (terem.equals("IB27")) {
			helyek.get(2).setAllapot1("óra:");
			helyek.get(2).setAllapot2(Integer.toString(letszam));
		} else if (terem.equals("IB26")) {
			helyek.get(3).setAllapot1("óra:");
			helyek.get(3).setAllapot2(Integer.toString(letszam));
		}
	}
	
	/**
	 * Ágens felmosásra küldése.
	 * @param ember		- ágens azonosítója
	 * @param terem		- terem azonosítója
	 */
	public void felmos(String ember, String terem) {
		try {
			
			// megjelenítendõ név
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
				helyek.get(0).ittEgyAgens(false);	// bemegy a folyosóról
				helyek.get(1).ittEgyAgens(true);	// a terembe
				helyek.get(1).setAllapot1("mos:");
				helyek.get(1).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(1).setPiszkos(false);
				helyek.get(1).ittEgyAgens(false);	// kimegy a terembõl
				helyek.get(0).ittEgyAgens(true);	// vissza a folyosóra
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
	 * Ágens kukaürítésre küldése.
	 * @param ember		- ágens azonosítója
	 * @param terem		- terem azonosítója
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
	 * Ágens mosdó tisztításra küldése.
	 * @param ember		- ágens azonosítója
	 */
	public void mosdottisztit(String ember) {		
		try {
			helyek.get(0).ittEgyAgens(false);	// elhagyja a folyosót
			helyek.get(4).ittEgyAgens(true);	// bemegy egy mosdóba
			Thread.sleep(var);
			helyek.get(4).ittEgyAgens(false);	// kimegy a mosdóból
			helyek.get(0).ittEgyAgens(true);	// vissza a folyosóra
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Terem koszosodását valósítja meg.
	 * @param terem		- terem azonosítója
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
	 * Kukák megfelelõ megjelenítéséért felel.
	 * @param terem		- terem azonosítója
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
	 * Üzenetsoron jelez, ha egy ágens újra szabad.
	 * @param agName	- ágens azonosítója
	 */
	public void szabad(String agName) {
		whatsgoingon.setText("Üzenet: "+agName+" ágens újra szabad.");
	}
}
