package perudoV3;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Game extends Remote {
	
	public int connexion(Clients cli)throws RemoteException;
	
	//public void ready()throws RemoteException;
	
	public boolean getState()throws RemoteException;
	public int getSize()throws RemoteException;
	
}
