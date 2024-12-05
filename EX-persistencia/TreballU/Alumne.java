import java.util.ArrayList;
public class Alumne{
	//atributs
	private String nom;
	private String cognom;
	private String dni;
	static public ArrayList<Modul> modulsMatriculat = new ArrayList<>();
	
	//const
	public Alumne(String nom, String cognom, String dni){
		this.nom=nom;
		this.cognom=cognom;
		this.dni=dni;
	}
	//gett
	public String getNom(){
		return nom;
	}
	public static  ArrayList<Modul> getLlistaModuls(){
		return modulsMatriculat;
	}
	public String getCognom(){
		return cognom;
	}
	public String getDNI(){
		return dni;
	}
	//sett
	public void setNom(String nom){
		this.nom=nom;
	}
	public void setCognom(String cognom){
		this.cognom=cognom;
	}
	public void setDNI(String dni){
		this.dni=dni;
	}
	public String ferMatricula(){
		return "Matrícula Acceptada";
	}
	public static void addModul(Modul objModul){
		modulsMatriculat.add(objModul);
	}
	//toString
	public String toString(){
		return "Alumne{ "+"Nom: "+getNom()+", Cognom: "+cognom+", dni: "+dni+" }";
	}
}