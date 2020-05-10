package sample;

import java.util.Arrays;


public class Maths {



    public static void show(double[][] matrice, int n, int m){

        for(int i=0; i<n ; i++){
            for(int j=0;j<m;j++){
                System.out.print(matrice[i][j]+"  ");
            }
            System.out.println();
        }

    }

    public static void show(double[] matrice, int n){


            for(int j=0;j<n;j++){
                System.out.println(matrice[j]);
            }


    }
    public static double[] genererNombresAleatoires(int nbIteration){
        double[] nbAleatoires = new double[nbIteration];

        for(int i=0;i<nbIteration;i++){
            nbAleatoires[i]=Math.random();
        }

        return nbAleatoires;
    }

    /**
     *

     * @param nbIteration
     * @param tauxDefaillance
     * @return liste de taille nbIteration, contenant les durées de vie de chaque appareil dans l'ordre croissant
     */


    public static double[] simulationLoiExpCroissant(int nbIteration, double tauxDefaillance){
        double[] simulationListe = new double[nbIteration];
        for(int i=0;i<nbIteration;i++) simulationListe[i] = -Math.log(Math.random())/tauxDefaillance;


        Arrays.sort(simulationListe);

        return simulationListe;

    }

    public static double[][] simulationLoiExpCroissant(int nbIteration,int nbPosts ,double tauxDefaillance){
        double[][] simulationListe = new double[nbIteration][nbPosts];
        for(int i=0;i<nbIteration;i++) {

         for(int j=0;j<nbPosts;j++){
            simulationListe[i][j] = (-1 / (tauxDefaillance) * Math.log(Math.random()));
         }
        }

    // show(simulationListe,nbIteration,nbPosts);

        return simulationListe;

    }
    public static double[] minSimu(double[][] simu){
        double[] minimums=new double[simu.length];

        for(int j=0;j<simu.length;j++) {


            double maxVal = Double.MAX_VALUE;
            double minVal = Double.MIN_VALUE;




            for (int i = 0; i < simu[j].length; i++) {
                if (simu[j][i] < maxVal)
                    maxVal = simu[j][i];
                if (simu[j][i] > minVal)
                    minVal = simu[j][i];
            }
            minimums[j]=maxVal;
        }

        //System.out.println(simu.length);
        //System.out.print(simu[0].length);
       //show(simu,simu.length,simu[0].length);
       //show(minimums,simu.length);
        return minimums;
    }
    public static double calculMTBF(double[] simu){
        double somme=0;
        for(int i=0;i<simu.length;i++){
            somme+=simu[i];
        }
        System.out.print(somme/simu.length);
        return somme/simu.length;
    }
    //pannes par mois

    /**
     *
     * @param nbIteration
     * @param tauxDefaillance
     * @return liste des durées de vie d'un poste sur un mois
     */
    public static double[] simulationParMois(int nbIteration, double tauxDefaillance){
        double[] listCroissante = simulationLoiExpCroissant(nbIteration,tauxDefaillance);
        double[] listeCumul = new double[(int)listCroissante[nbIteration-1]];


        for(int j=0;j<(int)listCroissante[nbIteration-1];j++) {

            double cumul = 0;
            for (int i = 0; i < nbIteration; i++) {

               if(listCroissante[i]<j+1&&listCroissante[i]>=j){
                   cumul ++;
               }
            }
            listeCumul[j]=cumul;
        }
        show(listeCumul,(int)listCroissante[nbIteration-1]);
            return listeCumul;
    }
    //pannes par mois

    /**
     *
     * @param nbIteration
     * @param nbPostes
     * @param tauxDefaillance
     * @return liste des durées de vie en des chaines de postes, en fonctions du mois ( nième mois de vie)
     */
    public static double[] simulationParMoisEnSerie(int nbIteration,int nbPostes ,double tauxDefaillance){
        double[] listCroissante = minSimu(simulationLoiExpCroissant(nbIteration,nbPostes,tauxDefaillance));
        double[] listeCumul = new double[(int)listCroissante[nbIteration-1]];


        for(int j=0;j<(int)listCroissante[nbIteration-1];j++) {

            double cumul = 0;
            for (int i = 0; i < nbIteration; i++) {

                if(listCroissante[i]<j+1&&listCroissante[i]>=j){
                    cumul ++;
                }
            }
            listeCumul[j]=cumul;
        }
        show(listeCumul,(int)listCroissante[nbIteration-1]);
        return listeCumul;
    }


    /**
     *
     * @param nbIteration
     * @param tauxDefaillance
     * @return liste des durées de vies cumulées par mois d'un seul poste
     */

    public static double[] simulationCumuleeParMois(int nbIteration, double tauxDefaillance){
        double[] listCroissante = simulationLoiExpCroissant(nbIteration,tauxDefaillance);
        double[] listeCumul = new double[(int)listCroissante[nbIteration-1]];


        for(int j=0;j<(int)listCroissante[nbIteration-1];j++) {
            double cumul = 0;

            for (int i = 0; i < nbIteration; i++) {

                if(listCroissante[i]>=j){
                    cumul ++;

                }

            }
            listeCumul[j]=cumul;
        }

        show(listeCumul,(int)listCroissante[nbIteration-1]);
        return listeCumul;
    }

    /**
     *
     * @param nbIteration
     * @param nbPostes
     * @param tauxDefaillance
     * @return liste des durées de vie d'une chaine de postes cumulées par mois
     */
    public static double[] simulationCumuleeParMoisEnSerie(int nbIteration, int nbPostes ,double tauxDefaillance){
        double[] listCroissante = minSimu(simulationLoiExpCroissant(nbIteration,nbPostes,tauxDefaillance));
        double[] listeCumul = new double[(int)listCroissante[nbIteration-1]];


        for(int j=0;j<(int)listCroissante[nbIteration-1];j++) {
            double cumul = 0;

            for (int i = 0; i < nbIteration; i++) {

                if(listCroissante[i]>=j){
                    cumul ++;

                }

            }
            listeCumul[j]=cumul;
        }

        show(listeCumul,(int)listCroissante[nbIteration-1]);
        return listeCumul;
    }


    /**
     *
     * @param MTBF
     * @param n
     * @return
     * @throws Exception
     */
    // pour un poste, n=1;


    // tauxdeDefaillance théorique
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
