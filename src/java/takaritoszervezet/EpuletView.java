package takaritoszervezet;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;

public class EpuletView extends GridWorldView {
	
	private long var = 800;
	
	enum teremtipus {
		nagyterem,
		kisterem,
		folyoso,
		mosdo
	}
	
	private class Hely {
		List<JPanel> mezok;
		JLabel nev = new JLabel("");
		JLabel allapot1 = new JLabel("");
		JLabel allapot2 = new JLabel("");
		List<JLabel> kukak;
		teremtipus tipus;
		Color piszkos;
		Color tiszta;
		
		public Hely (String n, teremtipus t){
			mezok = new ArrayList<JPanel>();
			kukak = new ArrayList<JLabel>();
			tipus = t;
			nev.setText(n);
			
			switch (tipus) {
			case folyoso:
				piszkos = new Color(254,173,140);
				tiszta = new Color(198,224,180);
				break;
			case nagyterem:
				piszkos = new Color(246,105,42);
				tiszta = new Color(146,208,80);
				break;
			case kisterem:
				piszkos = new Color(246,105,42);
				tiszta = new Color(146,208,80);
				break;
			case mosdo:
				piszkos = new Color(140,140,140);
				tiszta = new Color(140,140,140);
				break;
			}
		}	
		
		public void addPanel(JPanel p) {
			if (tipus==teremtipus.kisterem) {
				if (mezok.isEmpty()) {
					p.add(nev);		
				}
				if (mezok.size()==3) {
					p.add(allapot1);
				}
				if (mezok.size()==4) {
					p.add(allapot2);
				}
			} else if (tipus==teremtipus.nagyterem) {
				if (mezok.isEmpty()) {
					p.add(nev);		
				}
				if (mezok.size()==6) {
					p.add(allapot1);
				}
				if (mezok.size()==7) {
					p.add(allapot2);
				}
			} else if (tipus==teremtipus.mosdo) {
				p.add(allapot1);
			} else if (tipus==teremtipus.folyoso) {
				if (mezok.size()==7) {
					p.add(allapot1);
				}
				if (mezok.size()==8) {
					p.add(allapot2);
				}
			}
			mezok.add(p);
		}
		
		public void addKuka(int index) {
			JLabel k = new JLabel("o");
			k.setForeground(new Color(77,117,32));
			mezok.get(index).add(k);
			kukak.add(k);
		}
		
		public void teleKuka(boolean televane) {
			if (televane) {
				for (int i=0; i<kukak.size(); i++) {
					kukak.get(i).setForeground(new Color(196,5,0));
				}
			} else {
				for (int i=0; i<kukak.size(); i++) {
					kukak.get(i).setForeground(new Color(77,117,32));
				}
			}
			
		}
		
		public void setPiszkos(boolean piszkose) {
			if (piszkose) {
				for (int i=0; i<mezok.size(); i++) {
					mezok.get(i).setBackground(piszkos);
				}
			} else {
				for (int i=0; i<mezok.size(); i++) {
					mezok.get(i).setBackground(tiszta);
				}
			}
			allapot1.setText("");
			allapot2.setText("");
		}
		
		public void setAllapot1(String a1) {
			allapot1.setText(a1);
		}
		
		public void setAllapot2(String a2) {
			allapot2.setText(a2);
		}
	}
	
	private List<Hely> helyek;		// termek és a folyosó
	private List<String> emberek;	// kik szabadok éppenséggel
	
	private Epulet environment;
	
	private JPanel panel;
	private JLabel label;
	private JLabel whatsgoingon;
	private JLabel kiszabad;
	
	public EpuletView(GridWorldModel model, String title, int windowSize, Epulet e) {
		super(model, title, windowSize);
			
		environment = e;
		
		setVisible(true);
        repaint();
	}
	
