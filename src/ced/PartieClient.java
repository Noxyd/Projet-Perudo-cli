package ced;

import java.rmi.Remote;

public interface PartieClient extends Remote{
	
	public void bet(int nbMiseOld, int valMiseOld) throws java.rmi.RemoteException;

		
}
