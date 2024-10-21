package personnages;

import gestionVillage.Equipement;

public class Romain {
	private String nom;
	private int force;
	private Equipement[] equipements;
	private int nbEquipement = 0;
	private boolean vainqueur;
	
	public Romain(String nom, int force) {
		if (force < 0) {
			throw new IllegalArgumentException("Invalid force: " + force);
		}
		this.nom = nom;
		this.force = force;
		equipements = new Equipement[2];
	}
	
	  public boolean estVainqueur() {
	        return vainqueur;
	    }
	 
	public String getNom() {
		return nom;
	}
	
	public int getForce() {
		return force;
	}
	
	public void parler(String texte) {
		System.out.println(prendreParole() + "« " + texte + "» ");
	}

	private String prendreParole() {
		return "Le romain " + nom + " : ";
	}

/*public void recevoirCoup(int forceCoup) {
		assert(force>=0);
		int forceDebut=force;
		force -= forceCoup;
		if (force > 0) {
			parler("Aïe ");
		} else {
			parler("J'abandonne... ");
		}
		assert(forceDebut>force);
	}*/
	
	public Equipement[] recevoirCoup(int forceCoup) {
		Equipement[] equipementEjecte = null;
		// précondition
		if (force <= 0) {
			throw new IllegalArgumentException("Invalid force : " + force);
		}
		int oldForce = force;
		
		forceCoup = calculResistanceEquipement(forceCoup);
		
		force -= forceCoup;
		// if (force > 0) {
		// parler("Aïe");
		// } else {
		// equipementEjecte = ejecterEquipement();
		// parler("J'abandonne...");
		// }
		if (force == 0) {
			parler("Aïe");
		}
		else {
			equipementEjecte = ejecterEquipement();
			parler("J'abandonne...");
		}
		// post condition la force a diminuée
		if (forceCoup >= oldForce) {
	        parler("Je résiste au coup !");
	    } else {
	        parler("Aïe ! Le coup a réduit ma force.");
	    }
		
		if (force <= 0) {
            parler("J'abandonne...");
            vainqueur = false;  // Il perd
        } else {
            vainqueur = true;  // Il résiste au coup
        }
		
		return equipementEjecte;
	}
	
	
private int calculResistanceEquipement(int forceCoup) {
	String texte;
	texte = "Ma force est de " + this.force + ", et la force du coup est de " + forceCoup;
	int resistanceEquipement = 0;
	if (nbEquipement != 0){
		texte += "\nMais heureusement, grace à mon équipement sa force est diminué de ";
		for (int i = 0; i < nbEquipement; i++) {
			if ((equipements[i] != null && equipements[i].equals(Equipement.BOUCLIER))) {
				resistanceEquipement += 8;
			} else if ((equipements[i] != null && equipements[i].equals(Equipement.CASQUE))){
				System.out.println("Equipement casque");
				resistanceEquipement += 5;
			}
		}
		texte += resistanceEquipement + "!";
	}
	parler(texte);
	forceCoup = Math.max(0, forceCoup - resistanceEquipement);
	return forceCoup;
	}
	
	
private Equipement[] ejecterEquipement() {
	Equipement[] equipementEjecte = new Equipement[nbEquipement];
	System.out.println("L'équipement de " + nom + " s'envole sous la force du coup.");
	int nbEquipementEjecte = 0;
	for (int i = 0; i < nbEquipement; i++) {
		if (equipements[i] != null) {
			equipementEjecte[nbEquipementEjecte] = equipements[i];
			nbEquipementEjecte++;
			equipements[i] = null;
		}
	}
	return equipementEjecte;
	}
	
	public void sEquiper(Equipement equipement) {
		switch(nbEquipement) {
			case 0 :
			ajouterEquipement(equipement);
				break;
			case 1 :
				if(equipements[0]==equipement) {
					System.out.println("Le soldat " + nom + " possède déjà " + equipement + " ! ");
				}
				else {
					ajouterEquipement(equipement);
				}
				break;
			case 2 :
				System.out.println("Le soldat " + nom + " est déjà bien protégé !");
				break;
			default :
				throw new IllegalArgumentException("Invalid nbEquipement");
		}
	}

	private void ajouterEquipement(Equipement equipement) {
		equipements[nbEquipement]=equipement;
		nbEquipement+=1;
		System.out.println("Le soldat " + nom + " s'équipe avec un " + equipement + ". ");
	}
	
	public static void main(String[] args) {
		Romain minus = new Romain("Minus", 6);
		System.out.println(minus.prendreParole());
		minus.parler("Bonjour ! ");
		minus.recevoirCoup(30);
		System.out.println("Les équipements sont : ");
		for (Equipement equipement : Equipement.values()) {
			System.out.println("- " + equipement);
		}
		
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.CASQUE);
		minus.sEquiper(Equipement.BOUCLIER);
		minus.sEquiper(Equipement.CASQUE);
		
		
	}

}