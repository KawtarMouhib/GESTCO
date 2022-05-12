package ma.ensao.gi3.gestCom.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ma.ensao.gi3.gestCom.MainApp;
import ma.ensao.gi3.gestCom.MysqlConnector;
import ma.ensao.gi3.gestCom.model.Command;

public class DisplayCommandController  {

	@FXML
	private TableView<Command> tableCommand;
	@FXML
	private TableColumn<Command, Integer> num;
	@FXML
	private TableColumn<Command, String> date;
	@FXML
	private TableColumn<Command, String> client;
	@FXML
	private TableColumn<Command, String> adresse;
	@FXML
	private TableColumn<Command, String> produit;
	@FXML
	private TableColumn<Command, Integer> quantite;
	@FXML
	private TableColumn<Command, Integer> prix;
	@FXML
	private TableColumn<Command, String> status;

	//private MainApp mainApp;
	private Stage primaryStage, dialogStage;

	private CommandEditDialogController commandEditController;




	MysqlConnector con = new MysqlConnector();

	ResultSet rs = null;
	PreparedStatement ps = null;


	String var1 ;
	String var2;

	ObservableList<Command> list= FXCollections.observableArrayList();

	public DisplayCommandController(){
	}

	@FXML
	private void initialize(){
		String ldDebut = SearchCommandController.getLdDebut();
		String ldFin = SearchCommandController.getLdFin();
		var1 = ldDebut;
		var2 = ldFin;
		System.out.println(var1 + "et var2 =" + var2);

		try{
			Connection connectDB = con.getConnection();
			ps = connectDB.prepareStatement("SELECT com.numCommande,"
					+ "com.DateCommande, cli.NomClient,cli.Adresse, pro.NomProduit,"
					+ "com.Quantite, com.Prix,com.Status FROM commande com, produit pro"
					+ ",client cli WHERE (pro.IdProduit=com.IdProduit) AND "
					+ "(cli.IdClient=com.IdClient) AND (com.DateCommande "
					+ "BETWEEN '"+var1+"' AND '" +var2+ "')");
			if(ps != null)System.out.println("Connect� Prepared statement");
			rs = ps.executeQuery();

			while(rs.next())
			{
				list.add(new Command(rs.getInt("numCommande"), rs.getString("DateCommande"),
						rs.getString("NomClient"), rs.getString("Adresse"), rs.getString("NomProduit"),rs.getInt("Quantite"),rs.getInt("Prix"),rs.getString("Status")));
			}
	}catch(Exception e){
		System.err.println(e);
	}

		num.setCellValueFactory(new PropertyValueFactory<>("idCommand"));
		date.setCellValueFactory(new PropertyValueFactory<>("dateCommand"));
		client.setCellValueFactory(new PropertyValueFactory<>("nomClient"));
		adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
		produit.setCellValueFactory(new PropertyValueFactory<>("nomProduit"));
		prix.setCellValueFactory(new PropertyValueFactory<>("prix"));
		quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
		status.setCellValueFactory(new PropertyValueFactory<>("status"));

		tableCommand.setItems(list);

	}


	/**
	 * Called when the user clicks on the delete button.
	 */
	@FXML
	private void handleDeleteCommand() {
		int selectedIndex = tableCommand.getSelectionModel().getSelectedIndex();
		Command selectedCommand = tableCommand.getSelectionModel().getSelectedItem();
	    if (selectedIndex >= 0) {

	    	 try{
				 Connection connectDB = con.getConnection();
	 			ps = connectDB.prepareStatement("DELETE FROM commande WHERE numCommande ='" +selectedCommand.getIdCommand() +"'");
	 			ps.execute();
	 			if(ps != null)System.out.println("Connect� Prepared statement");
	 			refreshTable();

	 	}catch(Exception e){
	 		System.err.println(e);
	 		JOptionPane.showMessageDialog(null, e);
	 	}

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        //alert.initOwner(mainApp.getPrimaryStage());
	        alert.initOwner(dialogStage);
	        alert.setTitle("No Selection");
	        alert.setHeaderText("Aucune Commande selectionn�e");
	        alert.setContentText("Veuiller selectionn� une commande dans la table pour effectuer la supression");

	        alert.showAndWait();
	    }
	    // � revoir pour supprimer une  commande

	    tableCommand.getItems().remove(selectedIndex);

	}

	/**
	 * Called when the user clicks the edit button. Opens a dialog to edit
	 * details for the selected command.
	 */
	@FXML
	private void handleEditCommand() {
		try{
	    Command selectedCommand = tableCommand.getSelectionModel().getSelectedItem();
	    if (selectedCommand != null) {
	        //boolean okClicked = mainApp.showCommandEditDialog(selectedCommand);
	    	boolean okClicked = showCommandEditDialog(selectedCommand);
	        if (okClicked) {
	            //showPersonDetails(selectedCommand);
	        	commandEditController.showCommandDetails(selectedCommand);
	        }

	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.initOwner(dialogStage);
	        alert.setTitle("No Selection");
	        alert.setHeaderText("Aucune Commande selectionn�e");
	        alert.setContentText("Veuiller selectionn� une commande dans la table pour effectuer la modification");

	        alert.showAndWait();
	    }

		}catch(NullPointerException e)
		{
			//System.err.print(e);
		};
	  }



	/**
	 * Opens a dialog to edit details for the specified Command. If the user
	 * clicks OK, the changes are saved into the provided command object and true
	 * is returned.
	 *
	 * @param Command the command object to be edited
	 * @return true if the user clicked OK, false otherwise.
	 */
	public boolean showCommandEditDialog(Command command) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(MainApp.class.getResource("view/CommandEditDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();

	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Modifier Commande");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);

	        // Set the command into the controller.
	        CommandEditDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setCommand(command);

	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	    }



	@FXML
    private void refreshTable() {
        try {
	        	list.clear();
			Connection connectDB = con.getConnection();
	            ps = connectDB.prepareStatement("SELECT com.numCommande,"
						+ "com.DateCommande, cli.NomClient,cli.Adresse, pro.NomProduit,"
						+ "com.Quantite, com.Prix,com.Status FROM commande com, produit pro"
						+ ",client cli WHERE (pro.IdProduit=com.IdProduit) AND "
						+ "(cli.IdClient=com.IdClient) AND (com.DateCommande "
						+ "BETWEEN '"+ var1 +"' AND '" + var2 + "')");
	            rs = ps.executeQuery();

	            while (rs.next()){
	                list.add(new Command(rs.getInt("numCommande"),
	                		rs.getString("DateCommande"),
							rs.getString("NomClient"), rs.getString("Adresse"),
							rs.getString("NomProduit"),rs.getInt("Quantite"),
							rs.getInt("Prix"),rs.getString("Status")));
	                tableCommand.setItems(list);
            }


        } catch (Exception ex) {
            Logger.getLogger(DisplayCommandController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }



	 /**
     * change screen to search commande
     */
    	public void backToMain(ActionEvent event) throws IOException{
    		Parent tableViewParent = FXMLLoader.load(getClass().getResource("gestCo1.fxml"));
    		Scene tableViewScene = new Scene(tableViewParent);

    		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    		window.setScene(tableViewScene);
    		window.show();
    	}


}
