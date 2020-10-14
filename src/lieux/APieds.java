package lieux;

public class APieds extends MoyenTransport {

    // Pas vraiment utile. On pourrait en faire un singleton.
    public APieds() { }

    public String toString() { return "A Pieds"; }

    public boolean estPossible(Lieu l1, Lieu l2, Heure dep) {
    	return l1.estVoisin(l2);
    }

    public Heure attente(Lieu l1, Lieu l2, Heure dep){
    	return new Heure();
    }

    // duree du voyage de l1 a l2, hors temps d'attente, a l'heure dep selon
    // ce moyen de transport
    public Heure duree(Lieu l1, Lieu l2, Heure dep) throws ErreurTrajet {
        throw new UnsupportedOperationException();
    }


}
