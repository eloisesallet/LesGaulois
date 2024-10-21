package gestionMusee;

import gestionVillage.Equipement;
import personnages.Gaulois;

public class Musee {
    private Trophee[] trophees;
    private int nbTrophee;

    public Musee() {
        this.trophees = new Trophee[200];
        this.nbTrophee = 0;
    }
	
    public void donnerTrophee(Gaulois gaulois, Equipement equipement) {
        if (nbTrophee < trophees.length) {
            trophees[nbTrophee] = new Trophee(gaulois, equipement);
            nbTrophee++;
        } else {
            System.out.println("Le musée est plein, impossible d'ajouter plus de trophées.");
        }
    }
    
    public String extraireInstructionsOCaml() {
        String ocamlInstructions = "let musee = [\n";

        // Parcourir le tableau de trophées
        for (int i = 0; i < nbTrophee; i++) {
            Trophee trophee = trophees[i];
            if (trophee != null) {
                // Concaténer chaque couple (nom du gaulois, nom du trophée) au format OCaml
                ocamlInstructions += "\"" + trophee.donnerNom() + "\", \"" 
                                  + trophee.getEquipement().toString().toLowerCase() + "\";\n";
            }
        }
        // Fermer la liste OCaml
        ocamlInstructions += "]";
        
        return ocamlInstructions;
    }
}

