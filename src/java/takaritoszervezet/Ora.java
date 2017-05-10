package takaritoszervezet;

import java.util.HashMap;
import java.util.Map;

public class Ora {
	private int nap;
	private double ido;
	private int letszam;
	
	public Ora(int nap, double ido, int letszam){
		this.nap = nap;
		this.ido = ido;
		this.letszam = letszam;
	}
	
	// ha van ott óra: létszámot adja vissza, amúgy 0-t
	public int vanOra(int n,double i){
		if (nap == n && ido == i)
			return letszam;
		return 0;
	}
}
