package base;

public class PartieClientImpl implements PartieClient {
	
	//attributes
	private final String BASE_URL = "client-";
	private String url;
		
	//Constructor
	public PartieClientImpl() {
		super();
		this.url = BASE_URL+generer_chaine();
	}
	
	//Methods
	
	public void lobby(String url_partie){
		
	}
	
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
