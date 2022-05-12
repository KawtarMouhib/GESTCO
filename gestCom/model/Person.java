package ma.ensao.gi3.gestCom.model;
//import javax.xml.bind.annotation.*;
//import javax.xml.bind.annotation.XmlAccessorType;
//import javax.xml.bind.annotation.XmlAttribute;
//import javax.xml.bind.annotation.XmlRootElement;



import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Person {
private String idclient;
	private  String nomm;
	private  String emaill;
	private String tel;
	private String addresse;





	/*public Person(String nom, String email) {id=o;
		this.tel = new SimpleIntegerProperty(tel);
		this.nom = new SimpleStringProperty(nom);
		this.email=new SimpleStringProperty("jfkdjfkd");
		this.nomprod=new SimpleStringProperty("weeewwwww");
		this.quantite = new SimpleIntegerProperty(23);
}*/
	public Person(String nom, String email,String add,String telephone,String id) {
	this.idclient=id;
	this.addresse=add;
	this.tel=telephone;
	this.nomm =  nom;
	this.emaill=email;

}

	//les Gets
	public String getNomm() {
		return nomm;
	}
	public String getIdclient() {
		return idclient;
	}
	public String getAddresse() {
		return addresse;
	}
	public String getEmaill() {
		return emaill;
	}
	public String getTel() {
		return tel;
	}
	public String setnomm(String a) {
		return this.nomm=a;
	}
	/*public int gettel() {
		return tel.get();
	}
	public int getquantite() {
		return quantite.get();
	}
	//les Sets
	public void setid(int t) {
		this.id=t;
	}
	public void setnom(String nom) {
		this.nom.set(nom);
	}
	public void settel(int tel) {
		this.tel.set(tel);
	}
	public void setquantite(int quantite) {
		this.quantite.set(quantite);
	}
*/


}
