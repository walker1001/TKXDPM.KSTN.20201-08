package presentationlayer.box;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class NotificationBox {
    public static void display(String title, String message){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        Label label = new Label();
        label.setText(message);
        Label label1 = new Label();
        label1.setText("EcoBike System");
        Button button = new Button("OK");
        button.setOnAction(e -> {
            window.close();
        });

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label1, label, button);
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
//        return answer;
    }
}
