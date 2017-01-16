package perudoV1;
import java.rmi.*;

public class Main {

	public static void main(String[] args) {
			GameManager gm;
			
			int id_partie;
			
			try {
				gm = (GameManager) Naming.lookup("rmi://localhost:1099/Perudo");
				
				id_partie = gm.rejoindre("Sam");
				
				System.out.println("Liste dans la partie "+id_partie+" :");
				gm.liste_joueurs(id_partie);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}

}
