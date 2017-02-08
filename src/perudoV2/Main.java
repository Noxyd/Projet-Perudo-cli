




package perudoV2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

//Client
public class Main {

	public static void main(String[] args) {
		
		try {
			
			GameManager gm = (GameManager) Naming.lookup("rmi://localhost:1099/main-gm");
			
			choix(gm);
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void choix(GameManager gm){
		int id_partie;
		int choix;
		int w = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("1/ Jouer une partie Rapide");
		System.out.println("2/ Rechercher une partie");
		System.out.println("3/ Quitter");
		
		System.out.print("### Saisir : ");
		
		do{
			try {
				choix = sc.nextInt();
		
		
				do{
					switch (choix) {
						case 1:
							jouer_partie(gm);			
							w=1;
							break;
							
						case 2:
							rech_partie(gm);
							try {
								gm.recherche_partie_list();
							} catch (RemoteException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							w=1;
							break;
						
						case 3:
							System.out.println("");
							System.out.println("Au revoir !");
							w=1;
							break;
				
				
						default:
							w=0;
							System.out.println("Veuillez Sélectionner un choix valide !");
							System.out.print("### Saisir : ");
							choix = sc.nextInt();
							break;
					}
				}while(w == 0);
			} catch(Exception e){
				System.out.println("Oops!! Please enter only integral numbers");
				System.out.println(sc.next() + " was not valid input.");
				System.out.println("Veuillez Sélectionner un choix valide !");
				System.out.print("### Saisir : ");
				choix = 0;
			}
		}while(w == 0);
	}
	
	public static void jouer_partie(GameManager gm){
		
		String pseudo, url, game_url;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Recherche d'une partie : ");
		try {
			game_url = gm.recherche_partie();
			
			System.out.println("Partie trouvee, url : "+game_url);
						
			System.out.println("Saisir votre pseudo :");
			pseudo = sc.nextLine();
			url = pseudo;
			
			ClientsImpl client_1 = new ClientsImpl(pseudo, url);
			Game game_1 = (Game)Naming.lookup(game_url);
			
			if(game_1.connexion(client_1) != 100){
				System.out.println("Erreur lors de la connexion.");
			} else {
				System.out.println(client_1.getName()+" est connecté.");
			}
			
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void rech_partie(GameManager gm){
		ArrayList<String> url;
		
		int p =0;
		int w= 0;
		int l=0;
		System.out.println("======================");
		System.out.println("PERUDO by STRI | Recherche de partie");
		System.out.println("======================\n\n");
		System.out.println("Recherche d'une partie : ");
		try {
			url = gm.recherche_partie_list();
			l = url.size();
			System.out.println("Partie trouvï¿½e(s):");
			for(int i = 0; i < url.size(); i++) { 
				System.out.print((i+1)+". ");
			    System.out.println(url.get(i));
			}  
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		System.out.println("");
//		System.out.println("Veuillez selectionner le numero de la partie a rejoindre :");
		Scanner s = new Scanner(System.in);;
		
			do{try {
				System.out.println("Veuillez selectionner le numero de la partie a rejoindre :");
				p = s.nextInt();
				if(p > l || p < 1){
					System.out.println("Cette partie n'existe pas !");
					w=0;
				}else{
				w=1;
				}
			}catch(Exception e){
				System.out.println("Oops!! Please enter only integral numbers");
				System.out.println(s.next() + " was not valid input.");
				w=0;
			}
			}while(w==0);
		
		System.out.println("Vous avez selectionner la partie :");
		try {
			Scanner sc = new Scanner(System.in);
			String game_url, pseudo;
			
			url = gm.recherche_partie_list();
			System.out.println(url.get(p-1));
			
			System.out.println("Saisir votre pseudo :");
			pseudo = sc.nextLine();
			
			ClientsImpl client_1 = new ClientsImpl(pseudo, "xx");
			Game game_1 = (Game)Naming.lookup(url.get(p-1));
			
			if(game_1.connexion(client_1) != 100){
				System.out.println("Erreur lors de la connexion.");
			} else {
				System.out.println(client_1.getName()+" est connecté.");
			}
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
	}
	
	

}
