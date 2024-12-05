import java.util.*;
import java.io.*;
public class Main{
	//att globals
	static Scanner input = new Scanner(System.in);
	static int opcio=0;
	
	
	public static void main(String[] args){
	//
	//Programa
	//
	menu();
    }
	
	//Metode princiapl
	public static void menu(){
		//Mostrem les opcions del menu
		System.out.println("------------------------");
		System.out.println("         Menu");
		System.out.println("------------------------");
		System.out.println(" 1.Consultar Alumnes");
		System.out.println(" 2.Consultar Mòdul");
		System.out.println(" 3.Consultar Alumnes i mòduls Matriculats");
		System.out.println(" 4.Crear Alumne");
		System.out.println(" 5.Crear Modul");
		System.out.println(" 6.Modificar Alumne");
		System.out.println(" 7.Modificar Modul");
		System.out.println(" 8.Sortir");
		System.out.print("Opció: ");
		opcio=input.nextInt();
		//switch per escollir l'opcio i anar al metode corresponent
		if(opcio>0&&opcio<=8){
		switch(opcio){
			case 1:
				consultarAlumnes();
				break;
			case 2:
				consultarModuls();
				break;
			case 3:
				consultarAlumnesIModuls();
				break;
			case 4: 
				crearAlumne();
				break;
			case 5:
				crearModul();
				break;
			case 6:
				modificarAlumne();
				break;
			case 7:
				modificarModul();
				break;
			case 8:
				break;
		}
		}else{
			System.out.println("Valor incorecte");
			menu();
		}
	}
	
	//metode consultar alumne mostre tots els alumnes
	public static void consultarAlumnes(){
		saltLinia();
		try{
			File arxiuAlumnes = new File("Dades/Alumnes.txt");
			Scanner linia = new Scanner(arxiuAlumnes);
			while(linia.hasNextLine()){
				String guardar = linia.nextLine();
				String[] myArray = guardar.split("[.]");
				Alumne alumne=new Alumne(myArray[0],myArray[1],myArray[2]);
				System.out.println(alumne);
			}
			linia.close();
		}catch(FileNotFoundException e){
			System.out.println("No s'ha trobat el fitxer");
			e.printStackTrace();
		}
		menu();
	}
	
	//metode consultar moduls
	public static void consultarModuls(){
		saltLinia();
		try{
			File arxiuModul = new File("Dades/Moduls.txt");
			Scanner linia = new Scanner(arxiuModul);
			while(linia.hasNextLine()){
				String guardar = linia.nextLine();
				String regex= "[.]";
				String[] myArry =  guardar.split(regex);
				Modul modul = new Modul(myArry[0],myArry[1]);
				System.out.println(modul);
			}
			linia.close();
		}catch(FileNotFoundException e){
			System.out.println("No s'ha trobat el fitxer");
			e.printStackTrace();
		}
		menu();
	}
	
	//metode consultar els moduls que fa els alumnes
	public static void consultarAlumnesIModuls(){
		saltLinia();
		//alumnes finals
		String a="";
		//moduls primers
		ArrayList<Modul>llistaModul=new ArrayList<>();
		try{
			File arxiuAlumnesIModuls = new File("Dades/Alumnes_Moduls.txt");
			Scanner linia = new Scanner(arxiuAlumnesIModuls);
			while(linia.hasNextLine()){
				String guardar = linia.nextLine();
				String[] myArray = guardar.split("[.]");
				if(!guardar.contains("-")){
					Alumne alumne=new Alumne(myArray[0],myArray[1],myArray[2]);
					a+=alumne+" ";
				}else{
					while(guardar.contains("-")){
					Modul modul=new Modul(myArray[3],myArray[4]);
					llistaModul.add(modul);
					break;
					}
					a+=llistaModul+"\n";
				}
			System.out.println(a);
			//netejem algunes variables per poder guardar altres
			a="";
			llistaModul.clear();
			}
			linia.close();
		}catch(FileNotFoundException e){
			System.out.println("No s'ha trobat el fitxer");
			e.printStackTrace();
		}
	}
	
	public static void crearAlumne(){
		saltLinia();
		//att
			String nom="";
			String cognom="";
			String dni="";
			Scanner input = new Scanner(System.in);
		try{
			FileWriter editarAlumnes = new FileWriter("Dades/Alumnes.txt",true);
			System.out.print("Introduir nom Alumne: ");
			nom=input.next();
			System.out.print("Introduir cognom Alumne: ");
			cognom=input.next();
			System.out.print("Introduir dni Alumne: ");
			dni=input.next();
			editarAlumnes.write("\n"+nom+"."+cognom+"."+dni);
			editarAlumnes.close();
		}catch(IOException e){
			System.out.println("No s'ha trobat el fitxer");
			e.printStackTrace();
		}
		nom="";
		saltLinia();
		menu();
	}
	
