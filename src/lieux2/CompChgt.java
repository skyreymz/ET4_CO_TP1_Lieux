package lieux2;

public class CompChgt implements Comparateur {
    public int compare(Trajet t1, Trajet t2) {
    	try {
			if (t1.nbChgt() < t2.nbChgt()) {
				return -1;
			} else if (t1.nbChgt() > t2.nbChgt()) {
				return 1;
			} else {
				return 0;
			}
		} catch (ErreurTrajet e) {
			return 0;
		}
    }
}
