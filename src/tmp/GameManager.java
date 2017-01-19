package tmp;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameManager extends Remote{
	
	public String recherche_partie() throws RemoteException;
	
}
