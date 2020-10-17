package lieux2;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;

public class Trajet {
    private String nom;
    private Lieu depart;
    private Lieu arrivee;
    private Heure dateDepart;
    private ArrayList<Etape> sesEtapes;

    public Trajet(String n, Lieu dep, Lieu arr, Heure d, ArrayList<Etape> etapes) {
        nom = n;
        depart = dep;
        arrivee = arr;
        dateDepart = d;
        sesEtapes = etapes;
    }

    public String nom() { return nom; }

    public Lieu depart() { return depart; }

    public Lieu arrivee() { return arrivee; }

    public void liste() {
        System.out.println("Trajet " + nom + " de " + depart.nom() + " a " 
                           + arrivee.nom() + ", depart a " + dateDepart + " :");
        for(Etape e: sesEtapes) e.liste();
    }

    public boolean estCoherent() {
        // on vérifie que le moyen de transport utilisé pour chaque étape est bien possible
        for (Etape e : sesEtapes) {
            if (!e.estPossible()) {
                return false;
            }
        }
        return true;
    }

    public Heure hArrivee() throws ErreurTrajet {
    	if (sesEtapes.size() > 0) {
    		return sesEtapes.get(sesEtapes.size()-1).hArrivee();
    	} else {
    		return dateDepart;
    	}
    }

    public Heure duree() throws ErreurTrajet {
    	Heure dureeTotale = new Heure();
    	try {
    		for (Etape etapes : sesEtapes) {
				dureeTotale = dureeTotale.add(etapes.duree());
			}
        } catch (ErreurHeure e) {
			throw new ErreurTrajet(e.getMessage());
		}
    	return dureeTotale;
    }

    public Heure attente() throws ErreurTrajet {
    	Heure attenteTotale = new Heure();
    	try {
    		for (Etape etapes : sesEtapes) {
    			attenteTotale = attenteTotale.add(etapes.attente());
			}
        } catch (ErreurHeure e) {
			throw new ErreurTrajet(e.getMessage());
		}
    	return attenteTotale;
    }

    public int nbChgt() throws ErreurTrajet {
    	int compteur = 0;
        for (int i = 0, j = 1 ; j < sesEtapes.size() ; i++, j++) {
        	if (!sesEtapes.get(i).moyen().toString().equals(sesEtapes.get(j).moyen().toString())) {
        		compteur++;
        	}
        }
        return compteur;
    }

    public static Trajet meilleur(Collection<Trajet> col, Comparateur comp) throws ErreurTrajet {
        Object[] liste = col.toArray();
        if (liste.length == 0) {
        	throw new ErreurTrajet("Collection vide");
        }
        int best = 0;
        for (int j = 1 ; j < liste.length ; j++) {
        	if (comp.compare((Trajet)liste[best], (Trajet)liste[j]) == 1) {
        		best = j;
        	}
        }
        return (Trajet)liste[best];
    }

}
