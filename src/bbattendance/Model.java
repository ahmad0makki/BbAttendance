package bbattendance;

import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Model extends Application {
    
    private static Stage mainStage;
    
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Scene.fxml"));
        
        mainStage = stage;
        Scene scene = new Scene(root);
        
        stage.setTitle("Blackboard Attendance Marker");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void go(String sectionFile, String sessionFile, double minMinutes) {
        // Builds the output file name
        String outFile=sectionFile.substring(0, sectionFile.length() - 4) +
          " Attendance.xls";
        
        // Tries to generate an output
        try {
            Output.generate(Input.inSection(sectionFile), Input.inSession(sectionFile, minMinutes));
            Controller.success();
        } catch (Exception e) {
            Controller.fail(e.toString());
        }
    }
    
    // Opens a file chooser dialog and returns a file's path
    public static String chooseFilePath(String title) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        
        fileChooser.getExtensionFilters().addAll(
            new FileChooser.ExtensionFilter("Excel/CSV", "*.xlsx", "*.xls", "*.csv"),
            new FileChooser.ExtensionFilter("Text Files", "*.txt"),
            new FileChooser.ExtensionFilter("All Files", "*.*")
        );
        
        String s = ((File) fileChooser.showOpenDialog(mainStage)).toString();
        
        System.out.println(s);
        
        return s;
    }
}
