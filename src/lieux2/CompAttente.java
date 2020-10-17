package lieux2;

public class CompAttente implements Comparateur {
    public int compare(Trajet t1, Trajet t2) {
    	try {
			return t1.attente().compareTo(t2.attente());
		} catch (ErreurTrajet e) {
			return 0;
		}
    }
}
