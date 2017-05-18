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
	 * Konstruktor. L�trehoz egy helyet, inicializ�lja a v�ltoz�it, be�ll�tja a sz�neket 
	 * @param n		- hely neve
	 * @param t		- hely t�pusa
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
	 * Hozz�rendel egy panelt egy helyhez. K�l�n kezeli a kis termeket,
	 * a nagy termet, a mosd�kat �s a folyos�t.
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
	 * Felvesz egy kuk�t adott index� panelre, megfelel� sz�nez�ssel.
	 * @param index		- panel indexe
	 */
	public void addKuka(int index) {
		JLabel k = new JLabel("o");
		k.setForeground(new Color(77,117,32));
		mezok.get(index).add(k);
		kukak.add(k);
	}
	
	/**
	 * Megjelen�t vagy elt�vol�t egy �genst adott panelr�l.
	 * @param erkezik	- true -> �rkezik
	 * 					- false-> t�vozik
	 */
	public void ittEgyAgens(boolean erkezik) {
		if (erkezik) {
			JLabel a = new JLabel("�");
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
	 * Kuka kirajzol�s�t friss�ti.
	 * @param televane	- true -> megtelt, legyen piros
	 * 					- false-> ki�r�lt, legyen z�ld
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
	 * Terem sz�nez�s��rt felel�s.
	 * @param piszkose	- true -> piszkos, legyen piros
	 * 					- false-> tiszta,  legyen z�ld
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
	 * Els� �llapotjelz� c�mke.
	 * @param a1	- �ra:, mos:, kuka: �rt�keket vesz fel
	 */
	public void setAllapot1(String a1) {
		allapot1.setText(a1);
	}
	
	/**
	 * M�sodik �llapotjelz� c�mke.
	 * @param a1	- l�tsz�m, �gensn�v, �res string �rt�keket vesz fel
	 */
	public void setAllapot2(String a2) {
		allapot2.setText(a2);
	}
}
