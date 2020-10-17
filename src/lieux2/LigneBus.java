package lieux2;
import java.util.ArrayList;

public class LigneBus {
    protected String nomLigne;
    protected ArrayList<Arret> sesArrets;
    protected Heure[] sesDeparts; // horaires de départ du bus au premier arrêt de la ligne
    protected Heure[] sesTemps; // temps entre les différents arrêts de bus

    private LigneBus(String nom) { nomLigne = nom; }
    
    private static LigneBus INSTANCE1 = new LigneBus("Ligne 1");
    
    private static LigneBus INSTANCE2 = new LigneBus("Ligne 2");
    
    public static LigneBus getInstance(String nom) {
    	switch (nom) {
    	case "Ligne 1": return INSTANCE1;
    	case "Ligne 2": return INSTANCE2;
    	default:return INSTANCE1;
    	}
    }

    public void addArrets(Arret[] lesArrets) {
        sesArrets = new ArrayList<Arret>();
        for(Arret a : lesArrets) {
            a.ajoutLigne(this);
            sesArrets.add(a);
        }
    }

    public String nom() { return nomLigne; }

    /* pour simplifier on suppose que les durees de deplacement sont
     * independantes de l'heure. Pour definir les horaires il suffit de donner
     * les heures de depart ainsi que les durees entre 2 arrets successifs.
     * La dimension du tableau des horaires doit etre egale au nombre d'arrets
     * de la ligne moins 1.
     *
     * Attention: une ligne de bus est orientee. Les horaires sont donnes dans
     * le sens de parcours. Si on veut aussi pouvoir parcourir une ligne dans
     * l'autre sens, il faut creer une deuxieme ligne !
     * On suppose enfin que les lignes ne sont pas circulaires,
     */
    public void ajoutHoraires(Heure[] horaire, Heure[] hdepart)
        throws ErreurTrajet {
        if (horaire.length != sesArrets.size() - 1) {
            throw new ErreurTrajet("Horaire mal formatte");
        }
        sesDeparts = hdepart;
        sesTemps = horaire;
    }

    public boolean estPossible(Arret a1, Arret a2, Heure dep){
    	int indexDep = sesArrets.indexOf(a1);
	    int indexArr = sesArrets.indexOf(a2);
	    if ((indexDep != -1) && (indexArr != -1) && (indexDep < indexArr)) {
	    	Heure heureDernierPassage = new Heure();
	    	try {
	    		heureDernierPassage = heureDernierPassage.add(sesDeparts[sesDeparts.length-1]); // heure du dernier départ (au premier arrêt de la ligne) ; je suppose que sesDeparts est trié dans l'ordre croisant
		    	for (int i = 0 ; i < indexArr ; i++) {
		    		heureDernierPassage = heureDernierPassage.add(sesTemps[i]); // on ajoute les temps entre le départ et notre arrêt
		    	}
	    	} catch (ErreurHeure e) {
	       		return false;
	       	}
		   	if (heureDernierPassage.compareTo(dep) == -1) { // comparaison du dernier horaire de passage à notre arrêt par rapport à dep
		   		return false;
		   	} else {
		   		return true;
		   	}
	    } else {
	    	return false;
	    }
    }

    // On suppose que la duree de transport entre deux arrets ne depend pas
    // de l'heure et qu'on n'arrive jamais le lendemain du jour de depart.
    public Heure dureeEnBus(Arret a1, Arret a2) throws ErreurTrajet {
    	int indexDep = sesArrets.indexOf(a1);
	    int indexArr = sesArrets.indexOf(a2);
	    if ((indexDep != -1) && (indexArr != -1) && (indexDep < indexArr)) {
	    	Heure duree = new Heure();
	    	for (int i = indexDep ; i < indexArr ; i++) {
	    		try {
					duree = duree.add(sesTemps[i]);
				} catch (ErreurHeure e) {
					throw new ErreurTrajet(e.getMessage());
				}
	    	}
	    	return duree;
	    } else {
	    	throw new ErreurTrajet();
	    }
    }

    public Heure attente(Arret a, Heure h) throws ErreurTrajet {
    	int indexDep = sesArrets.indexOf(a);
    	if (indexDep == -1) {
    		throw new ErreurTrajet();
    	}
    	Heure heurePassage = new Heure();
    	try {
	    	for (int i = 0 ; i < indexDep ; i++) {
	    		heurePassage = heurePassage.add(sesTemps[i]); // on ajoute les temps entre le départ et notre arrêt
	    	}
	    	for (Heure heureDepart : sesDeparts) {
	    		heurePassage = heurePassage.add(heureDepart); // heure de chaque départ (au premier arrêt de la ligne)
			    if (heurePassage.compareTo(h) >= 0) {
			    	return h.delaiAvant(heurePassage);
			    }
			}
	    	throw new ErreurTrajet("Plus de passage de bus aujourd'hui");
    	} catch (ErreurHeure e) {
			throw new ErreurTrajet(e.getMessage());
    	}
    }
}
