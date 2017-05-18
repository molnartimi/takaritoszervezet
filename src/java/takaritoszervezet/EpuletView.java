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
	
	private List<Hely> helyek;		// termek �s a folyos�
	private List<String> emberek;	// kik szabadok �ppens�ggel
	
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
		whatsgoingon = new JLabel("�zenet: ");
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
        
        // els� nap els� reggel�n minden terem sz�ptiszta :3
        for (int i=0; i<helyek.size(); i++) {
        	helyek.get(i).setPiszkos(false);
        }  
    	
    	// adunk a termekhez kuk�kat
    	ib028.addKuka(13);
    	ib028.addKuka(16);
    	ib027.addKuka(6);
    	ib026.addKuka(6);

        setContentPane(panel);
        
	}

	// TODO ez �rja ki a napot �s id�t
	public void setIdo(String nap, String ido) {
		label.setText(nap + ". nap " + ido + " �ra");
	}
	
	// TODO ez nem tudom, megjelenjen e valahogy, ahogy gondolj�tok :)
	public void napVege() {
		whatsgoingon.setText("�zenet: Nap v�ge.");
	}

	// TODO ezt sem tudom, kell e :D
	public void hetVege() {
		whatsgoingon.setText("�zenet: H�t v�ge.");
	}

	
	// TODO eb�d sz�net van... ezt sem tudom, ki�rjuk e
	public void ebed() {
		whatsgoingon.setText("�zenet: Eb�dsz�net!");
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
	// TODO teremben �ra van, �s ennyi �s ennyi ember j�rt itt az utols� felmos�s ill kuka�r�t�s �ta (nem felt�tlen�l egyeznek meg)
	// itt tehetn�nk bele randomot, hogy t�nyleg koszos lett-e, ha igen, akkor sz�nezz�k ki a ter�letet,meg a kuk�kat
	// arra is �rdemes lesz figyelni, ami eddig koszos volt, ne v�ljon random tiszt�v� :D
	// az �gensekn�l 200 felett k�ld�nk felmosni, �s 300 felett k�ld�nk kuk�t�r�teni
	// szerintem ezzel egyhang�an lehetne, hogy ha a l�tsz�m meghaladja ezeket az �rt�keket, pl 0.7 es�llyel t�nyleg koszosodjanak be
	public void oraVan(String terem, int letszamFelmosasOta, int letszamKukauritesOta) {
		
	}
*/

	// ki�ratni, hogy �ra van, ennyi f�vel
	// ez m�r sz�pen n�z ki!
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
	
	// valakit elk�ldtek felmosni
	// terem �rt�ke "IB26", "IB27", "IB28" vagy "folyoso"
	// (ezt a f�ggv�nyt h�vja a sima felmos�shoz, �s konferencia el�tti musz�ly felmos�shoz is)
	// (megcsin�lhatjuk k�t k�lnb�z� f�ggv�nyben is, ha m�shogy szeretn�tek a kett�t �br�zolni)
	// megcsin�lja, de nem n�z ki sz�pen
	// TODO n�zzen ki sz�pen :(
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
	
	// valakit elk�ld kuk�t �r�teni
	// terem �rt�ke "IB26", "IB27", "IB28"
	// megcsin�lja, de nem n�z ki sz�pen
	// TODO n�zzen ki sz�pen :(
	// TODO meg n�zz�nk ut�na mi a b�nata neki, n�ha azt �rja tele kuk�ra hogy �res :o
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

	// TODO mosd�t tiszt�t valaki
	// jelenlegi logika szerint mindig elk�ld�nk valakit mosd�t tiszt�tani,
	// kiv�ve sz�netek alatt: p�ratlan �ra 45-kor, �s p�ros �ra eg�szkor
	// szerintem egyszer�s�g kedv��rt randomoljunk, a 6 k�z�l melyik mosd�ba menjen takar�tani
	// nincs lek�dolva, mikor lesz t�nylegesen koszos, szerintem a mosd�kat ne sz�nezz�k
	// (de csak a lustas�g mondatja velem, sz�val mondj�tok, ha szeretn�tek)
	public void mosdottisztit(String ember) {
		
	}
	
	// kipiros�tani a helysz�nt
	// terem �rt�ke "IB26", "IB27", "IB28" vagy "folyoso"
	// megbesz�ltek szerint, ha 200n�l t�bben j�rt�k m�r �ssze a helyet,
	// 0.7 val�sz�n�s�ggel koszosodik be
	// ezt az Epulet.java-ban lek�doltam, akkor h�vja ezt a f�ggv�nyt, ha m�r t�nyleg koszos
	// megcsin�lja �s m�g sz�p is :3
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
	
	// telinek jel�lni a kuk�kat a helysz�nen
	// terem �rt�ke "IB26", "IB27", "IB28"
	// kihagyhatjuk a folyos�n a kuka�r�t�st? egyenl�re kikommenteztem a k�db�l
	// ha 300n�l t�bben voltak m�r a teremben,
	// 0.4 val�sz�n�s�ggel telik meg
	// ezt az Epulet.java-ban lek�doltam, akkor h�vja ezt a f�ggv�nyt, ha m�r t�nyleg teli
	// megcsin�lja, de nem tudom itt van-e a hiba :o
	// TODO vad�ssz hib�ra!!!!
	public void telekuka(String terem) {
		if (terem.equals("IB28")) {
			helyek.get(1).teleKuka(true);
		} else if (terem.equals("IB27")) {
			helyek.get(2).teleKuka(true);
		} else if (terem.equals("IB26")) {
			helyek.get(3).teleKuka(true);
		}
	}
	
	// TODO ez az �gens megint szabad
	public void szabad(String agName) {
		whatsgoingon.setText("�zenet: "+agName+" �gens �jra szabad.");
	}

	
	
	
	
	
	

}
