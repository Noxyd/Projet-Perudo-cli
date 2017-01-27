package ced;

import java.rmi.Remote;
import java.util.ArrayList;

public interface PartieClient extends Remote{
	
	public ArrayList<Integer> bet(int nbMiseOld, int valMiseOld) throws java.rmi.RemoteException;

		
}
