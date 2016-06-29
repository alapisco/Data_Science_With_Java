package visualization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import models.Battle;
import util.Parser;

public class LineChartRegionsBattles extends Application {

    @Override
    public void start(Stage stage) {
        
        // define axys
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Regions");
        yAxis.setLabel("Number of Battles");

        final LineChart<String, Number> lineChart
                = new LineChart<>(xAxis, yAxis);

        lineChart.setTitle("Battles by Regions");

        XYChart.Series series = new XYChart.Series();
        

        //populating the series with data
        
        // parse from dataser
        ArrayList<Battle> battles = new Parser().parseDataset();
        
        // create a hashmap with regions and number of battles
        HashMap<String, Integer> battlesByRegions = new HashMap<>();

        for (Battle battle : battles) {

            String region = battle.getRegion().toString();

            if (battlesByRegions.containsKey(region)) {

                Integer value = battlesByRegions.get(region);
                battlesByRegions.put(region, value + 1);

            } else {

                battlesByRegions.put(region, 1);

            }

        }

        // Iterate hashmap and put results in chart
        Iterator it = battlesByRegions.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            // Add values to chart
            series.getData().add(new XYChart.Data(pair.getKey(), pair.getValue()));
            it.remove();
        }

        Scene scene = new Scene(lineChart, 800, 600);
        lineChart.getData().add(series);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch(args);
    }
}
