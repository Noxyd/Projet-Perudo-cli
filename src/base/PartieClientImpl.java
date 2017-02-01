package base;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class PartieClientImpl implements PartieClient, Runnable {
	
	//attributes
	private final String BASE_URL = "client-";
	private final int NB_MAX_JOUEURS = 1;
	private String url;
	PartieInt partie_server;
	private String url_server;
		
	//Constructor
	public PartieClientImpl() {
		super();
		this.url = BASE_URL+generer_chaine();
		this.partie_server = null;
		this.url_server = null;
	}
	
	//Methods
	
	public void run() {
		this.jouer(url_server);
		
	}
	
	public synchronized void lobby(String url_partie){
		this.url_server = url_partie;
		try {
			this.partie_server = (PartieInt) Naming.lookup(url_partie);
			//Saisie du pseudo
			String res;
			System.out.println("Saisir votre pseudo : ");
			Scanner sc = new Scanner(System.in);
			res = sc.nextLine();
			
			if(partie_server.getNombreJoueurs()<NB_MAX_JOUEURS){			
				partie_server.rejoindre(url, res);
				System.out.println("Bienvenue dans le lobby de "+url_partie);
				
				System.out.println("Les joueurs sont : ");
				String[] listePseudos = partie_server.getListePseudos();
				for(int i = 0; i<6; i++){
					System.out.println("- "+listePseudos[i]);			
				}
				
				//TODO Securiser la saisie
				
				do{
					System.out.println("Etes-vous pret a commencer ? [Yes]");
					sc = new Scanner(System.in);
					res = sc.nextLine();
				}while(!res.equals("Yes"));
				
				System.out.println("La partie va bientôt débuter.");
				
				partie_server.isReady(url);
				
				wait();
				
			} else {
				System.out.println("Impossible de rejoindre la partie : Serveur plein");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	public synchronized void jouer(String url){
			notify();
	}
	
	public String generer_chaine(){
		
	    String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
	    String chaine = "";
	    
	    for(int i = 0; i < 5; i++)
	    {
	       int j = (int)Math.floor(Math.random() * 62);
	       chaine += caracteres.charAt(j);
	    }
	    
	    return chaine;
	}

	
	
	
}
