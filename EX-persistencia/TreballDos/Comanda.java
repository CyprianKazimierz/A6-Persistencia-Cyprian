import java.util.*;
public class Comanda{
	//att
	private String nom;
	private String preuTotal;
	
	//cosntr
	public Comanda(String nom, String preuTotal){
		this.nom=nom;
		this.preuTotal=preuTotal;
	}
	
	//gettr
	public String getNom(){
		return nom;
	}
	public String getPreu(){
		return preuTotal;
	}
	
	//settr
	public void setNom(String nom){
		this.nom=nom;
	}
	public void setPreu(String preuTotal){
		this.preuTotal=preuTotal;
	}
	
	//toString
	public String toString(){
		return "Comanda{ "+nom+", preuTotal: "+preuTotal+" }";
	}
}