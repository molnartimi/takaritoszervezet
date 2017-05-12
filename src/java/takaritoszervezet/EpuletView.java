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
	public void felmos(String ember, String terem) {
		
		
	}

	
	
	
	
	
	

}
