package takaritoszervezet;
import java.util.ArrayList;

import jason.environment.grid.GridWorldModel;

public class EpuletModel extends GridWorldModel{
	
	protected static EpuletModel model = null;
	private static ArrayList<Terem> termek;
	
	protected EpuletModel(int arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
		//createTermek();
	}
	
	synchronized public static EpuletModel create() {
        if (model == null) {
            model = new EpuletModel(1,1,5);
        }
        return model;
    }
	
	/*private static void createTermek() {
		ArrayList<Ora> ora1 = new ArrayList<Ora>();
		ora1.add(new Ora(1,8.25,300));
		ora1.add(new Ora(1,10.25,100));
		ora1.add(new Ora(2,8.25,400));
		ora1.add(new Ora(2,10.25,400));
		ora1.add(new Ora(2,14.25,600));
		ora1.add(new Ora(3,8.25,285));
		termek.add(new Terem("IB28",ora1));
		
		ArrayList<Ora> ora2 = new ArrayList<Ora>();
		ora1.add(new Ora(1,10.25,20));
		ora1.add(new Ora(1,14.25,30));
		ora1.add(new Ora(2,10.25,150));
		ora1.add(new Ora(3,10.25,85));
		termek.add(new Terem("IB27",ora2));
		
		ArrayList<Ora> ora3 = new ArrayList<Ora>();
		ora1.add(new Ora(1,12.25,150));
		ora1.add(new Ora(2,8.25,15));
		ora1.add(new Ora(2,12.25,60));
		ora1.add(new Ora(3,10.25,30));
		termek.add(new Terem("IB26",ora3));
	}*/

	public static EpuletModel get() {
        return model;
    }
    
    public static void destroy() {
        model = null;
    }

	public static ArrayList<Terem> getTermek() {
		return termek;
	}

	/*public void addPercepts() {
		for(Terem t: termek){
			t.addPercepts();
		}
	}*/
}
