package sample;

//import com.sun.javaws.exceptions.ErrorCodeResponseException;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;


public class Controller {

    @FXML
    TextField nbPosteTravail;

    @FXML
    TextField MTBF;

    @FXML
    TextField defaillance;

    @FXML
    TextField MtbfComplete;

    @FXML
    TextField tpsReparation;

    @FXML
    TextField tpsReparationMoyen;

    @FXML
    TextField periodeOeuvre;

    @FXML
    TextField nbPanneJour;

    @FXML
    Button valider;

    @FXML
    BarChart<String, Integer> grapheFiabilite;

    @FXML
    CategoryAxis fiabiliteX;

    @FXML
    NumberAxis fiabiliteY;

    /**
     *
     */
    public void validerParametres() throws Exception {
        if (!nbPosteTravail.getText().isEmpty() && Formatting.isInteger(nbPosteTravail.getText())){
            Model.nbPoste = Integer.parseInt(nbPosteTravail.getText());
        }
        else {
            erreurParametresIncorrects("Nombre de postes de travail");
            return ;
        }

        if(!periodeOeuvre.getText().isEmpty() && Formatting.isNonNegativeNumeric(periodeOeuvre.getText())) {
            String s = periodeOeuvre.getText();
            double periodeOeuvre = Double.parseDouble(s);
            if(periodeOeuvre <= 24) {
                Model.periodeOeuvre = periodeOeuvre;
            }
            else {
                erreurParametresIncorrects("Periode oeuvrée supérieur à 24h");
            }
        }
        else {
            erreurParametresIncorrects("Format incorrect pour la période oeuvrée");
        }

        if (!tpsReparation.getText().isEmpty() && Formatting.isNonNegativeNumeric(tpsReparation.getText())) {
            Model.tpsReparationPanne = Double.parseDouble(tpsReparation.getText());
        }
        else {
            erreurParametresIncorrects("Format incorrect pour le temps de réparation / panne");
        }


        if (!MTBF.getText().isEmpty() && Formatting.isNonNegativeNumeric(MTBF.getText())) {
            Model.MTBFposte = Double.parseDouble(MTBF.getText());
            Model.tauxDefaillance = Maths.calculTauxDefaillance(Model.MTBFposte,1);
        }
        else if (!defaillance.getText().isEmpty() && Formatting.isNonNegativeNumeric(defaillance.getText())) {
            Model.tauxDefaillance = Double.parseDouble(defaillance.getText());
            Model.MTBFposte = Maths.calculMTBF(Model.tauxDefaillance);
        }
        else {
            erreurParametresIncorrects("MTBF ou taux de défaillances");
            return ;
        }

        Model.MTBFchaineComplete = Model.MTBFposte * Model.nbPoste;

        Model.nbPanneMoyenJour = Maths.calculProbaNombrePannes(Model.tauxDefaillance , Model.periodeOeuvre, Model.nbPoste);

        Model.tpsReparationMoyen = Maths.calculProbaNombrePannes(Model.tauxDefaillance, Model.tpsReparationPanne, Model.nbPoste);

        Model.calculerFiabilite();

        updateVueDepuisModel();

    }

    public void updateVueDepuisModel() {
        nbPosteTravail.setText(String.valueOf(Model.nbPoste));
        MTBF.setText(String.valueOf(Model.MTBFposte));
        defaillance.setText(String.valueOf(Model.tauxDefaillance));
        MtbfComplete.setText(String.valueOf(Model.MTBFchaineComplete));
        tpsReparation.setText(String.valueOf(Model.tpsReparationPanne));
        tpsReparationMoyen.setText(String.valueOf(Model.tpsReparationMoyen));
        nbPanneJour.setText(String.valueOf(Model.nbPanneMoyenJour));
        updateFiabiliteGraphe();

    }

    public void updateFiabiliteGraphe() {
        fiabiliteY.setUpperBound(Model.fiabiliteMois[0]);
        XYChart.Series serie = new XYChart.Series();
        for (int i =0; i < Model.fiabiliteMois.length; i++) {
            serie.getData().add(new XYChart.Data<String, Number>("Jour " + i, Model.fiabiliteMois[i]));
            //System.out.println(Model.fiabilite.get(i));


        }
        grapheFiabilite.getData().add(serie);
    }

    public void initialize() throws Exception{
        fiabiliteY.setUpperBound(100);
        validerParametres();
    }

    private void erreurParametresIncorrects(String nomParametre) {
        System.err.println(nomParametre);
    }



}
