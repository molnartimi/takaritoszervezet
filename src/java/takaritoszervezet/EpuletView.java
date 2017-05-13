package takaritoszervezet;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import jason.environment.grid.GridWorldModel;
import jason.environment.grid.GridWorldView;

public class EpuletView extends GridWorldView {
	
	private Epulet environment;
	
	private JPanel panel;
	private JLabel label;
	
	public EpuletView(GridWorldModel model, String title, int windowSize, Epulet e) {
		super(model, title, windowSize);
		
		environment = e;
		
		setVisible(true);
        repaint();
	}
	
	@Override
    public void initComponents(int width) {
		super.initComponents(width);
		panel = new JPanel();
        //panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
		panel.setLayout(new GridLayout(6,6));
        setContentPane(panel);
        
        label = new JLabel("");
        
        panel.add(new JLabel("Hello GUI"));
        panel.add(label);
        
        
	}

	// TODO ez �rja ki a napot �s id�t
	public void setIdo(String nap, String ido) {
		label.setText(nap + ". nap " + ido + " �ra");
	}
	
	// TODO ez nem tudom, megjelenjen e valahogy, ahogy gondolj�tok :)
	public void napVege() {
		
		
	}

	// TODO ezt sem tudom, kell e :D
	public void hetVege() {
		
		
	}

	
	// TODO eb�d sz�net van... ezt sem tudom, ki�rjuk e
	public void ebed() {
		
		
	}

	
	// TODO konferencia van a teremben
	public void konferencia(String terem) {
	
		
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

	// TODO ki�ratni, hogy �ra van, ennyi f�vel
	public void oraVan(String terem, int letszam){
		
	}
	
	// TODO valakit elk�ldtek felmosni
	// terem �rt�ke "IB26", "IB27", "IB28" vagy "folyoso"
	// (ezt a f�ggv�nyt h�vja a sima felmos�shoz, �s konferencia el�tti musz�ly felmos�shoz is)
	// (megcsin�lhatjuk k�t k�lnb�z� f�ggv�nyben is, ha m�shogy szeretn�tek a kett�t �br�zolni)
	public void felmos(String ember, String terem) {
		
		
	}
	
	// TODO valakit elk�ld kuk�t �r�teni
	// terem �rt�ke "IB26", "IB27", "IB28" vagy "folyoso"
	public void kukaturit(String ember, String terem) {
		
		
	}

	// TODO mosd�t tiszt�t valaki
	// jelenlegi logika szerint mindig elk�ld�nk valakit mosd�t tiszt�tani,
	// kiv�ve sz�netek alatt: p�ratlan �ra 45-kor, �s p�ros �ra eg�szkor
	// szerintem egyszer�s�g kedv��rt randomoljunk, a 6 k�z�l melyik mosd�ba menjen takar�tani
	// nincs lek�dolva, mikor lesz t�nylegesen koszos, szerintem a mosd�kat ne sz�nezz�k
	// (de csak a lustas�g mondatja velem, sz�val mondj�tok, ha szeretn�tek)
	public void mosdottisztit(String ember) {
		
	}
	
	// TODO kipiros�tani a helysz�nt
	// terem �rt�ke "IB26", "IB27", "IB28" vagy "folyoso"
	// megbesz�ltek szerint, ha 200n�l t�bben j�rt�k m�r �ssze a helyet,
	// 0.7 val�sz�n�s�ggel koszosodik be
	// ezt az Epulet.java-ban lek�doltam, akkor h�vja ezt a f�ggv�nyt, ha m�r t�nyleg koszos
	public void koszoslett(String terem) {
		
	}
	
	// TODO telinek jel�lni a kuk�kat a helysz�nen
	// terem �rt�ke "IB26", "IB27", "IB28"
	// kihagyhatjuk a folyos�n a kuka�r�t�st? egyenl�re kikommenteztem a k�db�l
	// ha 300n�l t�bben voltak m�r a teremben,
	// 0.4 val�sz�n�s�ggel telik meg
	// ezt az Epulet.java-ban lek�doltam, akkor h�vja ezt a f�ggv�nyt, ha m�r t�nyleg teli
	public void telekuka(String terem) {
		
	}
	
	// TODO ez az �gens megint szabad
	public void szabad(String agName) {
		
	}

	
	
	
	
	
	

}
