package ma.ensao.gi3.gestCom.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ensao.gi3.gestCom.MysqlConnector;
import ma.ensao.gi3.gestCom.model.Command;

public class CommandEditDialogController {

	  	@FXML
	    private TextField num;
	    @FXML
	    private TextField date;
	    @FXML
	    private TextField client;
	    @FXML
	    private ComboBox<String> produit;
	    @FXML
	    private TextField quantite;
	    @FXML
	    private TextField prix;
	    @FXML
	    private TextField prixTotal;
	    @FXML
	    private ComboBox<String> status;

	    private Stage dialogStage;
	    String query;
	    private Command command;
	    private boolean okClicked = false;
	    //private boolean update;

	    MysqlConnector con = new MysqlConnector();

		ResultSet rs = null, rs1 = null;
		PreparedStatement ps = null, ps1 = null;
		int idCommande;

	/**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	    @FXML
	    private void initialize() {

	    	try{
				Connection connectDB = con.getConnection();
				ps = connectDB.prepareStatement("SELECT NomProduit FROM produit ");
				if(ps != null)System.out.println("Connect� Prepared statement");
				rs = ps.executeQuery();
				while(rs.next())
				{
					String my_combo = rs.getString("NomProduit");
					produit.getItems().add(my_combo);

				}
				status.getItems().addAll("Livr�", "En cours", "Annul�");

		}catch(Exception e){
			System.err.println(e);
		}
	    }

	    /**
	     * Sets the stage of this dialog.
	     *
	     * @param dialogStage
	     */
	    public void setDialogStage(Stage dialogStage) {
	        this.dialogStage = dialogStage;
	    }

	    /**
	     * Sets the command to be edited in the dialog.
	     *
	     * @param command
	     */
	    public void setCommand(Command command) {
	        this.command = command;

	        num.setText(Integer.toString(command.getIdCommand()));
	        date.setText(command.getDateCommand());
	        client.setText(command.getNomClient());
	        produit.setValue(command.getNomProduit());
	        quantite.setText(Integer.toString(command.getQuantite()));
	        prix.setText(Integer.toString(command.getPrix()));
	        prixTotal.setText(Integer.toString(command.getPrix()));
	        status.setValue(command.getStatus());

	    }



	    /**
	     * Returns true if the user clicked OK, false otherwise.
	     *
	     * @return
	     */
	    public boolean isOkClicked() {
	        return okClicked;
	    }

	    /**
	     * Called when the user clicks ok.
	     */
	    @FXML
	    private void handleOk(ActionEvent event) throws IOException{
	        if (isInputValid()) {

	        	 /*connection = DbConnect.getConnect();
	             String name = nameFld.getText();
	             String birth = String.valueOf(birthFld.getValue());
	             String adress = adressFld.getText();
	             String email = emailFld.getText();
	             int*/


	            command.setIdCommand(Integer.parseInt(num.getText()));
	            command.setDateCommand(date.getText());
	            command.setNomClient(client.getText());
	            command.setNomProduit(produit.getValue().toString());
	            command.setQuantite(Integer.parseInt(quantite.getText()));
	            command.setPrix(Integer.parseInt(prix.getText()));
	            command.setPrix(Integer.parseInt(prixTotal.getText()));
	            command.setStatus(status.getValue());

	            System.out.println("IdProduit : " + command.getNomProduit());

	            /*try{
	    			ps = con.obtenirConnexion().prepareStatement("UPDATE commande SET"
	    					+ " numCommande= '" +num.getText()+
	    						"', DateCommande = '" +date.getText()+
	    						"',IdProduit ='" + "(SELECT IdProduit FROM produit WHERE NomProduit= '" +produit.getValue()+
	    						"')', Quantite = " + quantite.getText()+
	    						",Status = " + status.getValue() +
	    						"WHERE numCommande='" +num.getText()+"'");
	    			if(ps != null)System.out.println("Connect� Prepared statement");*/

	        	try{
	        		int value=0;
					Connection connectDB = con.getConnection();
	        		ps1= connectDB.prepareStatement("SELECT * FROM "
	        				+ "produit WHERE NomProduit='" + command.getNomProduit() +"'");
	        		rs= ps1.executeQuery();
	        		while(rs.next())
	    			{
	    				value = rs.getInt("IdProduit");
	    			}

	        		System.out.println("IdProduit : " + value);

	        		ps = connectDB.prepareStatement("UPDATE commande SET "
	        					+" DateCommande = '" +command.getDateCommand()+
	    						"', IdProduit = '"+ value +
	    						"', Quantite = '" + command.getQuantite()+
	    						"', Status = '" + command.getStatus() +
	    						"' WHERE numCommande=" + command.getIdCommand() );
	    			if(ps != null)System.out.println("Connect� Prepared statement");
	    			ps.execute();
	    	}catch(Exception e){
	    		System.err.println(e);
	    		JOptionPane.showMessageDialog(null, e);
	    		Logger.getLogger(CommandEditDialogController.class.getName()).log(Level.SEVERE, null, e);

	    	}
	            //JOptionPane.showMessageDialog(null, "Mise � jour effect� avec succ�s");

	            okClicked = true;
	            dialogStage.close();

	    }
	    }


