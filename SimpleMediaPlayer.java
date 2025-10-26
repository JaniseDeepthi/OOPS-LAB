package javafx;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class SimpleMediaPlayer extends Application {

    // Replace with your real name / registration number if you want
    private static final String STUDENT_NAME = "Janise Deepthi YP";
    private static final String REG_NO = "2117240070124";

    private MediaPlayer mediaPlayer;
    private Label statusLabel;
    private Label testCasesLabel;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Simple JavaFX Media Player");

        Button loadBtn = new Button("Load Media");
        Button playBtn = new Button("Play");
        Button pauseBtn = new Button("Pause");
        Button stopBtn = new Button("Stop");

        statusLabel = new Label("No file loaded.");
        testCasesLabel = new Label(); // will show printed test cases / student info in UI

        // Prepare buttons
        playBtn.setDisable(true);
        pauseBtn.setDisable(true);
        stopBtn.setDisable(true);

        // File chooser to load media
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Media File");
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Audio/Video", "*.mp3", "*.mp4", "*.m4a", "*.wav", "*.aac")
        );

        loadBtn.setOnAction(e -> {
            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                loadMedia(file);
                printTestCasesToConsoleAndUI();  // print test cases after loading (or at start)
            }
        });

        playBtn.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.play();
                statusLabel.setText("Playing");
                printTestCaseResult("TC1: Play -> Media starts");
            }
        });

        pauseBtn.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
                statusLabel.setText("Paused");
                printTestCaseResult("TC2: Pause -> Media pauses");
            }
        });

        stopBtn.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
                statusLabel.setText("Stopped (reset)");
                printTestCaseResult("TC3: Stop -> Media stops and resets");
            }
        });

        // Layout
        HBox controls = new HBox(10, loadBtn, playBtn, pauseBtn, stopBtn);
        controls.setAlignment(Pos.CENTER);

        VBox root = new VBox(12, statusLabel, controls, testCasesLabel);
        root.setPadding(new Insets(16));
        root.setAlignment(Pos.CENTER);

        Scene scene = new Scene(root, 600, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

        // initialise test cases display even if no file loaded
        printTestCasesToConsoleAndUI();
        
        // update button enable/disable based on mediaPlayer lifecycle
        // (we will enable after loadMedia)
    }

    private void loadMedia(File file) {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.dispose();
            }
            Media media = new Media(file.toURI().toString());
            mediaPlayer = new MediaPlayer(media);

            // Enable buttons now
            setButtonsEnabled(true);

            // When media finishes, update status
            mediaPlayer.setOnEndOfMedia(() -> statusLabel.setText("Playback finished"));

            statusLabel.setText("Loaded: " + file.getName());
            printTestCaseResult("File loaded: " + file.getName());
        } catch (Exception ex) {
            statusLabel.setText("Error loading media: " + ex.getMessage());
            printTestCaseResult("TC4/TC5: Error (no file / unsupported format) -> " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            setButtonsEnabled(false);
            ex.printStackTrace();
        }
    }

    private void setButtonsEnabled(boolean enabled) {
        // find buttons from scene graph (simple approach)
        Scene scene = statusLabel.getScene();
        if (scene == null) return;
        HBox controls = (HBox) ((VBox) scene.getRoot()).getChildren().get(1);
        controls.getChildren().forEach(node -> node.setDisable(!enabled && node != controls.getChildren().get(0))); 
        // keep Load enabled
    }

    private void printTestCasesToConsoleAndUI() {
        List<String> testCases = new ArrayList<>();
        testCases.add("Student Name: " + STUDENT_NAME);
        testCases.add("Registration No: " + REG_NO);
        testCases.add("");
        testCases.add("Test Cases:");
        testCases.add("TC1: Click Play → Output: Media starts");
        testCases.add("TC2: Click Pause → Output: Media pauses");
        testCases.add("TC3: Click Stop → Output: Media stops and resets");
        testCases.add("TC4: No file loaded → Output: Error message");
        testCases.add("TC5: Play file in unsupported format → Output: MediaException");

        // Print to console
        System.out.println("==== Media Player Test Cases ====");
        for (String line : testCases) {
            System.out.println(line);
        }

        // Display in UI label (join into a smaller text)
        StringBuilder sb = new StringBuilder();
        sb.append(testCases.get(0)).append(" | ").append(testCases.get(1)).append("\n");
        sb.append("TC1: Play | TC2: Pause | TC3: Stop | TC4: No file | TC5: Unsupported format");
        testCasesLabel.setText(sb.toString());
    }

    private void printTestCaseResult(String message) {
        // also log to console so you can see sequence
        System.out.println(message);
    }
}