	public static void crearModul(){
		saltLinia();
		//att
			Scanner input = new Scanner(System.in);
			String codi = "";
			String nom = "";
		try{
		  FileWriter editarModul = new FileWriter("Dades/Moduls.txt",true);
		  System.out.print("Introduir codi mdoul: ");
		  codi=input.next();
		  System.out.print("Introduir nom del Modul: ");
		  nom=input.next();
		  editarModul.write("\n"+codi+"."+nom);
		  editarModul.close();
		}catch(IOException e){
			System.out.println("No s'ha trobat el fitxer");
			e.printStackTrace();
		}
		codi="";
		nom="";
		saltLinia();
		menu();
	}
	
	//Modifiquem un alumne
	public static void modificarAlumne() {
	//att
		Scanner input = new Scanner(System.in);
		ArrayList<String> alumnes = new ArrayList<>();
    try {
        File arxiuAlumnes = new File("Dades/Alumnes.txt");
        Scanner linia = new Scanner(arxiuAlumnes);
        while (linia.hasNextLine()) {
            alumnes.add(linia.nextLine());
        }
        linia.close();
        System.out.println("Introduir el nom de la persona que es vol modificar: ");
        String nom = input.next();
        boolean trobat = false;
        for (int i = 0; i < alumnes.size(); i++) {
            String guardar = alumnes.get(i);
            String[] dades = guardar.split("\\.");
            if (dades[0].equals(nom)) {
                trobat = true;
                System.out.println("Introduir nou nom: ");
                String nouNom = input.next();
                System.out.println("Introduir nou cognom: ");
                String nouCognom = input.next();
                System.out.println("Introduir nou DNI: ");
                String nouDNI = input.next();
                alumnes.set(i, nouNom + "." + nouCognom + "." + nouDNI);
                break;
            }
        }
        if (trobat) {
            FileWriter modificarAlumne = new FileWriter(arxiuAlumnes);
            for (String alumne : alumnes) {
                modificarAlumne.write(alumne + "\n");
            }
            modificarAlumne.close();
            System.out.println("Alumne modificat correctament.");
        } else {
            System.out.println("Alumne no trobat.");
        }
    } catch (FileNotFoundException e) {
        System.out.println("No s'ha trobat el fitxer");
        e.printStackTrace();
    } catch (IOException e) {
        System.out.println("Error al modificar l'arxiu.");
        e.printStackTrace();
    }
	menu();
}
	
	
	public static void modificarModul(){
		//att
			saltLinia();
			ArrayList<String> Moduls = new ArrayList<>();
				try{
			File arxiuModul = new File("Dades/Moduls.txt");
			Scanner linia = new Scanner(arxiuModul);
			while(linia.hasNextLine()){
				Moduls.add(linia.nextLine());
			}
			linia.close();
			System.out.println("Introduir el codi del modul que es vol modificar: ");
			String codi = input.next();
			boolean trobat = false;
			for (int i = 0; i < Moduls.size(); i++) {
				String guardar = Moduls.get(i);
				String[] dades = guardar.split("\\.");
				if (dades[0].equals(codi)) {
					trobat = true;
					System.out.println("Introduir nou codi: ");
					String nouCodi= input.next();
					System.out.println("Introduir nou nom: ");
					String nouNom = input.next();
					Moduls.set(i, nouCodi + "." + nouNom);
					break;
				}
			}
			if (trobat) {
            FileWriter modificarModul = new FileWriter(arxiuModul);
            for (String modul : Moduls) {
                modificarModul.write(modul + "\n");
            }
            modificarModul.close();
        } else {
            System.out.println("Alumne no trobat.");
        }
		}catch(FileNotFoundException e){
			System.out.println("No s'ha trobat el fitxer");
			e.printStackTrace();
		}catch (IOException e) {
        System.out.println("Error al modificar l'arxiu.");
        e.printStackTrace();
		}
		menu();
	}
	//salt linia
	public static void saltLinia(){
	try{
		Thread.sleep(2000);
		System.out.print("\033[H\033[2J");
		System.out.flush();
		} catch(InterruptedException e){
			e.printStackTrace();
	}
	}
}