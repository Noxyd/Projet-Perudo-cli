package perudoV1;

import java.util.Scanner;

public class Menu {
	
	private final int JOUER = 1;
	private final int QUITTER = 2;
	
	public void choix(){
		int choix;
		Scanner sc = new Scanner(System.in);
		
		this.welcome_screen();
		
		System.out.print("### Saisir : ");
		choix = sc.nextInt();
		
		do{
			switch (choix) {
				case JOUER:
					this.jouer_partie();
					break;
					
				case QUITTER:
					System.out.println("");
					System.out.println("Au revoir !");
					break;
		
		
				default:
					choix=0;
					break;
			}
		}while(choix == 0);
		
	}
	
	public void welcome_screen(){
		System.out.println("======================");
		System.out.println("PERUDO by STRI");
		System.out.println("======================");
		System.out.println("");
		System.out.println("");
		System.out.println("1/ Jouer une partie");
		System.out.println("2/ Quitter");
		System.out.println("");
		System.out.println("");
	}
	
	public void jouer_partie(){
		System.out.println("======================");
		System.out.println("PERUDO by STRI | Jouer une partie");
		System.out.println("======================");
		System.out.println("");
		System.out.println("");
		
		
	}
	
	
}
