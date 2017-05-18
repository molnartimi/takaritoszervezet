package takaritoszervezet;
import java.awt.BorderLayout;
import java.awt.Color;
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
		panel.setLayout(new BorderLayout());
        //panel.setLayout((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
		JPanel pan= new JPanel();
		pan.setLayout(new GridLayout(6,6));
		label = new JLabel("");
        
        panel.add(new JLabel("Hello GUI"),BorderLayout.NORTH);
        panel.add(label);
        
        for(int i=0; i<36; i++){
			JPanel p= new JPanel();
			if(i%2==0)
				p.setBackground(Color.WHITE);
			else
				p.setBackground(Color.GRAY);
			JLabel l = new JLabel(Integer.toString(i));
			p.add(l);
			pan.add(p);
		}
        panel.add(pan, BorderLayout.SOUTH);
        setContentPane(panel);
        
        
        
        
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
	// terem értéke "IB26", "IB27", "IB28" vagy "folyoso"
	// (ezt a függvényt hívja a sima felmosáshoz, és konferencia elõtti muszály felmosáshoz is)
	// (megcsinálhatjuk két külnbözõ függvényben is, ha máshogy szeretnétek a kettõt ábrázolni)
	public void felmos(String ember, String terem) {
		
		
	}
	
	// TODO valakit elküld kukát üríteni
	// terem értéke "IB26", "IB27", "IB28" vagy "folyoso"
	public void kukaturit(String ember, String terem) {
		
		
	}

	// TODO mosdót tisztít valaki
	// jelenlegi logika szerint mindig elküldünk valakit mosdót tisztítani,
	// kivéve szünetek alatt: páratlan óra 45-kor, és páros óra egészkor
	// szerintem egyszerûség kedvéért randomoljunk, a 6 közül melyik mosdóba menjen takarítani
	// nincs lekódolva, mikor lesz ténylegesen koszos, szerintem a mosdókat ne színezzük
	// (de csak a lustaság mondatja velem, szóval mondjátok, ha szeretnétek)
	public void mosdottisztit(String ember) {
		
	}
	
	// TODO kipirosítani a helyszínt
	// terem értéke "IB26", "IB27", "IB28" vagy "folyoso"
	// megbeszéltek szerint, ha 200nál többen járták már össze a helyet,
	// 0.7 valószínûséggel koszosodik be
	// ezt az Epulet.java-ban lekódoltam, akkor hívja ezt a függvényt, ha már tényleg koszos
	public void koszoslett(String terem) {
		
	}
	
	// TODO telinek jelölni a kukákat a helyszínen
	// terem értéke "IB26", "IB27", "IB28"
	// kihagyhatjuk a folyosón a kukaürítést? egyenlõre kikommenteztem a kódból
	// ha 300nál többen voltak már a teremben,
	// 0.4 valószínûséggel telik meg
	// ezt az Epulet.java-ban lekódoltam, akkor hívja ezt a függvényt, ha már tényleg teli
	public void telekuka(String terem) {
		
	}
	
	// TODO ez az ágens megint szabad
	public void szabad(String agName) {
		
	}

	
	
	
	
	
	

}
