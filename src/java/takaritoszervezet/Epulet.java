package takaritoszervezet;
// Environment code for project takaritoszervezet

import java.util.Random;
import java.util.logging.Logger;

import jason.asSyntax.Structure;
import jason.environment.Environment;

public class Epulet extends Environment {

    private Logger logger = Logger.getLogger("takaritoszervezet."+Epulet.class.getName());

    EpuletView view;
	EpuletModel em;
	
	int nap;
	
    /** Called before the MAS execution with the args informed in .mas2j */
    @Override
    public void init(String[] args) {
    	EpuletModel.create();
		em = EpuletModel.get();
		
		nap = 1;
		
		view = new EpuletView(em, "Földszint", 350, this);
    }

	@Override
    public boolean executeAction(String agName, Structure action) {
		Random r = new Random();
		
		String command = action.toString();
		String functor = action.getFunctor();
		if(functor.equals("idoKiiras")){
			String args = command.substring(command.indexOf("(")+1,command.indexOf(")"));
			String nap = args.substring(0, args.indexOf(","));
			String ido = args.substring(args.indexOf(",")+1);
			view.setIdo(nap,ido);
			return true;
		}
		if(functor.equals("napvege")){
			view.napVege();
			return true;
		}
		if(functor.equals("hetvege")){
			view.hetVege();
			return true;
		}
		if(functor.equals("ebed")){
			view.ebed();
			return true;
		}
		if(functor.equals("konferencia")){
			String args = command.substring(command.indexOf("(")+1,command.indexOf(")"));
			args=args.substring(1,args.length()-1);
			view.konferencia(args);
			return true;
		}
		if(functor.equals("oravan")){
			String args = command.substring(command.indexOf("(")+1,command.indexOf(")"));
			String terem = args.substring(0, args.indexOf(","));
			terem = terem.substring(1,terem.length()-1); 	// idézõjelek eltávolítása
			String letszam = args.substring(args.indexOf(",")+1);
			view.oraVan(terem, Integer.parseInt(letszam));
			return true;
		}
		if(functor.equals("felmosat")){
			String args = command.substring(command.indexOf("(")+1,command.indexOf(")"));
			String ember = args.substring(0, args.indexOf(","));
			String terem = args.substring(args.indexOf(",")+1);
			terem = terem.substring(1,terem.length()-1);
			view.felmos(ember,terem);
			return true;
		}
		if(functor.equals("kukaturit")){
			String args = command.substring(command.indexOf("(")+1,command.indexOf(")"));
			String ember = args.substring(0, args.indexOf(","));
			String terem = args.substring(args.indexOf(",")+1);
			terem = terem.substring(1,terem.length()-1);
			view.kukaturit(ember,terem);
			return true;
		}
		if(functor.equals("mosdottisztit")){
			String args = command.substring(command.indexOf("(")+1,command.indexOf(")"));
			view.mosdottisztit(args);
			return true;
		}
		if(functor.equals("koszosodik")){
			String args = command.substring(command.indexOf("(")+1,command.indexOf(")"));
			String terem = args.substring(0, args.indexOf(","));
			terem = terem.substring(1,terem.length()-1); 	// idézõjelek eltávolítása
			String letszam = args.substring(args.indexOf(",")+1);
			
			if(Integer.parseInt(letszam) >= 200){
				if(r.nextDouble()>=0.3)
					view.koszoslett(terem);
			}
			view.oraVan(terem, Integer.parseInt(letszam));
			return true;
		}
		if(functor.equals("kukatelik")){
			String args = command.substring(command.indexOf("(")+1,command.indexOf(")"));
			String terem = args.substring(0, args.indexOf(","));
			terem = terem.substring(1,terem.length()-1); 	// idézõjelek eltávolítása
			String letszam = args.substring(args.indexOf(",")+1);
			
			if(Integer.parseInt(letszam) >= 300){
				if(r.nextDouble()>=0.6)
					view.telekuka(terem);
			}
			view.oraVan(terem, Integer.parseInt(letszam));
			return true;
		}
		if(functor.equals("szabad")){
			view.szabad(agName);
			return true;
		}

		return false;
    }

    /** Called before the end of MAS execution */
    @Override
    public void stop() {
        super.stop();
    }
    
    private String getStringFromCommand(String args, int index){
    	String result = args;
    	if(index==1){
    		result = result.substring(0, args.indexOf(","));
    		result = result.substring(1,result.length()-1);
    	}
    		return result;
    }
}
