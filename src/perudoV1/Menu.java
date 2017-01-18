package perudoV1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Menu {
	
	GameManager gm;
	private final int JOUER = 1;
	private final int RECHERCHER = 2;
	private final int QUITTER = 3;
	
	public Menu(){
		try {
			gm = (GameManager) Naming.lookup("rmi://localhost:1099/Perudo");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void choix(){
		int id_partie;
		int[] tab_partie;
		int choix;
		Scanner sc = new Scanner(System.in);
		
		this.welcome_screen();
		
		System.out.print("### Saisir : ");
		choix = sc.nextInt();
		
		do{
			switch (choix) {
				case JOUER:
					this.jouer_partie();
				try {
					id_partie=gm.recherche_partie();
					System.out.println(id_partie);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
					break;
					
				case RECHERCHER:
					this.rech_partie();
					try {
						gm.recherche_partie_list();
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
					
				case QUITTER:
					System.out.println("");
					System.out.println("Au revoir !");
					break;
		
		
				default:
					choix=0;
					break;
			}
		}while(choix == 0);
		
	}
	
	public void welcome_screen(){
		System.out.println("======================");
		System.out.println("PERUDO by STRI");
		System.out.println("======================");
		System.out.println("");
		System.out.println("");
		System.out.println("1/ Jouer une partie Rapide");
		System.out.println("2/ Rechercher une partie");
		System.out.println("3/ Quitter");
		System.out.println("");
		System.out.println("");
	}
	
	public void jouer_partie(){
		System.out.println("======================");
		System.out.println("PERUDO by STRI | Jouer une partie Rapide");
		System.out.println("======================");
		System.out.println("");
		System.out.println("");
		
		
	}
	
	public void rech_partie(){
		System.out.println("======================");
		System.out.println("PERUDO by STRI | Recherche de partie");
		System.out.println("======================");
		System.out.println("");
		System.out.println("");
		
		
	}
}
