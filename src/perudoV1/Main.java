package perudoV1;
import java.io.IOException;
import java.rmi.*;

public class Main {

	public static void main(String[] args) {
			GameManager gm;
			
			int id_partie;
			
			Menu le_menu = new Menu();
			
			le_menu.choix();
			
			
			/*
			try {
				gm = (GameManager) Naming.lookup("rmi://localhost:1099/Perudo");
				
				
				
				id_partie = gm.rejoindre("Sam");
				
				System.out.println("Liste dans la partie "+id_partie+" :");
				gm.liste_joueurs(id_partie);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		
	}
}
