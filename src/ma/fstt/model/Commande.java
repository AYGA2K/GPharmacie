package ma.fstt.model;

import java.util.List;

public class Commande {
	
	
	
	private Integer id_commande;
	private String numero;
	private String date;
	private Client client ;
	private List<Lign_cmd> lign_cmds;
	
	
	
	
	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Commande() {
		super();
		
	}
	public Commande(Integer id_commande, String numero, String date , Client client) {
		super();
		this.id_commande = id_commande;
		this.numero = numero;
		this.date = date;
		this.client = client ;
	
	}
	public void setLign_cmds(List<Lign_cmd> lign_cmds) {
		this.lign_cmds = lign_cmds;
	}
	public List<Lign_cmd> getLign_cmds() {
		return lign_cmds;
	}
	public Integer getId_commande() {
		return id_commande;
	}
	public void setId_commande(Integer id_commande) {
		this.id_commande = id_commande;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Commande [id_commande=" + id_commande + ", numero=" + numero + ", date=" + date + ", client=" + client
				+ "]";
	}
	
	
	
	
	
	

}
