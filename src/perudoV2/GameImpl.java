package perudoV2;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;


@SuppressWarnings("serial")
public class GameImpl extends UnicastRemoteObject implements Game, Runnable {
	
	private ArrayList<Clients> clients_list;
	private boolean state;
	
	public GameImpl() throws RemoteException{
		super();
		this.clients_list = new ArrayList<Clients>();
		this.state = false;
	}
	
	public void run(){
		GameControl gc = new GameControl(this, 1);
		Thread thGC = new Thread(gc);
		
		thGC.start();
		
		this.round();
	}
	
	public int getSize(){
		return clients_list.size();
	}
	
	public int connexion(Clients cli)throws RemoteException{
			
		clients_list.add(cli);
		System.out.println(cli.getName()+" s'est connecté.");
	
		return 100;
		
	}
	
	public void ready()throws RemoteException{
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
	}
	
	
	public void print_clients(){
			for(Clients cli : this.clients_list){
				try {
					System.out.println(cli.getName()+" : "+cli.getURL());
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
	}
	
	public void round(){
		
		ArrayList<Integer> client_choice;
		boolean test = false;
		boolean end_game = false;
		int round = 1;
		boolean first_player;
		String client_precedent = null;
		ArrayList<Integer> mise_precedente = new ArrayList<Integer>();
		
		
		synchronized(this){
			try {
				//Waiting for clients
				wait();
				//Begining of the game
				while(!end_game){
					//Begening of the round
					first_player = true;
					do{
						for(Clients cli : this.clients_list){	
							//Test the end of the round
							if(test == false){
								//Inform clients of the current player
								for(Clients client : this.clients_list){
									client.printString("[INFO] C'est au tour de "+cli.getName()+".");
									if(!first_player){
										client.printString("Le joueur "+client_precedent+" a misé "+(int)mise_precedente.get(0)+" dés de "+(int)mise_precedente.get(1)+".");
									}
								}
								
								//Let the client choose
								if(first_player){
									client_choice = cli.choice(round,1,1);
									first_player = false;
								} else {
									client_choice = cli.choice(round,(int)mise_precedente.get(0),(int)mise_precedente.get(1));
								}
								
								client_precedent = cli.getName();
								mise_precedente = client_choice;
							
							}
						}
						//Test the end of the round 
					}while(test != true);
					
					//Increment the round : new round
					round++;
					test = false;
					//Inform the clients of the new round
					System.out.println("Nouvelle manche : "+round);
					for(Clients client : this.clients_list){
						client.printString("[INFO] Fin de la manche "+(round-1)+".");
					}
					Thread.sleep(2000);
					if(round == 3){
						end_game = true;
					}else{
						for(Clients client : this.clients_list){
							client.printString("[INFO] Début de la manche "+round+".");
						}
					}
				}
				
				//End of game
				System.out.println("Fin de la partie.");
				for(Clients client : this.clients_list){
					client.printString("[INFO] Fin de la partie.");
				}
			}catch(Exception e){}
		}
	}

	public void resultAdvertise(String j1, String j2, int chx, boolean result){
		
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
		
	}
	
	public boolean getState(){
		return this.state;
	}
	
	public void setState(boolean n_state){
		this.state = n_state;
	}
}
