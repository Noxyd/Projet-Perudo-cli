package perudoV1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface Clients extends Remote {
	
	public String getName() throws RemoteException;
	
	public String getURL() throws RemoteException;
	
	public ArrayList<Integer> choice(int round,int nbMiseOld, int valMiseOld)throws RemoteException;
	
	public void printString(String chaine)throws RemoteException;
	
	public int getNbDes()throws RemoteException;
	
	public int getVal(int key)throws RemoteException;
	
	public void suppDe(int nbe)throws RemoteException;
	
	public void ajoutDe1()throws RemoteException;
	
	public ArrayList<Integer> getDes()throws RemoteException;
	
	public void lancerDe() throws RemoteException;
	
	public void ajoutDe5()throws RemoteException;
}
