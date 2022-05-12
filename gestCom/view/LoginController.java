package ma.ensao.gi3.gestCom.view;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class LoginController {

    Stage dialogStage;


	@FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Label message;

    public void login(ActionEvent event) throws IOException{

         String uname=username.getText();
         String pass=password.getText();

    	    	/*try {
					Class.forName("com.mysql.jdbc.Driver");
					Connection conn=DriverManager.getConnection("jdbc:mysql://LocalHost/gestionnaire","root","");
					PreparedStatement state=conn.prepareStatement("select *from chef where name=? and password=?");
					state.setString(1, uname);
					state.setString(2, pass);
					ResultSet rs=state.executeQuery();
				    if(rs.next()) {
				    	Parent login = FXMLLoader.load(getClass().getResource("inteface1.fxml"));
			    		Scene loginScene = new Scene(login);

			    		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

			    		window.setScene(loginScene);
			    		window.show();
				    } else {
				    	message.setText("Wrong username or password");
				    	username.setText("");
				    	password.setText("");
				    }

				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    }
    public void register(ActionEvent event) throws IOException {

    	Parent login = FXMLLoader.load(getClass().getResource("enregistrement.fxml"));
		Scene loginScene = new Scene(login);

		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

		window.setScene(loginScene);
		window.show();
    }*/


    }


    /**
     * change screen
     */
    public void changeScreen(ActionEvent event) throws IOException{
    	//if (username.getText().toString().equals("aaa") && password.getText().toString().equals("000"))
    	//{
    		Parent tableViewParent = FXMLLoader.load(getClass().getResource("gestCo1.fxml"));
        	Scene tableViewScene = new Scene(tableViewParent);

        	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        	window.setScene(tableViewScene);
        	window.show();
    	/*}else if (username.getText().toString() != "aaa" || password.getText().toString() != "000")
    		{
    			String errorMessage = "";
    	        if (username.getText() == null || username.getText().length() == 0) {
    	            errorMessage += "Nom invalide!\n";
    	        }
    	        if (password.getText() == null || password.getText().length() == 0) {
    	            errorMessage += "Mot de passe invalide!\n";
    	        }

    	        if(errorMessage!="") {
    	           // Show the error message.
    	            Alert alert = new Alert(AlertType.ERROR);
    	            alert.initOwner(dialogStage);
    	            alert.setTitle("Invalid Fields");
    	            alert.setHeaderText("Please correct invalid fields");
    	            alert.setContentText(errorMessage);

    	            alert.showAndWait();
    	            }
    		}*/
    	}


}
