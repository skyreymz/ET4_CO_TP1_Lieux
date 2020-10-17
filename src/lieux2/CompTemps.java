package lieux2;

public class CompTemps implements Comparateur {
    public int compare(Trajet t1, Trajet t2) {
    	try {
			return t1.duree().compareTo(t2.duree());
		} catch (ErreurTrajet e) {
			return 0;
		}
    }
}
