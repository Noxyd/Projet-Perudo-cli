package base;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class PartieClientImpl implements PartieClient {
	
	//attributes
	private final String BASE_URL = "client-";
	private String url;
	PartieInt partie_server;
		
	//Constructor
	public PartieClientImpl() {
		super();
		this.url = BASE_URL+generer_chaine();
		this.partie_server = null;
	}
	
	//Methods
	
	public void lobby(String url_partie){
		try {
			this.partie_server = (PartieInt) Naming.lookup("rmi://localhost:1099/"+url_partie);
			
			System.out.println("Bienvenue dans le lobby de "+url_partie);
			
			System.out.println("En attente du serveur...");
			
		} catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}	
	}
	
	public boolean isReady(){
		boolean answer;
		
		Scanner sc = new Scanner(System.in);
		
		//do{
			//System.out.println("");
		//}
		
		return answer;
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