	    /*private void getQuery() {

	        if (update == false) {

	            query = "INSERT INTO `student`( `name`, `birth`, `adress`, `email`) VALUES (?,?,?,?)";

	        }else{
	            query = "UPDATE `commande` SET "
	                    + "`DateCommande`=?,"
	                    + "`IdProduit`=?,"
	                    + "`Quantite`=?,"
	                    + "`status`= ? WHERE id =? ";
	        }

	    }*/



	    /**
		 * Fills all text fields to show details about the person.
		 * If the specified person is null, all text fields are cleared.
		 *
		 * @param command the command or null
		 */
		public void showCommandDetails(Command command) {
		    if (command != null) {
		        // Fill the labels with info from the person object.
		    	num.setText(Integer.toString(command.getIdCommand()));
		        date.setText(command.getDateCommand());
		        client.setText(command.getNomClient());
		        produit.setValue(command.getNomProduit());
		        quantite.setText(Integer.toString(command.getQuantite()));
		        prix.setText(Integer.toString(command.getPrix()));
		        prixTotal.setText(Integer.toString(command.getPrix()));
		        status.setValue(command.getStatus());

		    } else {
		        // command is null, remove all the text.
		        num.setText("");
		        date.setText("");
		        client.setText("");
		        produit.setValue("");
		        quantite.setText("");
		        prix.setText("");
		        prixTotal.setText("");
		        status.setValue("");
		    }
		}

	    /**
	     * Called when the user clicks cancel.
	     */
	    @FXML
	    private void handleCancel() {
	        dialogStage.close();
	    }

	    /**
	     * Validates the user input in the text fields.
	     *
	     * @return true if the input is valid
	     */
	    private boolean isInputValid() {
	        String errorMessage = "";

	        if (num.getText() == null || num.getText().length() == 0) {
	            errorMessage += "No valid Id Command!\n";
	        }
	        if (date.getText() == null || date.getText().length() == 0) {
	            errorMessage += "No valid Date Commande!\n";
	        }
	        if (client.getText() == null || client.getText().length() == 0) {
	            errorMessage += "No valid Name Client!\n";
	        }

	        if (produit.getValue() == null || produit.getValue().toString().length() == 0) {
	            errorMessage += "No valid Name Produit!\n";
	        }
	        if (quantite.getText() == null || quantite.getText().length() == 0) {
	            errorMessage += "No valid Quantite!\n";
	        }
	        if (prix.getText() == null || prix.getText().length() == 0) {
	            errorMessage += "No valid Price!\n";
	        }
	        if (prixTotal.getText() == null || prixTotal.getText().length() == 0) {
	            errorMessage += "No valid Total Price!\n";
	        }
	        if (status.getValue() == null || status.getValue().length() == 0) {
	            errorMessage += "No valid Status!\n";
	        }

	        if (errorMessage.length() == 0) {
	            return true;
	        } else {
	            // Show the error message.
	            Alert alert = new Alert(AlertType.ERROR);
	            alert.initOwner(dialogStage);
	            alert.setTitle("Invalid Fields");
	            alert.setHeaderText("Please correct invalid fields");
	            alert.setContentText(errorMessage);

	            alert.showAndWait();

	            return false;
	        }
	    }

}
