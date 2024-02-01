public class ReseauNeurones {
    private Couche[] couches;

    /* Initialisation d'un réseau de neuronne
    * il est composé de plusieurs couches qui contiennent eux même des neuronnes*/
    public ReseauNeurones(int[] architecture) {
        couches = new Couche[architecture.length];

        for (int i = 0; i < architecture.length; i++) {
            int nbNeurones = architecture[i];
            int nbEntrees = (i == 0) ? 0 : architecture[i - 1]; // 0 pour la première couche
            couches[i] = new Couche(nbNeurones, nbEntrees);
        }
    }

    /* Méthode pour obtenir les sorties du réseau */
    public double[] getSorties(double[] entrees) {
        double[] sorties = entrees;

        for (Couche couche : couches) {
            sorties = couche.getSorties(sorties);
        }

        return sorties;
    }

    /* Méthode d'entraînement avec rétropropagation du gradient
    * Le gradient est utilisé pour ajuster les poids et le biais du neurone de manière
    * à minimiser l'erreur entre les sorties du réseau et les sorties attendues. */
    public void entrainement(double[] entrees, double[] cibles, double tauxApprentissage) {
        double[] sorties = getSorties(entrees);

        // Calcul de l'erreur sur la couche de sortie
        double[] erreursSortie = new double[sorties.length];
        for (int i = 0; i < sorties.length; i++) {
            erreursSortie[i] = cibles[i] - sorties[i];
            System.out.println("l'erreur de sortie : "+erreursSortie[i]);
        }

        // Rétropropagation du gradient à travers les couches
        for (int i = couches.length - 1; i >= 0; i--) {
            Couche couche = couches[i];
            double[] erreursCouche = new double[couche.neurones.length];

            for (int j = 0; j < couche.neurones.length; j++) {
                double sommeErreurPonderee = 0;

                /* Permet de vérifier que la retropropagation du gradient n'est effectuée que pour les couches
                qui ne sont pas dernière couche du réseau. */
                if (couches.length > i + 1 && couches[i + 1].neurones.length > 0) {
                    for (int k = 0; k < Math.min(couche.neurones[j].poids.length, erreursSortie.length); k++) {

                        sommeErreurPonderee += couche.neurones[j].poids[k] * erreursSortie[k];
                        System.out.println("somme erreur pondérée : "+sommeErreurPonderee);
                    }
                }

                erreursCouche[j] = sommeErreurPonderee;
            }

            // Appliquer la rétropropagation pour chaque neurone de la couche
            couche.retropropagation(erreursCouche, tauxApprentissage);
        }
    }
}