	@Override
    public void initComponents(int width) {
		
		super.initComponents(width);
		
		helyek = new ArrayList<Hely>();
		
		emberek = new ArrayList<String>();
		emberek.add("Bea");
		emberek.add("Izolda");
		emberek.add("Lali");
		emberek.add("Mari");
		emberek.add("Zsombor");
		
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		JPanel pan= new JPanel();
		pan.setLayout(new GridLayout(10,10));
		label = new JLabel("");
		whatsgoingon = new JLabel("Üzenet: ");
		kiszabad = new JLabel("");
		
        panel.add(whatsgoingon, BorderLayout.NORTH);
//        panel.add(kiszabad, BorderLayout.EAST);
        panel.add(label);
        
        // a kis helyeink
        
        Hely folyoso = new Hely("foly.", teremtipus.folyoso);
        Hely ib028 = new Hely("ib028", teremtipus.nagyterem);
        Hely ib027 = new Hely("ib027", teremtipus.kisterem);
        Hely ib026 = new Hely("ib026", teremtipus.kisterem);
        Hely mosdok = new Hely("mosdok", teremtipus.mosdo);
        
        helyek.add(folyoso);
        helyek.add(ib028);
        helyek.add(ib027);
        helyek.add(ib026);  
        helyek.add(mosdok);
        
        for (int j=1; j<=10; j++) {
        	for (int i=1; i<=10; i++) {
        		JPanel p = new JPanel();
//        		p.setSize(12, 12);
        		
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
        
        // elsõ nap elsõ reggelén minden terem széptiszta :3
        for (int i=0; i<helyek.size(); i++) {
        	helyek.get(i).setPiszkos(false);
        }  
    	
    	// adunk a termekhez kukákat
    	ib028.addKuka(13);
    	ib028.addKuka(16);
    	ib027.addKuka(6);
    	ib026.addKuka(6);

        setContentPane(panel);
        
	}

	// TODO ez írja ki a napot és idõt
	public void setIdo(String nap, String ido) {
		label.setText(nap + ". nap " + ido + " óra");
	}
	
	// TODO ez nem tudom, megjelenjen e valahogy, ahogy gondoljátok :)
	public void napVege() {
		whatsgoingon.setText("Üzenet: Nap vége.");
	}

	// TODO ezt sem tudom, kell e :D
	public void hetVege() {
		whatsgoingon.setText("Üzenet: Hét vége.");
	}

	
	// TODO ebéd szünet van... ezt sem tudom, kiírjuk e
	public void ebed() {
		whatsgoingon.setText("Üzenet: Ebédszünet!");
	}

	
	// TODO konferencia van a teremben
	public void konferencia(String terem) {
		if (terem.equals("IB28")) {
			helyek.get(1).setAllapot1("konf");
			helyek.get(1).setAllapot2("erencia");
		} else if (terem.equals("IB27")) {
			helyek.get(2).setAllapot1("konf");
			helyek.get(2).setAllapot2("erencia");
		} else if (terem.equals("IB26")) {
			helyek.get(3).setAllapot1("konf");
			helyek.get(3).setAllapot2("erencia");
		}		
	}

	
	/*
	// TODO teremben óra van, és ennyi és ennyi ember járt itt az utolsó felmosás ill kukaürítés óta (nem feltétlenül egyeznek meg)
	// itt tehetnénk bele randomot, hogy tényleg koszos lett-e, ha igen, akkor színezzük ki a területet,meg a kukákat
	// arra is érdemes lesz figyelni, ami eddig koszos volt, ne váljon random tisztává :D
	// az ágenseknél 200 felett küldünk felmosni, és 300 felett küldünk kukátüríteni
	// szerintem ezzel egyhangúan lehetne, hogy ha a létszám meghaladja ezeket az értékeket, pl 0.7 eséllyel tényleg koszosodjanak be
	public void oraVan(String terem, int letszamFelmosasOta, int letszamKukauritesOta) {
		
	}
*/

	// kiíratni, hogy óra van, ennyi fõvel
	// ez már szépen néz ki!
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
	
	// valakit elküldtek felmosni
	// terem értéke "IB26", "IB27", "IB28" vagy "folyoso"
	// (ezt a függvényt hívja a sima felmosáshoz, és konferencia elõtti muszály felmosáshoz is)
	// (megcsinálhatjuk két külnbözõ függvényben is, ha máshogy szeretnétek a kettõt ábrázolni)
	// megcsinálja, de nem néz ki szépen
	// TODO nézzen ki szépen :(
	public void felmos(String ember, String terem) {
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
				helyek.get(1).setAllapot1("mos:");
				helyek.get(1).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(1).setPiszkos(false);
			} else if (terem.equals("IB27")) {
				helyek.get(2).setAllapot1("mos:");
				helyek.get(2).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(2).setPiszkos(false);
			} else if (terem.equals("IB26")) {
				helyek.get(3).setAllapot1("mos:");
				helyek.get(3).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(3).setPiszkos(false);
			} else if (terem.equals("folyoso")) {
				helyek.get(0).setAllapot1("mos:");
				helyek.get(0).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(0).setPiszkos(false);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	// valakit elküld kukát üríteni
	// terem értéke "IB26", "IB27", "IB28"
	// megcsinálja, de nem néz ki szépen
	// TODO nézzen ki szépen :(
	// TODO meg nézzünk utána mi a bánata neki, néha azt írja tele kukára hogy üres :o
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
				helyek.get(1).setAllapot1("kuka:");
				helyek.get(1).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(1).teleKuka(false);
			} else if (terem.equals("IB27")) {
				helyek.get(2).setAllapot1("kuka:");
				helyek.get(2).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(2).teleKuka(false);
			} else if (terem.equals("IB26")) {
				helyek.get(3).setAllapot1("kuka:");
				helyek.get(3).setAllapot2(nev);
				Thread.sleep(var);
				helyek.get(3).teleKuka(false);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// TODO mosdót tisztít valaki
	// jelenlegi logika szerint mindig elküldünk valakit mosdót tisztítani,
	// kivéve szünetek alatt: páratlan óra 45-kor, és páros óra egészkor
	// szerintem egyszerûség kedvéért randomoljunk, a 6 közül melyik mosdóba menjen takarítani
	// nincs lekódolva, mikor lesz ténylegesen koszos, szerintem a mosdókat ne színezzük
	// (de csak a lustaság mondatja velem, szóval mondjátok, ha szeretnétek)
	public void mosdottisztit(String ember) {
		
	}
	
	// kipirosítani a helyszínt
	// terem értéke "IB26", "IB27", "IB28" vagy "folyoso"
	// megbeszéltek szerint, ha 200nál többen járták már össze a helyet,
	// 0.7 valószínûséggel koszosodik be
	// ezt az Epulet.java-ban lekódoltam, akkor hívja ezt a függvényt, ha már tényleg koszos
	// megcsinálja és még szép is :3
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
	
	// telinek jelölni a kukákat a helyszínen
	// terem értéke "IB26", "IB27", "IB28"
	// kihagyhatjuk a folyosón a kukaürítést? egyenlõre kikommenteztem a kódból
	// ha 300nál többen voltak már a teremben,
	// 0.4 valószínûséggel telik meg
	// ezt az Epulet.java-ban lekódoltam, akkor hívja ezt a függvényt, ha már tényleg teli
	// megcsinálja, de nem tudom itt van-e a hiba :o
	// TODO vadássz hibára!!!!
	public void telekuka(String terem) {
		if (terem.equals("IB28")) {
			helyek.get(1).teleKuka(true);
		} else if (terem.equals("IB27")) {
			helyek.get(2).teleKuka(true);
		} else if (terem.equals("IB26")) {
			helyek.get(3).teleKuka(true);
		}
	}
	
	// TODO ez az ágens megint szabad
	public void szabad(String agName) {
		whatsgoingon.setText("Üzenet: "+agName+" ágens újra szabad.");
	}

	
	
	
	
	
	

}
