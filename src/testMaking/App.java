/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*
https://teratail.com/questions/15561
*/
package testMaking;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 500, 200);

        HBox hbox = new HBox();
        hbox.setAlignment(Pos.CENTER);

        Button button = new Button("ボタン");
        Label label = new Label("ラベル");
        TextField textField = new TextField();

        button.setOnAction((event) -> {
            label.setText("");
            Event.fireEvent(textField, new KeyEvent(KeyEvent.KEY_PRESSED,
                    KeyEvent.CHAR_UNDEFINED, "",
                    KeyCode.DOWN, false, false, false, false));
            Event.fireEvent(textField, new KeyEvent(KeyEvent.KEY_PRESSED,
                    KeyEvent.CHAR_UNDEFINED, "\n",
                    KeyCode.ENTER, false, false, false, false));
        });
        textField.setOnKeyPressed((event) -> {
            label.setText(label.getText() + "\nKey Pressed: " + event.getCode());
        });

        hbox.getChildren().add(button);
        hbox.getChildren().add(textField);
        hbox.getChildren().add(label);

        ((Group) scene.getRoot()).getChildren().add(hbox);

        primaryStage.setTitle("Button Test");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}