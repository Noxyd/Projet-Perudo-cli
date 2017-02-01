




package perudoV1;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

//Client
public class Main {

	public static void main(String[] args) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String pseudo, url;
		
		System.out.println("Saisir votre pseudo :");
		pseudo = sc.nextLine();
		url = pseudo;
		try {
			
			ClientsImpl client_1 = new ClientsImpl(pseudo, url);
			
			Game game_1 = (Game)Naming.lookup("rmi://localhost:1099/game-1");
			if(game_1.connexion(client_1) != 100){
				System.out.println("Erreur lors de la connexion.");
			} else {
				System.out.println(client_1.getName()+" est connecté.");
			}	
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
	}

}
