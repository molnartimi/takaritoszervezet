package takaritoszervezet;

import jason.environment.grid.GridWorldModel;

public class EpuletModel extends GridWorldModel{
	
	protected static EpuletModel model = null;

	protected EpuletModel(int arg0, int arg1, int arg2) {
		super(arg0, arg1, arg2);
	}
	
	synchronized public static EpuletModel create() {
        if (model == null) {
            model = new EpuletModel(1,1,5);
        }
        return model;
    }
	

	public static EpuletModel get() {
        return model;
    }
    
    public static void destroy() {
        model = null;
    }
    
}
