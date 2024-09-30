package gestionVillage;

import personnages.Gaulois;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;

	public Village(String nom, int nbVillageoisMax) {
		this.nom = nom;
		villageois = new Gaulois[nbVillageoisMax];
	}
	
	public void setChef(Chef chef) {
		this.chef = chef;
	}
	
	public String getNom() {
		return nom;
	}
	
	public String getChef() {
		return chef.getNom();
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois]=gaulois;
			nbVillageois +=1;
		} else {
			System.out.println("Erreur");
		}
	}
	
	public Gaulois trouverHabitant(int num) {
		return villageois[num-1];
	}
	
	public void afficherVillageois(Village village) {
		System.out.println("Dans le village du chef " + village.getChef() 
		+ " vivent les légendaires gaulois : ");
		for(int i=0;i<nbVillageois;i++) {
			System.out.println("- " + (villageois[i].getNom()));
		}
	}

	
	public static void main(String[] args) {
		Village village = new Village("Village des Irréductibles",30);
		
//	    Gaulois gaulois = village.trouverHabitant(30);
//	    Index 30 out of bounds for length 30
//		car les indices partent de 0 donc indice 30 = indice 31 
		
		Chef abraracourix = new Chef("Abraracourix",6, village);
		village.setChef(abraracourix);
		
		Gaulois asterix = new Gaulois("Astérix",8);
		village.ajouterHabitant(asterix);
		
//		Gaulois gaulois = village.trouverHabitant(1);
//		System.out.println(gaulois);
//		résultat = Gaulois [nom = Astérix, force = 8, effetPotion = 1] 
//	    c'est le premier habitant car dans trouver habitant on retourne num-1
		
		Gaulois obelix = new Gaulois("Obélix",25);
		village.ajouterHabitant(obelix);
		village.afficherVillageois(village);
	}
	
}
