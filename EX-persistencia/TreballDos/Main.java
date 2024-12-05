import java.util.Scanner;
import java.util.ArrayList;
import java.io.*;
public class Main{
    static Scanner input=new Scanner(System.in);
    static File arxiuComadaArticle = new File("Dades/Articles_Comandes.txt");
    public static void main(String[] args){
     menu();
    }
    public static void menu(){
        //attr
            int desicio;
        System.out.println("--------------------");
        System.out.println("        menú");
        System.out.println("--------------------");
        System.out.println("1.Consultar Comandes amb articles");
        System.out.println("2.Modificar dades");
        System.out.println("3.sortir");
        System.out.print("Escull una opció: ");
        desicio=input.nextInt();
        
        switch(desicio){
            case 1:
                saltLinia();
                consultarComnadesAmbArticles();
                break;
            case 2:
                saltLinia();
                subMenu();
                break;
            case 3:
                 System.out.println("Sortint del programa...");
                 System.exit(0); 
                 break;
            default:
                saltLinia();
                menu();
                break;
        }
      
    }
    
    //consultar fitxer amb les comandes i articles
    public static void consultarComnadesAmbArticles(){
        String regex = "[.]";
        ArrayList<String> llistaComArt = new ArrayList<>();
        ArrayList<String> llistaArticle = new ArrayList<>();
        try{
            Scanner linia = new Scanner(arxiuComadaArticle);
            while(linia.hasNextLine()){
                String comprovar = linia.nextLine();
                if(comprovar.contains("Comanda")){
                    String[] myArray = comprovar.split(regex);
                    Comanda comanda = new Comanda(myArray[0],myArray[1]);
                    llistaComArt.add(comanda+" ");
                }else if(comprovar.contains("art")){
                    String[] myArray = comprovar.split(regex);
                         Article article = new Article(myArray[0],myArray[1],myArray[2],myArray[3],myArray[4]);
                    llistaComArt.add(article+" ");
                }   
            }
         for(String mostrar : llistaComArt){
             System.out.println(mostrar);
         }
         llistaComArt.clear();
         llistaArticle.clear();
         menu();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    //preguntem que vol modificar
    public static void subMenu(){
            //attr
            int desicio2;
        System.out.println("--------------------");
        System.out.println("      Modificar");
        System.out.println("--------------------");
        System.out.println("1.Afegir comanda");
        System.out.println("2.Eliminar comanda");
        System.out.println("3.Modificar dades dels articles");
        System.out.println("4.Tornar menu principal");
        System.out.print("Escull una opció: ");
        desicio2=input.nextInt();
        
        switch(desicio2){
            case 1:
                saltLinia();
                afegirComanda();
                break;
            case 2:
                saltLinia();
                eliminarComanda();
                break;
            case 3:
                saltLinia();
                modificarDadesArticles();
                break;
            case 4:
                saltLinia();
                menu();
                break;
            default:
                saltLinia();
                subMenu();
                break;
        }
    }
    
            public static void afegirComanda(){
                try{
                    FileWriter escriureComanda = new FileWriter("Dades/Articles_Comandes.txt",true);
                //att comanda
                String nomComanda="";
                int preuTotal=0;
                ArrayList<String> llistaArticles = new ArrayList<>();
                //programa
                System.out.println("Introduir comanda: "); //comanda
                nomComanda=input.next();
                saltLinia();
                //escriure articles
                String fi="";
                while(!fi.equals("sortir")){
                    //att Article
                    String codi="";
                    String article="";
                    int quantitat=0;
                    int preu=0;
                    int preuQuantitat=0;
                    //inicia prog
                    llegirArticles();
                    System.out.println("Introduir codi article: ");//article
                    codi=input.next();
                    System.out.println("Introduir article: ");
                    article=input.next();
                    System.out.println("Introduir quantitat: ");
                    quantitat=input.nextInt();
                    System.out.println("Introduir preu: ");
                    preu=input.nextInt();
                    preuQuantitat=quantitat*preu;
                    preuTotal+=preuQuantitat;
                    //calculs i pasem variables per afegir al fitxer
                    String articles = codi+"."+article+"."+quantitat+"."+preu+"."+preuQuantitat;
                    llistaArticles.add(articles);
                    System.out.println("Vols sortir? si/no");
                    fi=input.next();
                    if(fi.equals("si")){
                        fi="sortir";
                    }
                    saltLinia();
                }
                //escriureComanda
                escriureComanda.write("\n"+nomComanda+"."+preuTotal+"."+" "+"."+" "+"."+" ");
                for(String mostrar : llistaArticles){
                    escriureComanda.write("\n"+mostrar);
                }
                escriureComanda.close();
                subMenu();
                }catch(IOException e){
                    System.out.println("Error fitxer");
                    e.printStackTrace();
                }
            }
            
			
            public static void eliminarComanda() {
            String igual = "";
			boolean eliminar = false;
			ArrayList<String> eliminarLinia = new ArrayList<>(); 
            try {
                Scanner linia = new Scanner(arxiuComadaArticle);
                ArrayList<String> linies = new ArrayList<>();
                while (linia.hasNextLine()) {
                    linies.add(linia.nextLine());
               }
			System.out.println("Quina comanda vols eliminar? ");
			igual = input.next();
			for (String liniaTrobada : linies) {
				if (liniaTrobada.contains("Comanda") && liniaTrobada.contains(igual)) {
					eliminar = true;
				}
				if (eliminar) {
					if (liniaTrobada.contains("Comanda") && !liniaTrobada.contains(igual)) {
						eliminar = false;
					}
				} else {
					eliminarLinia.add(liniaTrobada);
				}
			}
			FileWriter escribir = new FileWriter(arxiuComadaArticle);
			for (String liniaTrobada : eliminarLinia) {
				escribir.write(liniaTrobada + "\n");
			}
			escribir.close();
			subMenu(); 
			} catch (IOException e) {
				System.out.println("Error | fitxer no trobat");
				e.printStackTrace();
			}
		}
            
			
         public static void modificarDadesArticles() {
    ArrayList<String> guardarLinies = new ArrayList<>();
    String codi, article, comanda, articleModificar,comandaGuardar="";
    int quantitat, preu, preuQuantitat=0;
    try {
        Scanner linia = new Scanner(arxiuComadaArticle);
        while (linia.hasNextLine()) {
            guardarLinies.add(linia.nextLine());
        }
        System.out.println("Comanda que vols modificar la dada: ");
        comanda = input.next();
        System.out.println("Article a modificar: ");
        articleModificar = input.next(); 
        boolean comandaTrobada = false;
        boolean articleModificat = false;
        for (int i = 0; i < guardarLinies.size(); i++) {
            String liniaActual = guardarLinies.get(i);
            if (liniaActual.startsWith(comanda)) {
                comandaTrobada = true;
				comandaGuardar=comanda;
                continue;
            }
            if (comandaTrobada && liniaActual.startsWith("Comanda")) {
                break;
            }
            if (comandaTrobada && liniaActual.startsWith(articleModificar)) {
                System.out.println("Nou Codi: ");
                codi = input.next();
                System.out.println("Nou Article: ");
                article = input.next();
                System.out.println("Nova Quantitat: ");
                quantitat = input.nextInt();
                System.out.println("Nou Preu: ");
                preu = input.nextInt();
                preuQuantitat = quantitat * preu;
                
                guardarLinies.set(i, codi + "." + article + "." + quantitat + "." + preu + "." + preuQuantitat);
                articleModificat = true;
                break;
            }
        }
		for(int j = 0; j < guardarLinies.size(); j++){
			String modificarPreuComanda=guardarLinies.get(j);
			if(modificarPreuComanda.startsWith(comandaGuardar)){
				guardarLinies.set(j,comandaGuardar+"."+preuQuantitat+"."+" "+"."+" "+"."+" ");
				break;
			}
		}
        if (!articleModificat) {
            System.out.println("Article no trobat a la comanda.");
        }
        FileWriter escriure = new FileWriter(arxiuComadaArticle);
        for (String linies : guardarLinies) {
            escriure.write(linies + System.lineSeparator());
        }
        linia.close();
        escriure.close();
        System.out.println("Dades modificades correctament.");
		subMenu();

    } catch (IOException e) {
        System.out.println("Error | fitxer no trobat");
        e.printStackTrace();
    }
}



        
        //en el subMenu mostrar alguns articles per comprar
        public static void llegirArticles(){
            System.out.println("---------------------------");
            System.out.println("         Articles");
            System.out.println("---------------------------");
            try{
                File arxiuArticles = new File("Dades/Articles.txt");
                Scanner linia = new Scanner(arxiuArticles);
                while(linia.hasNextLine()){
                    String mostrar=linia.nextLine();
                    System.out.println(mostrar);
                }
				System.out.println("");
            }catch(IOException e){
                System.out.println("Fitxer error");
                e.printStackTrace();
            }
        }
        
        
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
