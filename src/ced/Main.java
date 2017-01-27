package ced;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
//
//			Menu le_menu = new Menu();
//			le_menu.choix();
		int w=0;
		int x=0;
		int nbMiseOld = 3;
		int valMiseOld = 4;
		String miseString;
		int nbMiseNew=0;
		int valMiseNew = 0 ;
		ArrayList result = new ArrayList<Integer>();
		
		 System.out.println("Vous avez decide de surencherir sur "+nbMiseOld+ " dés de "+valMiseOld);
		 System.out.println("Veuillez selectionner votre surenchere dans le format suivant x-y pour x(=nombre de dés) dés de y(=Valeur de dés)");
		  do{	  
			  try{
				  if(w==1){
					  System.out.println("La valeur que vous avez entre n'est pas possible pour surencherir");
					  System.out.println("Veuillez entrer une valeur correcte :");
				  }
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
				  if (tabval.length == 2){
				  
				      try {
				    	 for ( int y = 0; y < tabval.length; y++) {
				          result.add(y,Integer.parseInt(tabval[y]));
				      } }catch (NumberFormatException nfe) {
				        System.out.println("Format incorrect, veuillez entrer des entiers");
				        result.add(0,0);
				      }
			 		  
				  }else{
					  System.out.println("Format incorrect veuillez entrer un format de type x-y");
				        result.add(0,0);
				  }
			
			  
				  }
			    catch(Exception e){
				  System.out.println("Erreur entrée");
				  result.add(0,0);
			  }
			  
			   w=1;
			   if ((Integer)result.get(0) >= 3 && (Integer)result.get(1) > 4){
				   if ((Integer)result.get(1) >6){
					   x=0;
					   System.out.println("Ce n'est pas possible de miser sur un "+(Integer)result.get(1)+"... Un dés n'a que 6 face !");
				   }else{
					   x=1;
				   }
			   }else if((Integer)result.get(0) > 3 && (Integer)result.get(1) >= 4){
				   if ((Integer)result.get(1) >6){
					   x=0;
					   System.out.println("Ce n'est pas possible de miser sur un "+(Integer)result.get(1)+"... Un dés n'a que 6 face !");
				   }else{
					   x=1;
				   }
			   }else{
				   x=0;
			   }
			   
			   
			   
		  }while(x==0);
		  
		  System.out.println("result"+result);
		 
		  
		  }
}