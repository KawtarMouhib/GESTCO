package ma.ensao.gi3.gestCom.view;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ensao.gi3.gestCom.MysqlConnector;

public class NewCommandController implements Initializable  {

	 	@FXML
	    private TextField tel;

	    @FXML
	    private TextField nom;

	    @FXML
	    private TextField email;

	    @FXML
	    private TextField adresse;

	    @FXML
	    private  ComboBox<String> nomProduit;

	    @FXML
	    private  ComboBox<String> status;

	    @FXML
	    private TextField quantite;

	    @FXML
	    private TextField prix;

	    @FXML
	    private DatePicker date;

	    MysqlConnector connect=new MysqlConnector();

		PreparedStatement ps;
		ResultSet rs;



	public NewCommandController() {

	}

	/*URL location, ResourceBundle resources*/

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

		try{
			Connection connectDB = connect.getConnection();
			ps = connectDB.prepareStatement("SELECT NomProduit  FROM produit");
			if(ps != null)System.out.println("Connect� Prepared statement");
			rs = ps.executeQuery();
			while(rs.next())
			{
				String my_combo = rs.getString("NomProduit");
				nomProduit.getItems().add(my_combo);

			}
			status.getItems().addAll("Livr�", "En cours", "Annul�");

			//ps.close();
            //rs.close();

	}catch(Exception e){
		System.err.println(e);
	}

	}

   @FXML
    void register(ActionEvent event) throws Exception {
		/*ps = con.obtenirConnexion().prepareStatement("insert into client (NomClient,Tel,Email,Adresse) values(?,?,?,?)");
		ps.setString(2, tel.getText());
		ps.setString(1, nom.getText());
		ps.setString(4, adresse.getText());
		ps.setString(3, email.getText());

		int rsp=ps.executeUpdate();**/
	   Connection connectDB = connect.getConnection();
		PreparedStatement state1= connectDB.prepareStatement("INSERT INTO commande (DateCommande, IdProduit, IdClient, Prix, Quantite, Status) values(?,?,?,?,?,?)");
		state1.setString(5, quantite.getText());

		// recherche de l'id Produit
		String produit = nomProduit.getSelectionModel().getSelectedItem().toString();
		PreparedStatement state2= connectDB.prepareStatement("SELECT * FROM produit where NomProduit=?");
		state2.setString(1, produit);
		ResultSet result=state2.executeQuery();
		int idProduit=0 ;
		while(result.next()) {
			idProduit = result.getInt("IdProduit");
		}

		//recherche de l'id Client
		String nomClient = tel.getText();
		PreparedStatement state3= connectDB.prepareStatement("SELECT * FROM client where Tel=?");
		state3.setString(1, nomClient);
		ResultSet result3=state3.executeQuery();
		int idClient = 0;
		while(result3.next()) {
			idClient=result3.getInt("IdClient");
		}

		state1.setInt(2,idProduit);
		state1.setInt(3, idClient);
		state1.setInt(4, Integer.parseInt(prix.getText()) * Integer.parseInt(quantite.getText()));
		state1.setString(1,date.getValue().toString());
		state1.setString(6,status.getValue().toString());

		int r1=state1.executeUpdate();
	if((r1==1)) {
			JOptionPane.showMessageDialog(null,"succes");

	    	Parent login = FXMLLoader.load(getClass().getResource("gestCo1.fxml"));
    		Scene loginScene = new Scene(login);

    		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    		window.setScene(loginScene);
    		window.show();
			}else {
			JOptionPane.showMessageDialog(null,"not succes");
		}
    }

	public void retour(ActionEvent event) throws IOException{

		Parent tableViewParent = FXMLLoader.load(getClass().getResource("gestCo1.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}

	/*public void vider(ActionEvent event) {
		status.setValue("");
		prix.setText("");
		quantite.setText("");
	}*/

	@FXML
    void select(ActionEvent event) throws Exception {
		Connection connectDB = connect.getConnection();
		ps = connectDB.prepareStatement("insert into client (NomClient,Tel,Email,Adresse) values(?,?,?,?)");
		ps.setString(2, tel.getText());
		ps.setString(1, nom.getText());
		ps.setString(4, adresse.getText());
		ps.setString(3, email.getText());
		int rsp=ps.executeUpdate();
		if (rsp==1)System.out.println("valide");



		String produit = nomProduit.getSelectionModel().getSelectedItem().toString();
		PreparedStatement state2= connectDB.prepareStatement("select * from produit where NomProduit=?");
		state2.setString(1, produit);
		ResultSet result=state2.executeQuery();
	    Integer prix1=null;
		while(result.next()) {
			prix1=result.getInt("PrixUnitaire");
		}
		prix.setText(prix1.toString());
    }
}



