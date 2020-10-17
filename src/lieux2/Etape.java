package lieux2;

public class Etape {
    private Lieu dep, arr;
    private MoyenTransport moyen;
    private Heure hdep;

    /* Pour l'instant on autorise la creation d'une etape meme si
     * elle n'utilise pas un moyen de transport possible entre ces
     * lieux a cette heure.
     */
    public Etape(Lieu d, Lieu a, MoyenTransport m, Heure h) {
    	dep = d;
    	arr = a;
    	moyen = m;
    	hdep = h;
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
    	return moyen.estPossible(dep, arr, hdep);
    }

    public Heure duree() throws ErreurTrajet {
        return moyen.duree(dep, arr, hdep);
    }

    public Heure attente()  throws ErreurTrajet {
    	return moyen.attente(dep, arr, hdep);
    }
}
