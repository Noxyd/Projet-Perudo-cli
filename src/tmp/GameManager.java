package tmp;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameManager extends Remote{
	
	public String recherche_partie() throws RemoteException;
	public ArrayList recherche_partie_list() throws RemoteException;
	
}
