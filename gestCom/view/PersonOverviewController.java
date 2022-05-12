package ma.ensao.gi3.gestCom.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import ma.ensao.gi3.gestCom.MainApp;

public class PersonOverviewController {

	 	@FXML
	    private GridPane personGrid;
	    /*/@FXML
	    private TableColumn<Person, String> firstNameColumn;
	    @FXML
	    private TableColumn<Person, String> lastNameColumn;*/

	    @FXML
	    private Label fullName;
	    @FXML
	    private Label Price;
	    @FXML
	    private Label product;
	    @FXML
	    private Label date;
	    @FXML
	    private Label cityLabel;
	    @FXML
	    private Label stateCommand;

	    // Reference to the main application.
	    private MainApp mainApp;

	    /**
	     * The constructor.
	     * The constructor is called before the initialize() method.
	     */
	    public PersonOverviewController() {
	    }

	    /**
	     * Initializes the controller class. This method is automatically called
	     * after the fxml file has been loaded.
	     */
	   //@FXM
	   // private void initialize() {
	    	// Initialize the person table with the two columns.
	       // fullName.set

	        /*personGrid.add(cellData -> cellData.getValue().fullNameProperty(),0,1);
	        personGrid.add(cellData -> cellData.getValue().fullNameProperty(),0,1);
	        personGrid.add(cellData -> cellData.getValue().fullNameProperty(),0,1);*/
	   // }

	    /**
	     * Is called by the main application to give a reference back to itself.
	     *
	     * @param mainApp
	     */
	   /* public void setMainApp(MainApp mainApp) {
	        this.mainApp = mainApp;

	        // Add observable list data to the table
	        personTable.setItems(mainApp.getPersonData());
	    }*/
}
