package ma.ensao.gi3.gestCom.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.ensao.gi3.gestCom.MysqlConnector;
public class modifierclient {

	@FXML
	private TextField nom;
	@FXML
	private TextField email;
	@FXML
	private TextField tel;
	@FXML
	private TextField adresse;

	MysqlConnector con=new MysqlConnector();

private String idd;
	PreparedStatement ps = null;

	public  void getid(String id) throws SQLException{

		this.idd=id;

	}
	public void change(ActionEvent event) throws Exception{


System.out.println(tel.getText());
		String query1 ="UPDATE client SET "
				+" NomClient = '" +nom.getText()+
				"', Tel = '"+ tel.getText() +
				"', Adresse = '" + adresse.getText()+
				"', Email = '" + email.getText() +
				"' WHERE IdClient='" + idd+"'";
		Connection connectDB = con.getConnection();
		ps = connectDB.prepareStatement(query1);

		 ps.executeUpdate();
		 /*,Tel='%s',Adresse='%s',NomClient='%s'" + "where IdClient='%d'",
				email.getText(),tel.getText(),adresse.getText(),nom.getText()*/


}

	public void backToMain(ActionEvent event) throws IOException{
		Parent tableViewParent = FXMLLoader.load(getClass().getResource("gestCo1.fxml"));
		Scene tableViewScene = new Scene(tableViewParent);

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(tableViewScene);
		window.show();
	}


}
