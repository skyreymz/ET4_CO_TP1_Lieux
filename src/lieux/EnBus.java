package lieux;

public class EnBus extends MoyenTransport {
    private LigneBus saLigne;

    public EnBus(LigneBus l) { saLigne = l; }

    public String toString() {
        return "Ligne Bus [" + saLigne.nom() + "]";
    }

    public Heure attente(Lieu l1, Lieu l2, Heure dep) throws ErreurTrajet {
        throw new UnsupportedOperationException();
    }

    public Heure duree(Lieu l1, Lieu l2, Heure dep) throws ErreurTrajet {
        throw new UnsupportedOperationException();
    }

    public boolean estPossible(Lieu l1, Lieu l2, Heure dep) {
    	try {
    		Arret a1 = (Arret) l1;
    		Arret a2 = (Arret) l2;
    		return saLigne.estPossible(a1, a2, dep);
    	} catch (ClassCastException e) {
    		return false;
    	}
    }
}
