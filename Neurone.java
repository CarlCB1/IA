import java.util.*;
public class Neurone {
    public double[] poids;
    private double biais;
    private double sortie;
    private double[] entrees; // Nouvel attribut pour stocker les entrées lors de l'activation

    public Neurone(int nbEntrees) {
        poids = new double[nbEntrees];
        Random random = new Random();
        double ecartType = 1.0 / Math.sqrt(nbEntrees);  // Correction pour une initialisation Glorot
        for (int i = 0; i < nbEntrees; i++) {
            poids[i] = random.nextGaussian() * ecartType;
            System.out.println(poids[i]);
        }
        biais = random.nextGaussian() * ecartType;
    }

    // Méthode d'activation (sigmoid)
    public double activation(double[] entrees) {
        this.entrees = entrees; // Sauvegarder les entrées
        double sommePonderee = 0;
        for (int i = 0; i < poids.length; i++) {
            sommePonderee += poids[i] * entrees[i];
        }
        sommePonderee += biais;
        sortie = fonctionActivation(sommePonderee);
        return sortie;
    }

    private double fonctionActivation(double x) {
        return 1 / (1 + Math.exp(-x));
    }

    // Méthode de rétropropagation du gradient pour un neurone
    public void retropropagation(double erreur, double tauxApprentissage) {
        double deriveeSortie = sortie * (1 - sortie);
        double gradient = erreur * deriveeSortie;

        for (int i = 0; i < poids.length; i++) {
            double gradientPoids = gradient * entrees[i];
            poids[i] -= tauxApprentissage * gradientPoids;
        }

        // Mise à jour du biais
        biais -= tauxApprentissage * gradient;
    }
}
