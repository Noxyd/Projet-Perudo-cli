package perudoV3;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Classe GameImpl.
 * Classe représentant une partie, c'est ici que s'execute le thread de partie.
 * 
 * @author Groupe Garcia, Lesaichot, Tavera - STRI
 * 
 */

@SuppressWarnings("serial")
public class GameImpl extends UnicastRemoteObject implements Game, Runnable {
	
	//attributs
	private ArrayList<Clients> clients_list;
	private boolean state;
	private boolean notWait1 = false;
	private String id_game;
	private GameManager gm;
	
	//constantes
	private final int NB_MAX_CLIENT = 2;
	private final int NB_ROUND = 5;
	
	/**
	 * Le constructeur permet d'initialiser une liste contenant les clients.
	 * 
	 * @throws RemoteException
	 */
	public GameImpl(GameManager gm) throws RemoteException{
		super();
		this.clients_list = new ArrayList<Clients>();
		this.state = false;
		this.id_game = "perudo-"+generer_chaine();
		this.gm = gm;
	}
	
	/**
	 * Ouvre un thread pour débuter une partie
	 * 
	 */
	public void run(){
		this.round();
	}
	
	/**
	 * Ouvre un thread pour débuter une partie
	 * 
	 * @return clients_list.size();
	 */
	public int getSize(){
		return clients_list.size();
	}
	
	/**
	 * Retourne l'ID de la partie
	 * 
	 * @return String id_game
	 */
	public String getId(){
		return this.id_game;
	}
	
	/**
	 * Permet à un client de s'enregistrer dans une partie.
	 * 
	 * @return int 100;
	 */
	public synchronized int connexion(Clients cli)throws RemoteException{

		clients_list.add(cli);
		
		this.gm.afficher(cli.getName()+" s'est connecte.");
		
		if(this.getSize() == NB_MAX_CLIENT){
			
			notify();
			notWait1 = true;
		}
		
		return 100;

	}
	
	/*public void ready()throws RemoteException{
		synchronized(this){
			for(Clients cli : this.clients_list){
				cli.printString("[INFO] La partie va commencer.");
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			notify();
		}
	}*/
	
