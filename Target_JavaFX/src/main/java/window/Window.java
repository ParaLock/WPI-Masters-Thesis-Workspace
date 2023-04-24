
package window;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.Stage;

public class Window extends Application {

    @Override
    public void init() throws Exception {
        super.init();
        System.out.println("Inside init() method! Perform necessary initializations here.");
    }

    @Override
    public void start(Stage primaryStage) {

        GridPane gridPane = new GridPane();

        // Set up column and row constraints to distribute evenly
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setPercentWidth(100.0 / 3);
        RowConstraints rowConstraints = new RowConstraints();
        rowConstraints.setPercentHeight(100.0 / 3);

        for (int i = 0; i < 3; i++) {
            gridPane.getColumnConstraints().add(colConstraints);
            gridPane.getRowConstraints().add(rowConstraints);
            for (int j = 0; j < 3; j++) {
                Label label = new Label("Label " + (i * 3 + j + 1));
                label.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                label.setAlignment(Pos.CENTER);
                GridPane.setConstraints(label, j, i);
                gridPane.getChildren().add(label);
            }
        }

        Scene scene = new Scene(gridPane, 300, 300);
        primaryStage.setTitle("Grid Application");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        System.out.println("Inside stop() method! Destroy resources. Perform Cleanup.");
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}