package lieux;
import java.util.Collection;
import java.util.ArrayList;

public class TestLieuxSuite {
    static Batiment B620, B640, Supelec, LeGuichet , OrsayVille ;
    static Arret AOrsay, AGuichet, APlateau, AChateau, ASupelec, AAlgo;
    static LigneBus L1, L2;

    public static void creerLieux() {
        B620 = new Batiment("MdI");
        B640 = new Batiment("PUIO");
        Supelec = new Batiment("Supelec");
        LeGuichet = new Batiment("Gare Guichet");
        OrsayVille = new Batiment("Gare Orsay");

        AOrsay = new Arret("Arret Gare Orsay");
        AGuichet = new Arret("Arret Gare Guichet");
        APlateau = new Arret("Arret MdI-PUIO");
        AChateau = new Arret("Chateau");
        ASupelec = new Arret("Arret CNEF");
        AAlgo = new Arret("Arret Les Algorithmes");

        Arret[] tabArret1 = { AOrsay, AChateau, APlateau, ASupelec, AAlgo };

        Arret[] tabArret2 = { AGuichet, APlateau, ASupelec };

        L1 = new LigneBus("Ligne 1"); L1.addArrets(tabArret1);
        L2 = new LigneBus("Ligne 2"); L2.addArrets(tabArret2);
    }

    static void setHoraires() throws ErreurTrajet, ErreurHeure {
        Heure[] hA1 = { new Heure(0, 3), new Heure(0, 5), new Heure(0, 7),
                        new Heure(0, 9)};
        Heure[] dA1 = {
            new Heure(9, 0),
            new Heure(9, 30),
            new Heure(10, 30),
            new Heure(14, 0)
        };
        L1.ajoutHoraires(hA1, dA1);

        Heure[] hA2 = { new Heure(0, 8), new Heure(0, 3)};
        Heure[] dA2 = {
            new Heure(9, 0),
            new Heure(10, 0),
            new Heure(11, 0)
        };
        L2.ajoutHoraires(hA2, dA2);
    }

   static void setDistances()  throws ErreurTrajet, ErreurHeure {
       B620.addVoisin(APlateau, new Heure(0, 2));
       B640.addVoisin(APlateau, new Heure(0, 5));
       AOrsay.addVoisin(OrsayVille, new Heure(0, 1));
       AOrsay.addVoisin(AChateau, new Heure(0, 15));
       AChateau.addVoisin(APlateau, new Heure(0, 30));
       AGuichet.addVoisin(LeGuichet, new Heure(0, 1));
       APlateau.addVoisin(Supelec, new Heure(0, 20));
       APlateau.addVoisin(ASupelec, new Heure(0, 10));
       ASupelec.addVoisin(Supelec, new Heure(0, 7));
   }

    static void addRaccourci() throws ErreurTrajet, ErreurHeure {
        B620.addVoisin(B640, new Heure(0, 4));
    }


    public static void testeTrajet(Trajet t) throws ErreurTrajet, ErreurHeure {
        if (t.estCoherent()) {
            t.liste();
            System.out.println("Heure d'arrivee pour " + t.nom()
                               + ": " + t.hArrivee().toString());
            System.out.println();
        } else {
            System.out.println("Trajet " + t.nom() + " est incoherent !"); }
    }

   public static Trajet mkTrajet1() throws ErreurTrajet, ErreurHeure {
       ArrayList<Etape> res = new ArrayList<Etape>();
       Heure dep = new Heure(8, 50);
       res.add(new Etape(OrsayVille, AOrsay, new APieds(), dep));
       res.add(new Etape(AOrsay, APlateau, new EnBus(L1), new Heure(9, 0)));
       res.add(new Etape(APlateau, B640, new APieds(), new Heure(9, 8)));
       res.add(new Etape(B640, B620, new APieds(), new Heure(9, 13)));
       return new Trajet("t1", OrsayVille, B620, dep, res);
    }