	/**
	 * Permet d'afficher un client ainsi que son URL.
	 * 
	 */
	public void print_clients(){
			for(Clients cli : this.clients_list){
				try {
					System.out.println(cli.getName()+" : "+cli.getURL());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
	}
	
	/**
	 * Permet de jouer une partie. Cela signifie que le serveur est en mesure d'héberger une partie.
	 * Tous les traitements de jeux sont effectués au sein de cette méthode.
	 * 
	 */
	public void round(){

		ArrayList<Integer> client_choice;
		boolean test = false;
		boolean end_game = false;
		boolean recommencer = false;
		int round = 1;
		boolean first_player;
		char rec;
		Clients client_precedent = null;
		ArrayList<Integer> mise_precedente = new ArrayList<Integer>();
		Scanner sc = new Scanner(System.in);


		synchronized(this){
			try {
				//Waiting for clients
				while(!notWait1){
					wait();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				do{
					for(Clients clients : this.clients_list){
						clients.printString("[INFO] La partie va commencer.");
					}
					//Begining of the game
					
					for(Clients cli : this.clients_list){
						cli.ajoutDe5();
						this.gm.afficher("Initialisation des dï¿½s pour "+cli.getName()+" :\n"+cli.getDes()+"\n");
					}
					while(!end_game){
						//Begening of the round
						first_player = true;
						for(Clients cli : this.clients_list){
							cli.lancerDe();
							this.gm.afficher("Dï¿½s de "+cli.getName()+" :\n"+cli.getDes()+"\n");
						}
						
						do{
							for(Clients cli : this.clients_list){	
								//Test the end of the round
								if(test == false){
									//Inform clients of the current player
									for(Clients client : this.clients_list){
										client.printString("[INFO] C'est au tour de "+cli.getName()+".\n");
										if(!first_player){
											client.printString("Le joueur "+client_precedent.getName()+" a misï¿½ "+(int)mise_precedente.get(0)+" dï¿½ de "+(int)mise_precedente.get(1)+".\n");
										}
									}
	
									//Let the client choose
									if(first_player){
										client_choice = cli.choice(round,1,1, first_player);
										first_player = false;
									} else {
										client_choice = cli.choice(round,(int)mise_precedente.get(0),(int)mise_precedente.get(1),first_player);
									}
	
									this.gm.afficher("Choix de "+cli.getName()+" : "+client_choice);
									
									int choixAnnonce = 0;
	
									
									if (client_choice.get(0) == 0){
										System.out.println("choix MENTEUR");
										choixAnnonce = 2; 
										for(Clients client_message : this.clients_list){
											client_message.printString(cli.getName()+" dit MENTEUR !");
										}
										System.out.println("choix MENTEUR2");
									}else if(client_choice.get(0) == 1) {
										choixAnnonce = 3; 
										for(Clients client_message : this.clients_list){
											client_message.printString(cli.getName()+" dit TOUT PILE !");
										}
									} //toutpile 
									else {
										choixAnnonce = 1; 
										this.gm.afficher("choix MISE");
									}
	
									switch (choixAnnonce) {
									case 1:		//il annonce mise
										mise_precedente = client_choice;
										break;
	
									case 2:		//il annonce menteur
	
										int[][] tab_resultat = total_des();
	
										if(tab_resultat[1][mise_precedente.get(1)-1] < mise_precedente.get(0) ){
											//Joueur courant gagne
											for(Clients cli_annonce : this.clients_list){
												cli_annonce.printString("Le joueur "+cli.getName()+" a gagnï¿½.");
												cli_annonce.printString("Le joueur "+client_precedent.getName()+" perd 1 dï¿½.");
											}
	
											client_precedent.suppDe(1);
	
											test = true;
	
										} else {
											//Joueur courant perd
											for(Clients cli_annonce : this.clients_list){
												cli_annonce.printString("PERDU : Le joueur "+cli.getName()+" perd 1 dï¿½.");
											}
	
											cli.suppDe(1);
	
											test = true;
										}
	
	
										break;
									case 3:		//il annnonce tout pile
	
										int[][] tab_resultat2 = this.total_des();
										
										
										
	
										if(tab_resultat2[1][mise_precedente.get(1)-1] == mise_precedente.get(0) ){
											//Joueur courant gagne
											for(Clients cli_annonce : this.clients_list){
												cli_annonce.printString("Le joueur "+cli.getName()+" a gagnï¿½, il gagne 1 dï¿½ !");
											}
	
											client_precedent.ajoutDe1();
	
											test = true;
	
										} else {
											//Joueur courant perd
											for(Clients cli_annonce : this.clients_list){
												cli_annonce.printString("PERDU : Le joueur "+cli.getName()+" perd 1 dï¿½.");
											}
	
											cli.suppDe(1);
	
											test = true;
										}
	
									}	  
	
									client_precedent = cli;
	
								}
	
							}
							//Test the end of the round 
						}while(test != true);
	
						for(Clients cli : this.clients_list){
							this.gm.afficher(cli.getName()+" : "+cli.getDes());
						}
	
						//Increment the round : new round
						round++;
						test = false;
						//Inform the clients of the new round
						this.gm.afficher("Nouvelle manche : "+round);
						for(Clients client : this.clients_list){
							client.printString("[INFO] Fin de la manche "+(round-1)+".");
						}
						Thread.sleep(2000);
						if(round == this.NB_ROUND){
							end_game = true;
						}else{
							for(Clients client : this.clients_list){
								client.printString("[INFO] Debut de la manche "+round+".");
							}
						}
					}
					//End of game
					this.gm.afficher("Fin de la partie.");
					for(Clients client : this.clients_list){
						client.printString("[INFO] Fin de la partie.");
					}
					
					
				}while(recommencer == true);
			}catch(Exception e){

			}
		}

	}
	
	/*public void resultAdvertise(String j1, String j2, int chx, boolean result){
		
		switch (chx){
		
			case 1 : //Menteur
				
				System.out.println(j1+" a annoncer que " +j2+" etait un menteur.");
				//TODO traiter le result true or false
				break;
			
			case 2 : // Toutpile
				System.out.println(j1+" a annoncer tout pile sur la mise de " +j2+".");
				break;
				
			default :
				
				break;
				
		}
		
	}*/
	
	/**
	 * Permet de connaître l'état d'une partie (en cours ou non).
	 * 
	 * @return boolean state
	 */
	public boolean getState(){
		return this.state;
	}
	
	/**
	 * Permet de modifier l'état d'une partie (en cours ou non).
	 * 
	 */
	public void setState(boolean n_state){
		this.state = n_state;
	}
	
	/**
	 * Affiche le total des dés en fin de partie
	 * 
	 * @return int[][] tab_res
	 */
	public int[][] total_des(){

		int[][] tab_res = new int [2][6];
		
		for(int i=0; i<6; i++){
			
			int val = i+1;
			
			tab_res[0][i] = val;
			
			for(Clients cli : this.clients_list){
				try {
					for(int j = 0; j<cli.getNbDes(); j++){
						if(cli.getVal(j) == val || cli.getVal(j) == 1){
							tab_res[1][i] += 1;
						}
					}
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
		
		
		//affichage du total
		System.out.println("Total des dï¿½s : ");
		for(int i = 0;i<2;i++){
			for(int j=0;j<6;j++){
				System.out.print(tab_res[i][j]);
			}
			System.out.println("");
		}

		return tab_res;

	}
	
	
	/**
	 * Permet de générer une chaine de caractères alpha-numériques.
	 * @return String chaine
	 */
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
