package ma.ensao.gi3.gestCom.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class chercherclient {
	@FXML
	private TextField id;

	public void change(ActionEvent event) throws IOException{

		FXMLLoader loader = new FXMLLoader(getClass().getResource("afficheclient.fxml"));
        Parent root = loader.load();
        afficheclientcontrol scene2Controller = loader.getController();
        //passer id a lautre controlleur !!
        scene2Controller.show(id.getText());

	    Scene tableViewScen = new Scene(root);

	    //This line gets the Stage information
	    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

	    window.setScene(tableViewScen);
	    window.show();


	}
	//FXMLLoader loader = new FXMLLoader();


	public void backToMain(ActionEvent event) throws IOException{
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("gestCo1.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}

}
