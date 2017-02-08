package perudoV2;

import java.rmi.RemoteException;

public class GameControl implements Runnable{
	
	private int state;
	private GameImpl game;
	
	private final int NB_MAX_CLIENTS = 2;
	
	public GameControl(GameImpl game, int state){
		super();
		this.state = state;
		this.game = game;
	}

	@Override
	public void run() {
		boolean test = true;
		int size;
		switch(this.state){
			case 1 :
				//Check the number of clients to begin
				do{
					size = game.getSize();
					if(size == NB_MAX_CLIENTS){
						test = false;
					}
				}while(test != false);
				//The game begin when there is enough clients
				try {
					game.ready();
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				break;
				
			case 2 : 
				//attente de tours
				
			default:
				System.out.println("Etat default");
		}
	}
	
	public void setState(int state){
		this.state = state;
	}
	
}