    public static Trajet mkTrajet1a() throws ErreurTrajet, ErreurHeure {
        ArrayList<Etape> res = new ArrayList<Etape>();
        Heure dep = new Heure(8, 50);
        res.add(new Etape(OrsayVille, AOrsay, new APieds(), dep));
        res.add(new Etape(AOrsay, APlateau, new EnBus(L1), new Heure(8, 53)));
        res.add(new Etape(APlateau, B640, new APieds(), new Heure(9, 12)));
        res.add(new Etape(B640, B620, new APieds(), new Heure(9, 17)));
        return new Trajet("t1a", OrsayVille, B620, dep, res);
    }


   public static Trajet mkTrajet1b() throws ErreurTrajet, ErreurHeure {
       ArrayList<Etape> res = new ArrayList<Etape>();
       Heure dep = new Heure(8, 50);
       res.add(new Etape(OrsayVille, AOrsay, new APieds(), dep));
       res.add(new Etape(AOrsay, APlateau, new EnBus(L1), new Heure(9, 0)));
       res.add(new Etape(APlateau, B620, new APieds(), new Heure(9, 8)));
       return new Trajet("t1b", OrsayVille, B620, dep, res);
   }

   public static Trajet mkTrajet4() throws ErreurTrajet, ErreurHeure {
       Heure dep = new Heure(8, 40);
       ArrayList<Etape> res = new ArrayList<Etape>();
       res.add(new Etape(OrsayVille, AOrsay,  new APieds(), dep));
       res.add(new Etape(AOrsay, ASupelec,  new EnBus(L1), new Heure(8, 45)));
       return new Trajet("t4", OrsayVille, ASupelec, dep, res);
    }

   public static Trajet mkTrajet4b() throws ErreurTrajet, ErreurHeure {
       Heure dep = new Heure(8, 40);
       ArrayList<Etape> res = new ArrayList<Etape>();
       res.add(new Etape(OrsayVille, AOrsay, new APieds(), dep));
       res.add(new Etape(AOrsay, APlateau,  new EnBus(L1), new Heure(8, 45)));
       res.add(new Etape(APlateau, ASupelec, new EnBus(L2), new Heure(9, 8)));
       return new Trajet("t4b", OrsayVille, ASupelec, dep, res);
    }


   public static Trajet mkTrajet4c() throws ErreurTrajet, ErreurHeure {
       Heure dep = new Heure(8, 40);
       ArrayList<Etape> res = new ArrayList<Etape>();
       res.add(new Etape(OrsayVille, AOrsay, new APieds(), dep));
       res.add(new Etape(AOrsay, AChateau, new APieds(), new Heure(8, 41)));
       res.add(new Etape(AChateau, APlateau, new APieds(), new Heure(8, 56)));
       res.add(new Etape(APlateau, ASupelec, new APieds(), new Heure(9, 26)));
       return new Trajet("t4c", OrsayVille, ASupelec, dep, res);
    }

   public static Trajet mkTrajet5() throws ErreurTrajet, ErreurHeure {
       Heure dep = new Heure(8, 59);
       ArrayList<Etape> res = new ArrayList<Etape>();
       res.add(new Etape(OrsayVille, AOrsay, new APieds(), dep));
       res.add(new Etape(AOrsay, APlateau, new EnBus(L1), new Heure(9, 0)));
       res.add(new Etape(APlateau, Supelec, new APieds(), new Heure(9, 8)));
       res.add(new Etape(Supelec, ASupelec, new APieds(), new Heure(9, 35)));
       res.add(new Etape(ASupelec, AAlgo, new EnBus(L1), new Heure(10, 15)));
       return new Trajet("t5", OrsayVille, AAlgo, dep, res);
    }

   public static Trajet mkTrajet5b() throws ErreurTrajet, ErreurHeure {
       Heure dep = new Heure(8, 30);
       ArrayList<Etape> res = new ArrayList<Etape>();
       res.add(new Etape(OrsayVille, AOrsay, new APieds(), dep));
       /* Il devra attendre 15 mn en fait */
       res.add(new Etape(AOrsay, APlateau, new EnBus(L1), new Heure(8, 45)));
       /* se promene avant de reprendre le bus au meme endroit */
       res.add(new Etape(APlateau, ASupelec, new EnBus(L1), new Heure(9, 38)));
       res.add(new Etape(ASupelec, AAlgo, new EnBus(L1), new Heure(10, 15)));
       return new Trajet("t5b", OrsayVille, AAlgo, dep, res);
    }

