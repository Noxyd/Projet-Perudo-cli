package perudoV2;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Classe GameManager.
 * Permet de gérer des parties : créer, supprimer, naviguer dans la liste des parties.
 * 
 * @author Groupe Garcia, Lesaichot, Tavera - STRI
 * 
 */

@SuppressWarnings("serial")
public class GameManagerImpl extends UnicastRemoteObject implements GameManager{

	
	
	/* Attributs */
	private Map<String, GameImpl> liste_parties;
	
	private final int NBMAX_JOUEURS = 2;
	private final String BASE_URL = "rmi://localhost:1099/";
	
	
	
	/* Constructeur */
	
	/**
	 * Le constructeur permet de creer une hashmap contenant l'url (key) de la partie ainsi qu'une Partie (Object).
	 * 
	 * @throws RemoteException
	 */
	public GameManagerImpl() throws RemoteException {
		super();
		liste_parties = new HashMap<String,GameImpl>();
	}
	
	
	
	/* Méthodes */
	
	/**
	 * Permet d'effectuer la recherche et l'attribution d'une partie.
	 * Retourne l'url de la partie et effectue un rebind entre l'url et l'objet de type Partie.
	 * 
	 * @return String url
	 * 
	 */
	public String recherche_partie() throws RemoteException{
		
		String url = "none";
		//test permet de vérifier qu'une partie a été trouvé dans la Hashmap.
		boolean test = false;
		
		for(String key : liste_parties.keySet()){
			//parties.get(key) retourne la valeur de la clé associé.
			//true : la partie est en en cours || false : la salle d'attente est ouverte.
			GameImpl current = liste_parties.get(key);
			
			if(current.getState() == false && test == false){
				if(current.getSize() <= NBMAX_JOUEURS){
					url = BASE_URL+key;
					try {
						Naming.rebind(url, current);
					} catch (MalformedURLException e) {
						e.printStackTrace();
					}
					test = true;
				}
			}
		}
				
		return url;
	}
	public ArrayList<String> recherche_partie_list() throws RemoteException {
		 ArrayList<String> list_part = new ArrayList<String>();
		 String url = "none";
			//test permet de vérifier qu'une partie a été trouvé dans la Hashmap.
			boolean test = false;
			
			for(String key : liste_parties.keySet()){
				//parties.get(key) retourne la valeur de la clé associé.
				//true : la partie est en en cours || false : la salle d'attente est ouverte.
				GameImpl current = liste_parties.get(key);
				
				if(current.getState() == false && test == false){
					if(current.getSize() <= NBMAX_JOUEURS){
						url = BASE_URL+key;
						try {
							Naming.rebind(url, current);
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
						list_part.add(url);
					}
				}
			}
			
			return list_part;
	}

	
	/**
	 * Cette méthode permet de créer un nouvel objet de type Partie à l'intérieur de la hashmap.
	 * On génere un id à cette partie ("perudo-" suivie de 5 caractères alpha-numériques). 
	 * Cet id devient la key de l'objet.
	 * 
	 */
	public void creer_partie() throws RemoteException{
		
		String id = "perudo-";
		
		id += generer_chaine();
		
		//TODO Penser à vérifier que la chaine n'existe pas deja
		
		liste_parties.put(id, new GameImpl());
		String url = BASE_URL+id;
		try {
			Naming.rebind(url, liste_parties.get(id));
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		
		System.out.println("Partie "+url+" crée.");
		
		Thread thPartie = new Thread(liste_parties.get(id));
		
		thPartie.start();
	}
	
	/**
	 * Génère une chaine composée de 5 caratères alpha-numériques.
	 * 
	 * @return chaine
	 */
	public String generer_chaine(){
		
		    String caracteres = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
		    String chaine = "";
		    
		    for(int i = 0; i < 5; i++)
		    {
		       int j = (int)Math.floor(Math.random() * 62);
		       chaine += caracteres.charAt(j);
		    }
		    
		    return chaine;
	}

}
