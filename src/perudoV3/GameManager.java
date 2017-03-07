package perudoV3;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface GameManager extends Remote{
	
	public String recherche_partie() throws RemoteException;
	public ArrayList<String> recherche_partie_list() throws RemoteException;
	public String creer_partie_client() throws RemoteException;
	public void declarer_partie(String url) throws RemoteException;
	
}
