package ma.ensao.gi3.gestCom.view;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.ensao.gi3.gestCom.MysqlConnector;
import ma.ensao.gi3.gestCom.model.Person;
public class afficheclientcontrol  {




@FXML
private TableView<Person> tableusers;


@FXML
private TableColumn<Person,String> nom;
@FXML
private TableColumn<Person,String> email;
@FXML
private TableColumn<Person,String> adres;
@FXML
private TableColumn<Person,String> identifiant;
@FXML
private TableColumn<Person,String> tele;

MysqlConnector con=new MysqlConnector();


ResultSet rs;
PreparedStatement pst;
ObservableList<Person> list=FXCollections.observableArrayList();

public void modifier(ActionEvent event) throws IOException, SQLException{
	Person client = tableusers.getSelectionModel().getSelectedItem();
	FXMLLoader loader = new FXMLLoader(getClass().getResource("modifclient.fxml"));
    Parent root = loader.load();
    modifierclient scene2Controller = loader.getController();
    //passer id a lautre controlleur !!
    scene2Controller.getid(client.getIdclient());

    Scene tableViewScen = new Scene(root);

    //This line gets the Stage information
    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

    window.setScene(tableViewScen);
    window.show();


}


public  void show(String id){

	//Connection con=mysqlconnect.getConnection();

System.out.println(id);
    PreparedStatement ps;
	try {
		String query1 =String.format ("select * from client where IdClient='%s' " ,id);
		Connection connectDB = con.getConnection();
		ps = connectDB.prepareStatement(query1);
		 rs= ps.executeQuery();
		 while(rs.next()){

			 list.add(new Person(rs.getString("NomClient"),rs.getString("Email"),rs.getString("Adresse"),rs.getString("Tel"),rs.getString("IdClient"))) ; }
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	nom.setCellValueFactory(new PropertyValueFactory<>("nomm"));
    email.setCellValueFactory(new PropertyValueFactory<>("emaill"));
    adres.setCellValueFactory(new PropertyValueFactory<>("addresse"));
    tele.setCellValueFactory(new PropertyValueFactory<>("tel"));
    identifiant.setCellValueFactory(new PropertyValueFactory<>("idclient"));



    tableusers.setItems(list);


}


public void backToMain(ActionEvent event) throws IOException{
	Parent tableViewParent = FXMLLoader.load(getClass().getResource("gestCo1.fxml"));
	Scene tableViewScene = new Scene(tableViewParent);

	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

	window.setScene(tableViewScene);
	window.show();
}









}
