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

	// TODO ez írja ki a napot és idõt
	public void setIdo(String nap, String ido) {
		label.setText(nap + ". nap " + ido + " óra");
	}
	
	// TODO ez nem tudom, megjelenjen e valahogy, ahogy gondoljátok :)
	public void napVege() {
		
		
	}

	// TODO ezt sem tudom, kell e :D
	public void hetVege() {
		
		
	}

	
	// TODO ebéd szünet van... ezt sem tudom, kiírjuk e
	public void ebed() {
		
		
	}

	
	// TODO konferencia van a teremben
	public void konferencia(String terem) {
	
		
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

	// TODO kiíratni, hogy óra van, ennyi fõvel
	public void oraVan(String terem, int letszam){
		
	}
	
	// TODO valakit elküldtek felmosni
	public void felmos(String ember, String terem) {
		
		
	}

	
	
	
	
	
	

}
