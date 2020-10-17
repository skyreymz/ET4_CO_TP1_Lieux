package lieux2;

public class EnBus extends MoyenTransport {
    private LigneBus saLigne;

    private EnBus(LigneBus l) { saLigne = l; }
    
    private static EnBus INSTANCE1 = new EnBus(LigneBus.getInstance("Ligne 1"));
    
    private static EnBus INSTANCE2 = new EnBus(LigneBus.getInstance("Ligne 2"));
    
    public static EnBus getInstance(LigneBus ligne) {
    	if (ligne.equals(LigneBus.getInstance("Ligne 1"))) {
    		return INSTANCE1;
    	} else if (ligne.equals(LigneBus.getInstance("Ligne 2"))) {
    		return INSTANCE2;
    	} else {
    		return INSTANCE1;
    	}
    }
    
    public String toString() {
        return "Ligne Bus [" + saLigne.nom() + "]";
    }

    public Heure attente(Lieu l1, Lieu l2, Heure dep) throws ErreurTrajet {
    	try {
    		Arret a1 = (Arret) l1;
    		Arret a2 = (Arret) l2;
    		return saLigne.attente(a1, dep);
    	} catch (ClassCastException e) {
    		throw new ErreurTrajet();
    	}
    }

    public Heure duree(Lieu l1, Lieu l2, Heure dep) throws ErreurTrajet {
    	try {
    		Arret a1 = (Arret) l1;
    		Arret a2 = (Arret) l2;
    		return saLigne.dureeEnBus(a1, a2);
    	} catch (ClassCastException e) {
    		throw new ErreurTrajet();
    	}
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
