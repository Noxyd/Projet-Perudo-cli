package perudoV1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameManager extends Remote{

	public int rejoindre(String pseudo) throws RemoteException;
	public int recherche_partie() throws RemoteException;
	public void creer_partie() throws RemoteException;
	public int genererID() throws RemoteException;
	public void liste_parties() throws RemoteException;
	public void liste_joueurs(int id_partie) throws RemoteException;
	
}
