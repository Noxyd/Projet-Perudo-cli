package perudoV1;
import java.net.MalformedURLException;
import java.rmi.*;

public class Main {

	public static void main(String[] args) {
			Test_RMI stub;
			try {
				stub = (Test_RMI) Naming.lookup("rmi://localhost:1099/partie");
				stub.sayHello();
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
