import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class UserGUI extends Application {
    private boolean isPause = false;
    private StringProperty msg = new SimpleStringProperty("null");
    private AlarmThread alarmThread = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("clock");
        Button btnStart = new Button("start");
        Button btnPause = new Button("pause");

        btnStart.setOnAction(event -> {
            System.out.println("start");
            ((Button)event.getSource()).setOnAction(null);
            alarmThread = new AlarmThread(20, this);
            alarmThread.start();
        });

        btnPause.setOnAction(event -> {
            if (isPause) {
                isPause = false;
                System.out.println("resume~");
                alarmThread.onResume();
            } else {
                isPause = true;
                System.out.println("pause~");
                alarmThread.onPause();
            }
        });

        TextField tf = new TextField();
        tf.textProperty().bind(msg);
        VBox root = new VBox();
        root.setAlignment(Pos.CENTER);
        root.getChildren().add(btnStart);
        root.getChildren().add(btnPause);
        root.getChildren().add(tf);
        primaryStage.setScene(new Scene(root, 500, 300));
        primaryStage.show();
    }

    public void setMsgValue(String str) {
        msg.setValue(str);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
