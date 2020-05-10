package sample;

public class Test {


    public static void main(String[] args) {
        int nbIter = 100;
        int nbPost = 5;
        double tauxDefaillance = 0.02;



        //Maths.minSimu(Maths.simulationLoiExpCroissant(nbIter,nbPost,tauxDefaillance));
        Maths.simulationParMoisEnSerie(nbIter,nbPost,tauxDefaillance);/**panne par mois de la chaine**/
        Maths.simulationCumuleeParMoisEnSerie(nbIter,nbPost,tauxDefaillance);/**durée de vie de la chaine**/
        Maths.calculMTBF(Maths.minSimu(Maths.simulationLoiExpCroissant(nbIter,nbPost,tauxDefaillance))); /**mettre la liste des minimums des postes en séries pour obtenir le MTBF simulé de la série, comme précédement

         avec       int nbIter = 10000;
         int nbPost = 7;
         double tauxDefaillance = 0.02;

         on trouve environ 7,2 à chaque coup. cela correspond car la MTBF d'une série de poste doit valoir 1/(nbPost*tauxDefaillance), ce qui vaut à peu près 7.
         Pour faire cela, je simule un tableau de double, avec nbIter lignes, et nbPost colones. Donc chaque ligne représente une série de poste.
         On sait que si un poste se casse, alors toute la série est cassée.
         On sait que nous générons des durées de vies aléatoirement. J'en remplis mon tableau.
         Je choisis la durée de vie la plus petite avec la fonction minSimu, qui transforme la matrice de double en liste des doubles minimaux de chaque série. Je parcours chaque ligne de la matrice et prends le minimum.
         Ensuite j'applique les mêmes actions que pour un seul poste. La seul différence est que du coup la durée de vie globale est plus courte, donc MTBF plus petite etc etc.

         **/
        //System.out.print(Maths.calculMTBF(Maths.simulationLoiExpCroissant(nbIter,tauxDefaillance)));
        //Maths.simulationLoiExpCroissant(nbIter,nbPost,tauxDefaillance);

        //Maths.simulationParMois(nbIter,tauxDefaillance);
        // Maths.show(Maths.simulationLoiExpCroissant(nbIter,tauxDefaillance),nbIter);
        //  Maths.show(Maths.simulationCumuleeParMois(nbIter,tauxDefaillance),4*nbIter);
    }
}