   public static Trajet mkTrajet5c() throws ErreurTrajet, ErreurHeure {
       Heure dep = new Heure(8, 47);
       ArrayList<Etape> res = new ArrayList<Etape>();
       res.add(new Etape(OrsayVille, AOrsay, new APieds(), dep));
       res.add(new Etape(AOrsay, AChateau, new APieds(), new Heure(8, 48)));
       res.add(new Etape(AChateau, APlateau, new EnBus(L1), new Heure(9,3 )));
       res.add(new Etape(APlateau, AAlgo, new EnBus(L1), new Heure(9, 8)));
       return new Trajet("t5c", OrsayVille, AAlgo, dep, res);
    }


    public static Trajet mkTrajet5d() throws ErreurTrajet, ErreurHeure {
       Heure dep = new Heure(8, 40);
       ArrayList<Etape> res = new ArrayList<Etape>();
       res.add(new Etape(OrsayVille, AOrsay, new APieds(), dep));
       res.add(new Etape(AOrsay, AChateau, new APieds(), new Heure(8, 41)));
       res.add(new Etape(AChateau, APlateau, new APieds(), new Heure(8, 56)));
       res.add(new Etape(APlateau, AAlgo, new EnBus(L1), new Heure(9, 26)));
       return new Trajet("t5d", OrsayVille, AAlgo, dep, res);
    }


    public static void main(String[] args) throws ErreurTrajet, ErreurHeure  {
        creerLieux();
        setDistances();
        addRaccourci();
        setHoraires();

        Trajet t4 = mkTrajet4(); testeTrajet(t4);
        Trajet t4b = mkTrajet4b(); testeTrajet(t4b);
        Trajet t4c = mkTrajet4c(); testeTrajet(t4c);

        System.out.println("\nComparaison de trajets");
        Collection<Trajet> col = new ArrayList<Trajet>();
        col.add(t4); col.add(t4b); col.add(t4c);

        System.out.println("Comparaison en termes de temps total");
        Trajet res1 = Trajet.meilleur(col, new CompTemps());
        res1.liste();

        System.out.println("\nComparaison en termes de temps d'attente");
        Trajet res2 = Trajet.meilleur(col, new CompAttente());
        res2.liste();

        System.out.println("\nComparaison en termes de nombre de changements");
        Trajet res3 = Trajet.meilleur(col, new CompChgt());
        res3.liste();

        System.out.println("\nComparaison de changements entre t4 et t4b");
        Collection<Trajet> col2 = new ArrayList<Trajet>();
        col2.add(t4); col2.add(t4b);
        Trajet res4 = Trajet.meilleur(col2, new CompChgt());
        res4.liste();

        System.out.println("\nTest des trajets5");

        Trajet t5 = mkTrajet5(); testeTrajet(t5);
        Trajet t5b = mkTrajet5b(); testeTrajet(t5b);
        Trajet t5c = mkTrajet5c(); testeTrajet(t5c);
        Trajet t5d = mkTrajet5d(); testeTrajet(t5d);
        Collection<Trajet> col5 = new ArrayList<Trajet>();
        col5.add(t5); col5.add(t5b); col5.add(t5c); col5.add(t5d);
        for(Trajet t: col5) {
            System.out.print(t.nom());
            System.out.print(" , duree : " + t.duree());
            System.out.print(" , attente : " + t.attente());
            System.out.println(" , changements: " + t.nbChgt());
        }
        Trajet b1 = Trajet.meilleur(col5, new CompTemps());
        Trajet b2 = Trajet.meilleur(col5, new CompAttente());
        Trajet b3 = Trajet.meilleur(col5, new CompChgt());
        System.out.println("Le plus court: " + b1.nom());
        System.out.println("Le moins d'attente: " + b2.nom());
        System.out.println("Le moins de changements: " + b3.nom());
        col5.remove(t5d);
        Trajet b4 = Trajet.meilleur(col5, new CompChgt());
        System.out.println("Le moins de changements (bis): " + b4.nom());
    }
}
