package perudoV2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

//Server
public class Main {
	public static void main(String[] args){
		
		try {
			
			/*GameImpl game = new GameImpl();
			GameControl gc = new GameControl(game, 1);
			Thread thGC = new Thread(gc);
			
			System.out.println("[SERVER ONLINE]");
			
			//Turn on the registry
			LocateRegistry.createRegistry(1099);
			
			//Bind url with the shared game object
			Naming.rebind("game-1", game);
			
			//This thread check if there is enough clients to begin
			thGC.start();
			
			//The game wait for the notify of the GC
			game.round();*/
			
			GameManagerImpl gm = new GameManagerImpl();
			
			//Turn on the registry
			LocateRegistry.createRegistry(1099);
			
			System.out.println("[SERVER ONLINE]");
			
			//Bind url with the shared game object
			Naming.rebind("main-gm", gm);
			
			//Create games
			gm.creer_partie();
			gm.creer_partie();
			
		} catch (RemoteException | MalformedURLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}