package boundary;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainBoundary extends Application{
	
	private Map<String, BoundaryScreen> telas = new HashMap<>();

	
	@Override
	public void start(Stage stage) throws Exception {
		telas.put("animal", new AnimalBoundary() );
		telas.put("habitat", new HabitatBoundary()  );
		BorderPane borderPane = new BorderPane();
        
        MenuBar menuBar = new MenuBar();
        Menu menuCadastro = new Menu("Cadastro");

        MenuItem itemMenuAnimal = new MenuItem("Animal");
        itemMenuAnimal.setOnAction ( e -> 
            borderPane.setCenter( new AnimalBoundary().render() )
        );
        
        MenuItem itemMenuHabitat = new MenuItem("Habitat");
        itemMenuHabitat.setOnAction( e-> 
            borderPane.setCenter( new HabitatBoundary().render() )
        );
        
        menuCadastro.getItems().addAll( itemMenuAnimal, itemMenuHabitat );
        menuBar.getMenus().addAll( menuCadastro );
        borderPane.setTop( menuBar );
        
        Scene scn = new Scene(borderPane,910,512);
        stage.setScene(scn);
        stage.setTitle("Animals");
        stage.show();
	}
	
	public static void main(String[] args) {
		Application.launch(MainBoundary.class, args);
	}	
}
