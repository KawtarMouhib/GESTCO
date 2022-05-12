package ma.ensao.gi3.gestCom.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SearchClientController {

	 /**
     * change screen to search commande
     */
    	public void backToMain(ActionEvent event) throws IOException{
    		Parent tableViewParent = FXMLLoader.load(getClass().getResource("gestCo.fxml"));
    		Scene tableViewScene = new Scene(tableViewParent);

    		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    		window.setScene(tableViewScene);
    		window.show();
    	}


    	/**
         * change screen to display client research
         */
        	public void searchClient(ActionEvent event) throws IOException{
        		Parent tableViewParent = FXMLLoader.load(getClass().getResource("affiche_client_rechercher.fxml"));
        		Scene tableViewScene = new Scene(tableViewParent);

        		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        		window.setScene(tableViewScene);
        		window.show();
        	}


}
