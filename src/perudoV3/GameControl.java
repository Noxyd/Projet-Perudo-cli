package perudoV3;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

public class GameControl implements Runnable{
	
	private GameImpl game;
	private HashMap<String,Clients> clients_list;
	String name = "";
	public Clients retCli,cli;

	
	public GameControl(GameImpl game, ArrayList<Clients> clients_listrecup){
		super();
		this.game = game;
		this.clients_list = new HashMap<>();
		peuplHashM(clients_listrecup);
	}
	
	private void peuplHashM(ArrayList<Clients> clients_list){
		for(Clients cli : clients_list){
			try {
				this.clients_list.put(cli.getName(),cli);
			} catch (RemoteException e) {
			}
		}
	}
	
	@Override
	public void run() {		
		
		while(true){
			try {
				quit();
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
			}
		}
		
	}
	
	public void quit() throws RemoteException{

		for(String name : clients_list.keySet()){
			try {
					this.clients_list.get(name).getName();
					System.out.println("Le joueur "+ name);
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
				} catch (RemoteException e1) {
					System.out.println("Absent(s) "+ name + " a quitté la partie.");
					this.clients_list.remove(name);
					game.setRet(name);
					game.dispEnd();


				}	
		}
	}
	
}
