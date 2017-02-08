package perudoV2;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Clients extends Remote {
	
	public String getName() throws RemoteException;
	
	public String getURL() throws RemoteException;
	
	public ArrayList<Integer> choice(int round,int nbMiseOld, int valMiseOld)throws RemoteException;
	
	public void printString(String chaine)throws RemoteException;
}
