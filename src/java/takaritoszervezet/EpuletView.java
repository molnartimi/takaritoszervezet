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
	
	public void setLabel(String res) {
		label.setText("Default Internal Action után változtattak: " + res);
	}

}
