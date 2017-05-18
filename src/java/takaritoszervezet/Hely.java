package takaritoszervezet;

import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JPanel;

import takaritoszervezet.EpuletView.teremtipus;

public class Hely {
	List<JPanel> mezok;
	JLabel nev = new JLabel("");
	JLabel allapot1 = new JLabel("");
	JLabel allapot2 = new JLabel("");
	List<JLabel> kukak;
	List<JLabel> agensek;
	teremtipus tipus;
	Color piszkos;
	Color tiszta;
	
	/**
	 * Konstruktor. Létrehoz egy helyet, inicializálja a változóit, beállítja a színeket 
	 * @param n		- hely neve
	 * @param t		- hely típusa
	 */
	public Hely (String n, teremtipus t){
		mezok = new ArrayList<JPanel>();
		kukak = new ArrayList<JLabel>();
		agensek = new ArrayList<JLabel>();
		
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
	
	/**
	 * Hozzárendel egy panelt egy helyhez. Külön kezeli a kis termeket,
	 * a nagy termet, a mosdókat és a folyosót.
	 * @param p		- panel
	 */
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
	
	/**
	 * Felvesz egy kukát adott indexû panelre, megfelelõ színezéssel.
	 * @param index		- panel indexe
	 */
	public void addKuka(int index) {
		JLabel k = new JLabel("o");
		k.setForeground(new Color(77,117,32));
		mezok.get(index).add(k);
		kukak.add(k);
	}
	
	/**
	 * Megjelenít vagy eltávolít egy ágenst adott panelrõl.
	 * @param erkezik	- true -> érkezik
	 * 					- false-> távozik
	 */
	public void ittEgyAgens(boolean erkezik) {
		if (erkezik) {
			JLabel a = new JLabel("•");
			switch (tipus) {
			case folyoso:
				mezok.get(51+agensek.size()).add(a);
				break;
			case nagyterem:
				mezok.get(15+agensek.size()).add(a);
				break;
			case kisterem:
				mezok.get(7+agensek.size()).add(a);
				break;
			case mosdo:
				Random r = new Random();
				mezok.get(r.nextInt(6)+agensek.size()).add(a);
				break;
			}
			agensek.add(a);
		} else {				
			Container parent = agensek.get(agensek.size()-1).getParent();
			parent.remove(agensek.get(agensek.size()-1));
			parent.validate();
			parent.repaint();
			agensek.remove(agensek.size()-1);
		}			
	}
	
	/**
	 * Kuka kirajzolását frissíti.
	 * @param televane	- true -> megtelt, legyen piros
	 * 					- false-> kiürült, legyen zöld
	 */
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
	
	/**
	 * Terem színezéséért felelõs.
	 * @param piszkose	- true -> piszkos, legyen piros
	 * 					- false-> tiszta,  legyen zöld
	 */
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
	
	/**
	 * Elsõ állapotjelzõ címke.
	 * @param a1	- óra:, mos:, kuka: értékeket vesz fel
	 */
	public void setAllapot1(String a1) {
		allapot1.setText(a1);
	}
	
	/**
	 * Második állapotjelzõ címke.
	 * @param a1	- létszám, ágensnév, üres string értékeket vesz fel
	 */
	public void setAllapot2(String a2) {
		allapot2.setText(a2);
	}
}
