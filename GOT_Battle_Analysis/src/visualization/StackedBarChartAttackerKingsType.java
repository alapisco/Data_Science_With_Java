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

import static javafx.application.Application.launch;

public class StackedBarChartAttackerKingsType extends Application {
    
    final CategoryAxis xAxis = new CategoryAxis();
    final NumberAxis yAxis = new NumberAxis();
    
    final StackedBarChart<String, Number> sbc
            = new StackedBarChart<>(xAxis, yAxis);
    
    @Override
    public void start(Stage stage) {

        //parse dataset
        ArrayList<Battle> battles = new Parser().parseDataset();

        // For this chart we need a list of years and attacker kings
        String[] battleTypes = getBattleTypes(battles);
        String[] attackerKings = getAttackerKings(battles);

        // Create series (battle types)
        XYChart.Series<String, Number>[] series = new XYChart.Series[battleTypes.length];
        
        for (int i = 0; i < series.length; i++) {

            // Initializate new serie
            series[i] = new XYChart.Series<>();

            // Get battle type name
            String battleType = battleTypes[i];
            series[i].setName(battleType);
            
            for (int j = 0; j < attackerKings.length; j++) {
                
                String attackerKing = attackerKings[j];

                //get number of attacks from every year
                int attacks = getNumberOfAttacks(battleType, attackerKing, battles);

                //add data
                series[i].getData().add(new XYChart.Data<>(attackerKing, attacks));
                
            }
        }
        
        sbc.setTitle("Attacker Kings");
        
        xAxis.setLabel("Attacker Kings");
        xAxis.setCategories(FXCollections.<String>observableArrayList(attackerKings));
        yAxis.setLabel("Number of Attacks");
        
        Scene scene = new Scene(sbc, 800, 600);
        sbc.getData().addAll(series);
        
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        
        launch(args);
    }
    
    public int getNumberOfAttacks(String battleType, String attackerKing, ArrayList<Battle> battles) {
        
        int numberOfBattles = 0;
        for (Battle battle : battles) {
            String battleTypeStr = battle.getBattle_type()==null?"": battle.getBattle_type().toString();
            if (battleTypeStr.equals(battleType) && battle.getAttacker_king().equals(attackerKing)) {
                numberOfBattles++;
            }
        }
        
        return numberOfBattles;
    }
    
    public String[] getBattleTypes(ArrayList<Battle> battles) {
        
        HashSet<String> battleTypes = new HashSet<>();
        for (Battle battle : battles) {
            Battle.BattleType battleType = battle.getBattle_type();
            if (battleType == null) {
                battleTypes.add("");
            } else {
                battleTypes.add(battleType.toString());
            }
        }
        return battleTypes.toArray(new String[battleTypes.size()]);
        
    }
    
    public String[] getAttackerKings(ArrayList<Battle> battles) {
        
        HashSet<String> attackers = new HashSet<>();
        for (Battle battle : battles) {
            attackers.add(battle.getAttacker_king());
        }
        return attackers.toArray(new String[attackers.size()]);
        
    }
    
}
