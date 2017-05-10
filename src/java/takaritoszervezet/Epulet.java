package takaritoszervezet;
// Environment code for project takaritoszervezet

import jason.asSyntax.*;
import jason.asSyntax.parser.ParseException;
import jason.environment.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.*;

public class Epulet extends Environment {

    private Logger logger = Logger.getLogger("takaritoszervezet."+Epulet.class.getName());

    EpuletView view;
	EpuletModel em;
	
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
    	EpuletModel.create();
		em = EpuletModel.get();
		
		initHiedelmek();
		
		view = new EpuletView(em, "Földszint", 350, this);
    }

	// Hiedelem: oravan(melyiknap,melyikterem,hanyorakor,hanyember)
    private void initHiedelmek() {
    	addPercept("manager",Literal.parseLiteral(
    			"szabad[source(tak_Mari),source(tak_Bea),source(tak_Lali),source(tak_Izolda),source(tak_Zsombor)]"));
    	addPercept("manager",Literal.parseLiteral("ebedszunet(13.25)"));
    	addPercept("manager",Literal.parseLiteral("esosido(2)"));
    	addPercept("manager",Literal.parseLiteral("esosido(3)"));
    	
    	ArrayList<Terem> termek = em.getTermek();
    	for (Terem t: termek){
    		
    	}
    	
	}

	@Override
    public boolean executeAction(String agName, Structure action) {
    	if(action.getFunctor().equals("printResult")){
			String command=action.toString();
			String res=command.substring(command.indexOf("(")+1,command.indexOf(")"));
			view.setLabel(res);
			return true;
		}

		return false;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
}
