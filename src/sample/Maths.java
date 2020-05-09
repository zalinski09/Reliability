package sample;

import java.lang.Math;

public class Maths {


    /**
     *
     * @param MTBF
     * @param n
     * @return
     * @throws Exception
     */
    // pour un poste, n=1;
    public static double calculTauxDefaillance(double MTBF,int n) throws Exception {

        if(MTBF<=0) {
            throw new Exception("Le MTBF ne peut pas être négatif ou nul");
        }
        return n/MTBF;
    };

    /**
     *
     * @param tauxDefaillance
     * @return
     * @throws Exception
     */
    public static double calculMTBF(double tauxDefaillance) throws Exception {

        if(tauxDefaillance<=0) {
            throw new Exception("Le tauxDefaillance ne peut pas être négatif ou nul");
        }
        return 1/tauxDefaillance;
    }



    /**
     *      fiabilité pour 14h : P(X>14) = exp(-tauxDefaillanceChaine*14)
     * @param   t
     * @param tauxDefaillanceChaine
     * @return fiabilité pour un temps t
     */
    public static double loideSurvie(double t,double tauxDefaillanceChaine){
        return Math.exp(-t*tauxDefaillanceChaine);
    }


    /**
     *
     * @param MTBF
     * @param tempsTravail
     * @param nbjour
     * @return  temps moyen de mobilisation de l'équipe d'entretien
     */
    public static double calculEntretien(double MTBF, double tempsTravail, int nbjour){

        return MTBF*tempsTravail*nbjour;
    }

    /**
     *
     * @param n
     * @return factoriel de n
     */
    public static int factoriel(int n) {
        int i, fact = 1;
        for (i = 1; i <= n; i++) {
            fact = fact * i;
        }
        return fact;
    }


    /**
     *
     * @param taux
     * @param temps
     * @param n
     * @return Proba que X soit égal à n
     */
    public static double calculProbaNombrePannes(double taux, double temps, int n){
        return Math.exp(-taux*temps)*(Math.pow(taux*temps,n)/(factoriel(n)));
    }

    /**
     *
     * @param taux
     * @param temps
     * @param n
     * @return Probab que X supérieur ou égal à n
     */
    public static double calculProbaMoinsDePannes(double taux, double temps, int n){
        double somme = 0;
        for(int i = 0; i< n+1; i++){
            somme += calculProbaNombrePannes(taux,temps,i);
        }
        return somme;
    }

}
