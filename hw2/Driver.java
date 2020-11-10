package hw2;
/*
Driver for the program, opens the admin control panel which is a singleton
 */
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;

public class Driver extends Application{

    public static void main(String[] args)
    {
        // Launch the application.
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        AdminPanel adminPanel = AdminPanel.getInstance();
        HBox adminBox = adminPanel.getAdminPanel();

        //set up scene and stage to show
        Scene scene = new Scene(adminBox, 560, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Admin Control Panel");
        primaryStage.show();
    }
}
