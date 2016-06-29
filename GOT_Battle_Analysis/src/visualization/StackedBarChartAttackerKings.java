package visualization;

import java.util.ArrayList;
import java.util.HashSet;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import models.Battle;
import util.Parser;

public class StackedBarChartAttackerKings extends Application {

    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    final StackedBarChart<String, Number> sbc
            = new StackedBarChart<>(xAxis, yAxis);

    @Override
    public void start(Stage stage) {

        //parse dataset
        ArrayList<Battle> battles = new Parser().parseDataset();

        // For this chart we need a list of years and attacker kings
        String[] years = getYears(battles);
        String[] attackerKings = getAttackerKings(battles);

        // Create series (Kings)
        XYChart.Series<String, Number>[] series = new XYChart.Series[attackerKings.length];

        // For every serie (king)
        for (int i = 0; i < series.length; i++) {

            // Initializate new serie
            series[i] = new XYChart.Series<>();

            // Get attacker kings name
            String kings = attackerKings[i];
            series[i].setName(kings);

            for (int j = 0; j < years.length; j++) {

                String year = years[j];

                //get number of attacks from every year
                int attacks = getNumberOfAttacks(kings, year, battles);

                //add data
                series[i].getData().add(new XYChart.Data<>(year, attacks));

            }
        }

        sbc.setTitle("Attacker Kings");

        xAxis.setLabel("Years");
        xAxis.setCategories(FXCollections.<String>observableArrayList(years));
        yAxis.setLabel("Number of Attacks");

        Scene scene = new Scene(sbc, 800, 600);
        sbc.getData().addAll(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }

    public String[] getYears(ArrayList<Battle> battles) {

        HashSet<String> years = new HashSet<>();
        for (Battle battle : battles) {
            years.add(Integer.toString(battle.getYear()));
        }
        return years.toArray(new String[years.size()]);

    }

    public String[] getAttackerKings(ArrayList<Battle> battles) {

        HashSet<String> attackers = new HashSet<>();
        for (Battle battle : battles) {
            attackers.add(battle.getAttacker_king());
        }
        return attackers.toArray(new String[attackers.size()]);

    }

    public int getNumberOfAttacks(String attackerKing, String year, ArrayList<Battle> battles) {

        int numberOfBatttles = 0;
        int yearInt = Integer.parseInt(year);
        for (Battle battle : battles) {
            if (battle.getAttacker_king().equals(attackerKing) && battle.getYear() == yearInt) {
                numberOfBatttles++;
            }
        }

        return numberOfBatttles;
    }

}
