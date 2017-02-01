package base;

import java.rmi.RemoteException;

public interface PartieInt extends java.rmi.Remote{
	
	public void rejoindre(String url, String pseudo) throws java.rmi.RemoteException;
	
	public int getNombreJoueurs() throws java.rmi.RemoteException;
	
	public boolean getState() throws java.rmi.RemoteException;
	
	public void setState(String url,boolean value) throws java.rmi.RemoteException;
	
	public void listerJoueurs() throws RemoteException;
	
	public String[] getListePseudos() throws RemoteException;
	
	public void isReady(String url) throws RemoteException;
	
}
