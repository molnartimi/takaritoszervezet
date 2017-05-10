package takaritoszervezet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Terem {
	private String nev;
	private ArrayList<Ora> orak; // hány órakor hány diák jár oda
	private int felmosasOtaLetszam;
	private int kukaUritesOtaLetszam;
	
	public Terem(String nev, ArrayList<Ora> orak){
		this.nev = nev;
		this.orak = orak;
		felmosasOtaLetszam = 0;
		kukaUritesOtaLetszam = 0;
	}
	
	public Integer vanOra(int nap,double ido){
		int letszam=0;
		for (int i=0;i<orak.size();i++)
			letszam=orak.get(i).vanOra(nap, ido);
		return letszam;
	}
}
