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
		String psd =null;
		for(int i = 0; i < ordreJ.size(); i++) { 
		    try {
				psd = partie_server.urlPseudo(ordreJ.get(i));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    System.out.print("Joueur n°"+(i+1)+". ");
		    System.out.println(psd);
		}  
		
	}
	public void yourDice(){
		
		ArrayList dice = new ArrayList<Integer>();
		try {
			dice = partie_server.recupDe(this.url);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(dice);
		
	}
	
	public void currentPlayerAdvertise(String url){
		String psd = null;
		try {
			psd = partie_server.urlPseudo(url);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("C'est au tour de "+psd+" de jouer.");
		
	}
	
	public void currentBetAdvertise(String url, int nb1, int nb2){
		String psd = null;
		try {
			psd = partie_server.urlPseudo(url);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(psd + "a annoncer "+nb1+" dés de "+nb2);
	}
	
	
	public ArrayList<Integer> bet(int nbMiseOld, int valMiseOld){
		
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
			
			  	int z=0;
			  	int x=0;
				nbMiseNew=0;
				valMiseNew = 0 ;
//				ArrayList result = new ArrayList<Integer>();
				
				 System.out.println("Vous avez decide de surencherir sur "+nbMiseOld+ " dés de "+valMiseOld);
				 System.out.println("Veuillez selectionner votre surenchere dans le format suivant x-y pour x(=nombre de dés) dés de y(=Valeur de dés)");
				  do{	  
					  try{
						  if(z==1){
							  System.out.println("La valeur que vous avez entre n'est pas possible pour surencherir");
							  System.out.println("Veuillez entrer une valeur correcte :");
						  }
						  Scanner scs = new Scanner(System.in);
						  miseString = scs.nextLine();
						  String tabval[] = miseString.split("-");
						  
						  for (int i = 0; i <2; i++) {
						      try {
						    	  resultChx.remove(0);
						      }catch(Exception e){
//						    	  System.out.println("Erreur lors du recyclage ArrayList");
						      }
						  }
						  if (tabval.length == 2){
						  
						      try {
						    	 for ( int y = 0; y < tabval.length; y++) {
						          resultChx.add(y,Integer.parseInt(tabval[y]));
						      } }catch (NumberFormatException nfe) {
						        System.out.println("Format incorrect, veuillez entrer des entiers");
						        resultChx.add(0,0);
						      }
					 		  
						  }else{
							  System.out.println("Format incorrect veuillez entrer un format de type x-y");
						        resultChx.add(0,0);
						  }
					
					  
						  }
					    catch(Exception e){
						  System.out.println("Erreur entrée");
						  resultChx.add(0,0);
					  }
					  
					   z=1;
					   if ((Integer)resultChx.get(0) >= nbMiseOld && (Integer)resultChx.get(1) > valMiseOld){
						   if ((Integer)resultChx.get(1) >6){
							   x=0;
							   System.out.println("Ce n'est pas possible de miser sur un "+(Integer)resultChx.get(1)+"... Un dés n'a que 6 face !");
						   }else{
							   x=1;
						   }
					   }else if((Integer)resultChx.get(0) > nbMiseOld && (Integer)resultChx.get(1) >= valMiseOld){
						   if ((Integer)resultChx.get(1) >6){
							   x=0;
							   System.out.println("Ce n'est pas possible de miser sur un "+(Integer)resultChx.get(1)+"... Un dés n'a que 6 face !");
						   }else{
							   x=1;
						   }
					   }else{
						   x=0;
					   }
					   
					   
					   
				  }while(x==0);
				  
				  //Changer retour suivant rmi serveur pour renvoyer l'arraylist
				 // System.out.println("result"+result);
				  
				 
				  
				  }
		return resultChx;
		
	}
	
	public void resultAdvertise(String urlJ1, String urlJ2, int chx, boolean result){
		
		String psdJprec = null;
		String psdJcure = null;
		try {
			psdJprec = partie_server.urlPseudo(urlJ1);
			psdJcure = partie_server.urlPseudo(urlJ2);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
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
