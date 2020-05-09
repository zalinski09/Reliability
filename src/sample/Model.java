package sample;

import java.util.ArrayList;

public class Model {

    public static final int nbJourFiabilite = 5;

    public static int nbPoste;

    public static double MTBFposte;

    public static double tauxDefaillance;

    public static double MTBFchaineComplete;

    public static double periodeOeuvre;

    public static double tpsReparationPanne;

    public static double tpsReparationMoyen;

    public static double nbPanneMoyenJour;

    public static ArrayList<Double> fiabilite;


    public static void calculerFiabilite() {
        fiabilite = new ArrayList<Double>();
        for (int i = 1; i <= nbJourFiabilite ; i++) {
            fiabilite.add(Maths.loideSurvie(periodeOeuvre * i, tauxDefaillance * nbPoste)*100);
            System.out.println(fiabilite.get(i-1));
        }
    }


}
