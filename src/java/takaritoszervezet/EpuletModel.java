package takaritoszervezet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import jason.environment.grid.GridWorldModel;

public class EpuletModel extends GridWorldModel{
	
	protected static EpuletModel model = null;
	protected static Map<String,Integer> osszLetszamFelmosashoz = null;
	protected static Map<String,Integer> osszLetszamKukahoz = null;

	
	
	protected EpuletModel(int arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
		//createTermek();
	}
	
	synchronized public static EpuletModel create() {
        if (model == null) {
            model = new EpuletModel(1,1,5);
            osszLetszamFelmosashoz = new HashMap<String,Integer>();
            osszLetszamKukahoz = new HashMap<String,Integer>();
            
            osszLetszamFelmosashoz.put("IB26", 0);
            osszLetszamFelmosashoz.put("IB27", 0);
            osszLetszamFelmosashoz.put("IB28", 0);
            osszLetszamFelmosashoz.put("folyoso", 0);
            
            osszLetszamKukahoz.put("IB26", 0);
            osszLetszamKukahoz.put("IB27", 0);
            osszLetszamKukahoz.put("IB28", 0);
            osszLetszamKukahoz.put("folyoso", 0);
        }
        return model;
    }
	

	public static EpuletModel get() {
        return model;
    }
    
    public static void destroy() {
        model = null;
    }
    
    public static void addHelyszinLatogatas(String helyszin,int letszam){
    	osszLetszamFelmosashoz.replace(helyszin, osszLetszamFelmosashoz.get(helyszin) + letszam);
    	osszLetszamKukahoz.replace(helyszin, osszLetszamKukahoz.get(helyszin) + letszam);
    }
    
    public static int getLetszamFelmosashoz(String helyszin){
    	return osszLetszamFelmosashoz.get(helyszin);
    }
    
    public int getLetszamKukahoz(String helyszin){
    	return osszLetszamKukahoz.get(helyszin);
    }

}
