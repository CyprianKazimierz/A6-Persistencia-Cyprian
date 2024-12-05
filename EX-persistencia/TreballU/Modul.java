public class Modul{
	//atributs
	private String codi;
	private String nom;
	
	//const
	public Modul(String codi, String nom){
		this.codi=codi;
		this.nom=nom;
	}
	
	//gett
	public String getCodi(){
		return codi;
	}
	public String getNom(){
		return nom;
	}
	
	//sett
	public void setCodi(String codi){
		this.codi=codi;
	}
	public void setNom(String nom){
		this.nom=nom;
	}
	
	//toString
	public String toString(){
		return "Modul{ "+"Codi: "+getCodi()+", Nom del Modul: "+getNom()+" }"; 
	}
}