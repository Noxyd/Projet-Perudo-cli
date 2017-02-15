package perudoV2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

@SuppressWarnings("serial")
public class ClientsImpl extends UnicastRemoteObject implements Clients {
	
	private String name;
	private String url;
	private ArrayList<Integer> des;
	
	public ClientsImpl(String name, String url) throws RemoteException {
		super();
		this.name = name;
		this.url = url;
		this.des = new ArrayList<Integer>();
	}
	
	public String getName() throws RemoteException{
		return this.name;
	}
	
	public String getURL() throws RemoteException{
		return this.url;
	}
	
	public ArrayList<Integer> choice(int round, int nbMiseOld, int valMiseOld, boolean fPlayer)throws RemoteException{
		
		int p =0;
		int w = 0;
		Scanner s = new Scanner(System.in);
		ArrayList<Integer> resultChx = new ArrayList<Integer>();
		String miseString;
		
		if(fPlayer){
			System.out.println("Vous etes le premier jpueur, c'est a vous de jouer:");
			System.out.println("======================");
			System.out.println("Vos des : "+this.getDes());
		} else {
			System.out.println("C'est a vous de jouer:");
			System.out.println("======================");
			System.out.println("");
			System.out.println("1/ Annoncer menteur");
			System.out.println("2/ Annoncer tout pile");
			System.out.println("3/ Surencherir");
			System.out.println("");
			System.out.println("");
			System.out.println("Vos des : "+this.getDes());
		}
		
		
		do{
			if(fPlayer){
				p = 3;
				w=1;
			}else{
			try {
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
		}}while(w==0);
		
		switch (p) {
		  case 1://il annonce menteur
			  
			  resultChx.add(0);
			  //System.out.println(resultChx);
			  break;
			  
		  case 2:		//il annnonce tout pile
			  
			  resultChx.add(1);
			  break;
			  
		  case 3:		//il annonce mise
			
			  	int z=0;
			  	int x=0;
				if (fPlayer){
					 System.out.println("Vous etes le premier joueur, veuillez selectionner votre erenchere dans le format suivant x-y pour x(=nombre de dés) dés de y(=Valeur de dés)");
				}else{
					 System.out.println("Vous avez decide de surencherir sur "+nbMiseOld+ " dés de "+valMiseOld);
					 System.out.println("Veuillez selectionner votre surenchere dans le format suivant x-y pour x(=nombre de dés) dés de y(=Valeur de dés)");
				}
			
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
						   }else if((Integer)resultChx.get(0) == 0){
							   x=0;
							   System.out.println("Ce n'est pas possible de miser 0 des de quoi que ce soit...");
						   }
						   else{
							   x=1;
						   }
					   }else if((Integer)resultChx.get(0) > nbMiseOld && (Integer)resultChx.get(1) >= valMiseOld){
						   if ((Integer)resultChx.get(1) >6){
							   x=0;
							   System.out.println("Ce n'est pas possible de miser sur un "+(Integer)resultChx.get(1)+"... Un dés n'a que 6 face !");
						   }else if((Integer)resultChx.get(0) == 0){
							   x=0;
							   System.out.println("Ce n'est pas possible de miser 0 des de quoi que ce soit...");
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
	
	public void printString(String chaine)throws RemoteException{
		System.out.println(chaine);
	}
	
	public int getNbDes()throws RemoteException{
		return this.des.size();
	}
	
	public int getVal(int key)throws RemoteException{
		return this.des.get(key);
	}
	
	public void suppDe(int nbe)throws RemoteException{
		try {	
			int nbede = this.getNbDes();
			int endsize = nbede - nbe;
		
		
			while(nbede != endsize){
				des.remove(nbede-1);	
				nbede = this.getNbDes();
			}
		}catch(Exception e){
			
		}
	}
	
	public void ajoutDe1()throws RemoteException{
		des.add(0);
	}
	
	public void ajoutDe5()throws RemoteException{
		des.add(0);
		des.add(0);
		des.add(0);
		des.add(0);
		des.add(0);
	}
	
	public ArrayList<Integer> getDes()throws RemoteException{
		return des;
	}
	
	public void lancerDe() throws RemoteException{
		 int i;
		   for (i=0;i<this.getNbDes();i++) {
			   Random rand = new Random();
		       int nombreAleatoire = rand.nextInt(7 - 1) + 1;  //attention: le 7 est exclu et le 1 inclu
		       this.des.set(i, nombreAleatoire);
		   }		
	}
}
