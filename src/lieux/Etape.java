package lieux;

public class Etape {
    private Lieu dep, arr;
    private MoyenTransport moyen;
    private Heure hdep;

    /* Pour l'instant on autorise la creation d'une etape meme si
     * elle n'utilise pas un moyen de transport possible entre ces
     * lieux a cette heure.
     */
    public Etape(Lieu d, Lieu a, MoyenTransport m, Heure h) {
    }

    public void liste() {
	/* manque encore l'horaire de depart + le delai d'attente */
	System.out.println("De " + dep.nom() + " a " + arr.nom() + ": "
			   + moyen.toString() + " [depart: " + hdep + "]");
    }

    public MoyenTransport moyen() { return moyen; }

    public Lieu depart() { return dep; }

    public Lieu arrivee() { return arr; }

    public Heure hDepart() { return hdep; }

    public Heure hArrivee() throws ErreurTrajet {
    	return moyen.hArrivee(dep, arr, hdep);
    }

    public boolean estPossible() {
        return this.moyen.estPossible(this.dep, this.arr, this.hdep);
    }

    public Heure duree() throws ErreurTrajet {
        throw new UnsupportedOperationException();
    }

    public Heure attente()  throws ErreurTrajet {
        throw new UnsupportedOperationException();
    }
}
