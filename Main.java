import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Définir l'architecture du réseau
        int[] architecture = { 2, 3, 1 };

        // Initialiser le réseau
        ReseauNeurones reseau = new ReseauNeurones(architecture);

        // Données d'entraînement
        double[][] donneesEntrainement = {
                { 0.1, 0.2 }, // Entrée 1
                { 0.4, 0.5 }, // Entrée 2
        };

        double[][] ciblesEntrainement = {
                { 0.8 }, // Sortie attendue pour Entrée 1
                { 0.3 }, // Sortie attendue pour Entrée 2
        };

        // Entraînement sur plusieurs époques
        int epochs = 1000;
        double tauxApprentissage = 0.1;

        for (int epoch = 0; epoch < epochs; epoch++) {
            for (int i = 0; i < donneesEntrainement.length; i++) {
                // Obtenir l'exemple d'entraînement
                double[] entrees = donneesEntrainement[i];
                double[] cibles = ciblesEntrainement[i];

                // Effectuer une étape d'entraînement
                reseau.entrainement(entrees, cibles, tauxApprentissage);
            }

            // Vous pouvez également ajouter des instructions pour afficher l'erreur, etc.
            // System.out.println("Erreur moyenne : " + calculerErreurMoyenne(reseau, donneesEntrainement, ciblesEntrainement));
        }

        // Utilisation du réseau après l'entraînement
        for (int i = 0; i < donneesEntrainement.length; i++) {
            double[] entrees = donneesEntrainement[i];
            double[] sorties = reseau.getSorties(entrees);

            System.out.println("Entrée : " + Arrays.toString(entrees));
            System.out.println("Sortie attendue : " + Arrays.toString(ciblesEntrainement[i]));
            System.out.println("Sortie du réseau : " + Arrays.toString(sorties));
            System.out.println("-------------------");
        }
    }
}
