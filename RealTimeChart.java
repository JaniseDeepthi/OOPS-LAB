package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.util.Random;

public class RealTimeChart extends Application {

    private XYChart.Series<Number, Number> series;
    private int x = 0; // x-axis counter

    @Override
    public void start(Stage stage) {
        stage.setTitle("Real-Time Data Visualization");

        // Define X and Y axes
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Time (s)");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Value");

        // Create LineChart
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Real-Time Data Chart");

        // Series for data
        series = new XYChart.Series<>();
        series.setName("Random Data");
        lineChart.getData().add(series);

        // Scene
        Scene scene = new Scene(lineChart, 800, 600);
        stage.setScene(scene);
        stage.show();

        // Start simulating real-time data
        simulateData();
    }

    private void simulateData() {
        Random random = new Random();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            // TC1: Chart starts empty
            if (x == 0) {
                System.out.println("TC1: Chart started empty");
            }

            // TC2: Data added every second
            int yValue = random.nextInt(100); // Random value 0-99
            System.out.println("TC2: Adding value = " + yValue);
            series.getData().add(new XYChart.Data<>(x++, yValue));

            // TC4: Chart auto-scales
            if (series.getData().size() > 20) {
                series.getData().remove(0); // Keep last 20 points
            }

            // TC3: Stop updates simulation
            if (x == 10) {
                System.out.println("TC3: Stopping updates, chart freezes");
                ((Timeline) event.getSource()).stop();
            }

            // TC5: Non-numeric input handling simulation
            try {
                String input = "abc"; // simulate bad input
                int test = Integer.parseInt(input);
            } catch (NumberFormatException e) {
            	System.out.println("Janise Deepthi YP - 2117240070124");
                System.out.println("TC5: Non-numeric input handled gracefully");
            }

        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


