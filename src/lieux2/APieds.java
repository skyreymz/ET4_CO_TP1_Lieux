package lieux2;

public class APieds extends MoyenTransport {

    // Pas vraiment utile. On pourrait en faire un singleton.
    private APieds() { }
    
    private static APieds INSTANCE = new APieds();
    
    public static APieds getInstance() {
    	return INSTANCE;
    }

    public String toString() { return "A Pieds"; }

    public boolean estPossible(Lieu l1, Lieu l2, Heure dep) {
    	return l1.estVoisin(l2);
    }

    public Heure attente(Lieu l1, Lieu l2, Heure dep){
    	return new Heure(); // Pas d'attente lorsqu'on est à pieds
    }

    // duree du voyage de l1 a l2, hors temps d'attente, a l'heure dep selon
    // ce moyen de transport
    public Heure duree(Lieu l1, Lieu l2, Heure dep) throws ErreurTrajet {
    	return l1.distance(l2);
    }
}
