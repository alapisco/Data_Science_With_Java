package visualization;

import java.util.ArrayList;
import java.util.HashSet;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import models.Battle;
import util.Parser;

public class BarChartAttackersDefenders extends Application {

    @Override
    public void start(Stage stage) {

        XYChart.Series series1 = new XYChart.Series();
        series1.setName("Attacker Size");
        XYChart.Series series2 = new XYChart.Series();
        series2.setName("Defender Size");

        //parse dataset
        ArrayList<Battle> battles = new Parser().parseDataset();

        String[] battleNames = this.getBattleNames(battles);

        int added = 0;
        for (String battleName : battleNames) {

            int attackerSize = getAttackerNumbers(battleName, battles);
            int defenderSize = getDefenderNumbers(battleName, battles);

            if (attackerSize != 0 && defenderSize != 0) {
                series1.getData().add(new XYChart.Data(attackerSize, battleName));
                series2.getData().add(new XYChart.Data(defenderSize, battleName));
                added++;
            }

            if (added == 10) {
                break;
            }

        }

        NumberAxis xAxis = new NumberAxis();
        CategoryAxis yAxis = new CategoryAxis();
        BarChart<Number, String> bc
                = new BarChart<>(xAxis, yAxis);
        
        bc.setTitle("Battle Participants");
        xAxis.setLabel("Army Size");
        xAxis.setTickLabelRotation(90);
        yAxis.setLabel("Battle Name");

        Scene scene = new Scene(bc, 1600, 1200);
        bc.getData().addAll(series1, series2);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public String[] getBattleNames(ArrayList<Battle> battles) {

        HashSet<String> battleNames = new HashSet<>();
        for (Battle battle : battles) {
            battleNames.add(battle.getName());

        }
        return battleNames.toArray(new String[battleNames.size()]);

    }

    public int getDefenderNumbers(String battleName, ArrayList<Battle> battles) {

        for (Battle battle : battles) {
            if (battle.getName().equals(battleName)) {
                Integer size = battle.getDefender_size();
                size = size == null ? 0 : size;
                return size;
            }
        }

        return 0;
    }

    public int getAttackerNumbers(String battleName, ArrayList<Battle> battles) {

        for (Battle battle : battles) {
            if (battle.getName().equals(battleName)) {
                Integer size = battle.getAttacker_size();
                size = size == null ? 0 : size;
                return size;
            }
        }

        return 0;
    }
}
