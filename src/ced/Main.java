package ced;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//
//			Menu le_menu = new Menu();
//			le_menu.choix();
		
		int nbMiseOld = 3;
		int valMiseOld = 4;
		String miseString;
		int nbMiseNew=0;
		int valMiseNew = 0 ;
		ArrayList result = new ArrayList<Integer>();
		
		 System.out.println("Vous avez decide de surencherir sur "+nbMiseOld+ " dés de "+valMiseOld);
		  
		  do{	  
			  try{
				  System.out.println("Veuillez selectionner votre surenchere dans le format suivant x-y pour x(=nombre de dés) dés de y(=Valeur de dés)");
				  Scanner scs = new Scanner(System.in);
				  miseString = scs.nextLine();
				  String tabval[] = miseString.split("-");
				  
				  for (int i = 0; i <2; i++) {
				      try {
				    	  result.remove(0);
				      }catch(Exception e){
//				    	  System.out.println("Erreur lors du recyclage ArrayList");
				      }
				  }
				  
				  for ( int y = 0; y < tabval.length; y++) {
				      try {
				          result.add(y,Integer.parseInt(tabval[y]));
				      } catch (NumberFormatException nfe) {
				        System.out.println("Format incorrect...");
				      }
			 		  
				  }
			
			  
				  }
			    catch(Exception e){
				  System.out.println("tu tes chier dessus lvl2");
			  }
			  
			   
			  
		  }while((Integer)result.get(0) < 3);
		  
		  System.out.println("result"+result);
		 
		  
		  }
}