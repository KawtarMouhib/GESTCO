package ma.ensao.gi3.gestCom.model;

public class Command {

	private  int idCommand;
	private  String dateCommand;
	private  String nomClient;
	private  String adresse;
	private  String nomProduit;
	private  int prix;
	private  int quantite;
	private String status;



	/**
	 * Default constructor.
	 */
	public Command() {

	}

	/**
	 * Constructor with some initial data.
	 *
	 * @param idCommand
	 * @param dateCommand
	 * @param idClient
	 * @param adresse
	 * @param idProduit
	 * @param prix
	 * @param quantite
	 * @param status
	 */

	public Command(int idCommand, String dateCommand, String nomClient,String adresse, String nomProduit, int quantite, int prix,String status) {
		this.idCommand = idCommand;
		this.dateCommand = dateCommand;
		this.nomClient = nomClient;
		this.adresse = adresse;
		this.nomProduit = nomProduit;
		this.prix = prix;
		this.quantite = quantite;
		this.status = status;
	}

	/*public Command(int idCommand, int idProduit) {
		this.idCommand = idCommand;
		this.idProduit = idProduit;
	}*/


	public int getIdCommand() {
		return idCommand;
	}

	public void setIdCommand(int idCommand) {
		this.idCommand = idCommand;
	}

	public String getDateCommand() {
		return dateCommand;
	}

	public void setDateCommand(String dateCommand) {
		this.dateCommand = dateCommand;
	}

	public String getNomClient() {
		return nomClient;
	}

	public void setNomClient(String nomClient) {
		this.nomClient = nomClient;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getNomProduit() {
		return nomProduit;
	}

	public void setNomProduit(String nomProduit) {
		this.nomProduit = nomProduit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	public int getPrix() {
		return prix;
	}

	public void setPrix(int prix) {
		this.prix = prix;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
