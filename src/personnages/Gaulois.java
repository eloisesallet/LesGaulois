package personnages;

import gestionMusee.Musee;
import gestionVillage.Equipement;

public class Gaulois {
	private String nom;
	private int force;
	private int nbtrophees;
	private int effetPotion = 1;
	private Equipement[] trophees = new Equipement[100];

	public Gaulois(String nom, int force) {
		this.nom = nom;
		this.force = force;
	}

	public String getNom() {
		return nom;
	}
	
	public void parler(String texte) {
		System.out.println(prendreParole() + "« " + texte + "» ");
	}

	/*private String prendreParole() {
		return "Le Gaulois " + nom + " : ";
	}*/
	
	private String prendreParole() {
		return "Le gaulois " + nom + " : ";
		}
	
	/*public void frapper(Romain romain) {
		System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
		romain.recevoirCoup((force/3)*effetPotion);
	}*/
	
	public void frapper(Romain romain) {
		System.out.println(nom + " envoie un grand coup dans la mâchoire de " + romain.getNom());
		Equipement[] troph = romain.recevoirCoup((force / 3)*effetPotion);
		for (int i = 0; troph != null && i < troph.length && nbtrophees < trophees.length; i++, nbtrophees++) {
			this.trophees[nbtrophees] = troph[i];
		}
	}

//  @Override
//	public String toString() {
//		return "Gaulois [nom = " + nom + ", force = " + force + ", effetPotion = " + effetPotion + "] ";
//	}
	
	public void boirePotion(int forcePotion) {
		effetPotion = forcePotion;
		System.out.println(prendreParole() + "Merci Druide, je sens que ma force est " + forcePotion + " fois décuplée. ");
	}
	
	 public void faireUneDonnation(Musee musee) {
	        if (nbtrophees > 0) {
	            System.out.println("Le gaulois " + nom + " : « Je donne au musée tous mes trophées :");
	            for (int i = 0; i < nbtrophees; i++) {
	                if (trophees[i] != null) {
	                    System.out.println("- " + trophees[i]);
	                    musee.donnerTrophee(this, trophees[i]);
	                }
	            }
	            nbtrophees = 0;
	        } else {
	            System.out.println("Le gaulois " + nom + " n'a pas de trophées à donner.");
	        }
	    }

	public static void main(String[] args) {
		Romain minus = new Romain("Minus", 12);
		Gaulois asterix = new Gaulois("Astérix", 8);
		System.out.println(asterix.nom);
		System.out.println(asterix);
		
		// test des fonctions
		
		System.out.println(asterix.prendreParole());
		asterix.parler("Bonjour ! ");
		asterix.frapper(minus);
		asterix.boirePotion(7);
	}
}