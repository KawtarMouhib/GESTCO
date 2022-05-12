package ma.ensao.gi3.gestCom.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;
import ma.ensao.gi3.gestCom.MysqlConnector;

public class SearchCommandController {

	@FXML
	private ComboBox<String> nomProduit;
	@FXML
	private ComboBox<String> status;
	@FXML
	private DatePicker dateDebut;
	@FXML
	private DatePicker dateFin;

	private static String ldDebut = null, ldFin = null;
	private static String comboProduit = null, comboStatus = null;

	//private Stage primaryStage;


	MysqlConnector con = new MysqlConnector();


	ResultSet rs = null, rs1 = null;
	PreparedStatement ps = null, ps1 = null;





	ArrayList<String> list= null;

	@FXML
	private void initialize(){


		try{
			Connection connectDB = con.getConnection();
			ps = connectDB.prepareStatement("SELECT NomProduit  FROM produit");
			if(ps != null)System.out.println("Connect� Prepared statement");
			rs = ps.executeQuery();
			while(rs.next())
			{
				String my_combo = rs.getString("NomProduit");
				nomProduit.getItems().add(my_combo);

			}
			status.getItems().addAll("Livr�", "En cours", "Annul�");

	}catch(Exception e){
		System.err.println(e);
	}


	}

	public void dateController(){
		ldDebut = dateDebut.getValue().toString();
		ldFin = dateFin.getValue().toString();

		System.out.println("la date obtenu est de: " + ldDebut +" et " + ldFin);

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

    /**
     * change screen to search commande
     */
        public void searchCommand(ActionEvent event) throws IOException{

        	dateController();
       		Parent tableViewParent = FXMLLoader.load(getClass().getResource("affiche_commande_du_client.fxml"));
       		Scene tableViewScene = new Scene(tableViewParent);

       		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

       		window.setScene(tableViewScene);
       		window.show();
       	}

        /***
         *  Getters et setters
         */

        public static String getLdDebut(){
        	return ldDebut;
        }

        public static String getLdFin(){
        	return ldFin;
        }

        public static String getComboProduit(){
        	return comboProduit;
        }

        public static String getComboStatus(){
        	return comboStatus;
        }

}