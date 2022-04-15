package ma.fstt.model;

import java.util.List;

// java bean
public class Client {
	
	
	private Integer id_client ;
	
	private String nom ;
	
	private String email ;
	
	
	private String genre ;
	
	// one to many 
	private List<Commande> lsitcmds ;
	
	
	
	


	public List<Commande> getLsitcmds() {
		return lsitcmds;
	}




	public void setLsitcmds(List<Commande> lsitcmds) {
		this.lsitcmds = lsitcmds;
	}




	public Client(Integer id_client, String nom, String email, String genre) {
		super();
		this.id_client = id_client;
		this.nom = nom;
		this.email = email;
		this.genre = genre;
	}
	
	


	public Client() {
		super();
		
	}




	public Integer getId_client() {
		return id_client;
	}


	public void setId_client(Integer id_client) {
		this.id_client = id_client;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getGenre() {
		return genre;
	}


	public void setGenre(String genre) {
		this.genre = genre;
	}




	@Override
	public String toString() {
		return "Client [id_client=" + id_client + ", nom=" + nom + ", email=" + email + ", genre=" + genre + "]";
	}
	
	
	
	
	
	
	
	
	

}
