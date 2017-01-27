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
	
	public void bet(int nbMiseOld, int valMiseOld){
		int p =0;
		int w = 0;
		int nbMiseNew, valMiseNew;
		Scanner s = new Scanner(System.in);
		ArrayList resultChx = new ArrayList<Integer>();
		String miseString;
		
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
		  case 1://il annonce menteur
			  
			  resultChx.add(0);
			  break;
			  
		  case 2:		//il annnonce tout pile
			  
			  resultChx.add(1);
			  break;
			  
		  case 3:		//il annonce mise
			
			  System.out.println("Vous avez decide de surencherir sur "+nbMiseOld+ "dés de "+valMiseOld);
			  
			  do{	  
				  try{
					  System.out.println("Vuillez selectionner votre surenchere dans le format suivant x-y pour x(=nombre de dés) dés de y(=Valeur de dés)");
					  Scanner scs = new Scanner(System.in);
					  miseString = scs.nextLine();
					  miseString.useDelimiter
					  
				  }catch(Exception e){
					  
				  }
			  }while(1);
			  
	          input.useDelimiter("#;.");
	          String s = "";
	          while (input.hasNextInt()) {
	                   s +=(input.nextInt()) + "\n";
	           
	          }
	          System.out.println(s);
			  
				  
			break;
		}
		
	}
	
	public void resultAdvertise(String urlJ1, String urlJ2, int chx, boolean result){
		
		String psdJprec, psdJcure;
		psdJprec = partie_server.urlPseudo(urlJ1);
		psdJcure = partie_server.urlPseudo(urlJ2);
		
		switch (chx){
		
			case 1 : //Menteur
				
				System.out.println(psdJcure+" a annoncer que " +psdJprec+" etait un menteur");
				break;
			
			case 2 : // Toutpile
				
				break;
				
			default :
				
				break;
				
		}
		
	}
}
