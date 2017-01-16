package perudoV1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface JoueurInt extends Remote {
	public String getPseudo() throws RemoteException;
	public void setPseudo(String pseudo) throws RemoteException;
	//public ArrayList<De> getDe_joueur() throws RemoteException;
}
