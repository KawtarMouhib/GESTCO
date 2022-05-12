package ma.ensao.gi3.gestCom.view;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainPageController {


	 /**
     * change screen to new command
     */
    	public void changeScreenToNewCom(ActionEvent event) throws IOException{
    		Parent tableViewParent = FXMLLoader.load(getClass().getResource("nouvelle_commande.fxml"));
    		Scene tableViewScene = new Scene(tableViewParent);

    		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    		window.setScene(tableViewScene);
    		window.show();
    	}


    	 /**
         * change screen to search commande
         */
        	public void changeScreenToSearchCom(ActionEvent event) throws IOException{
        		Parent tableViewParent = FXMLLoader.load(getClass().getResource("chercher_commande1.fxml"));
        		Scene tableViewScene = new Scene(tableViewParent);

        		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        		window.setScene(tableViewScene);
        		window.show();
        	}

        	 /**
             * change screen to search client
             */
            	public void changeScreenToSearchClient(ActionEvent event) throws IOException{
            		Parent tableViewParent = FXMLLoader.load(getClass().getResource("chercherclient.fxml"));
            		Scene tableViewScene = new Scene(tableViewParent);

            		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            		window.setScene(tableViewScene);
            		window.show();
            	}

            	/**
                 * Sets the stage of this dialog.
                 *
                 * @param dialogStage
                 */
                /*public void setDialogStage(Stage dialogStage) {
                    this.dialogStage = dialogStage;
                }*/


}
