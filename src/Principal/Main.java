package Principal;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) {
		
		try {
			Registry registre = LocateRegistry.getRegistry(10000);
			
			PartieInt stub = (PartieInt) Naming.lookup("partie");
			
			System.out.println(stub.printHello());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
