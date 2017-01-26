package ced;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Scanner;

import ced.PartieInt;

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
	
	
	public void printToAll(String msg){
		
		System.out.println("Message du serveur: "+msg);
		
	}
	
	public void orderInGame(ArrayList<String> ordreJ){
		String psd;
		for(int i = 0; i < ordreJ.size(); i++) { 
		    psd = partie_server.urlPseudo(ordreJ.get(i));
		    System.out.print("Joueur n°"+(i+1)+". ");
		    System.out.println(psd);
		}  
		
	}
	public void yourDice(){
		
		ArrayList dice = new ArrayList<Integer>();
		dice = partie_server.recupDe(this.url);
		System.out.println(dice);
		
	}
	
	public void currentPlayerAdvertise(String url){
		String psd;
		psd = partie_server.urlPseudo(url);
		System.out.println("C'est au tour de "+psd+" de jouer.");
		
	}
	
	public void currentBetAdvertise(String url, int nb1, int nb2){
		String psd = partie_server.urlPseudo(url);
		System.out.println(psd + "a annoncer "+nb1+" dés de "+nb2);
	}
	
	public void bet(){
		int p =0;
		int w = 0;
		Scanner s = new Scanner(System.in);
		
		System.out.println("C'est a vous de jouer:");
		System.out.println("======================");
		System.out.println("");
		System.out.println("1/ Annoncer menteur");
		System.out.println("2/ Annoncer tout pile");
		System.out.println("3/ Surencherir");
		System.out.println("");
		System.out.println("");
		
		do{try {
			System.out.println("Veuillez selectionner l'action que vous voulez réaliser :");
			p = s.nextInt();
			if(p > 3 || p < 1){
				System.out.println("Ce choix est invalide !");
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
		
		switch (p) {
		  case 1:		//il annonce menteur
			  //partie_serveur.menteur();
		    break;
		  case 2:		//il annnonce tout pile
			  //partie_serveur.toutPile();
		    break;
		  case 3:		//il annonce mise
			 
			  
			  //recuperer derniere mise et faire test correspondant puis appeller methode serveur correspondante
			  //partie_serveur.miser();			  
			break;
		}
		
	}
}
