package ma.fstt.model;
import java.util.List;



public class Produit {
	
	private int Id_Produit;
	private String Libelle;
	private int Pull;
	private Rayon rayon;
    private List<Lign_cmd> lign_cmds;
	
	Produit(int Id_Produit,String Libelle , int Pull,Rayon rayon){
        super();
		this.Id_Produit=Id_Produit;
		this.Libelle=Libelle;
		this.rayon=rayon;
		this.Pull=Pull;

	}
    public Produit(){
        super();
    }
    
  public void setLign_cmds(List<Lign_cmd> lign_cmds) {
      this.lign_cmds = lign_cmds;
  }
  public List<Lign_cmd> getLign_cmds() {
      return lign_cmds;
  }
        
    


  public int getId_Produit() {
      return Id_Produit;
  }
  public void setId_Produit(int id_Produit) {
      Id_Produit = id_Produit;
      
  }
  public void setrayon(Rayon rayon) {
     this.rayon = rayon;
      
  }
  public Rayon getrayon() {
      return rayon;
  }
  
  public String getLibelle() {
      return Libelle;
  }
  public void setLibelle(String libelle) {
      Libelle = libelle;
  }
  
  
  public int getPull() {
      return Pull;
  }
  public void setPull(int pull) {
      Pull = pull;
  }
  
  

  @Override
	public String toString() {
		return "produit [id_produit=" + Id_Produit + ", libelle=" + Libelle + ", Pull=" + Pull + ", Rayon=" + rayon+ "]";
	}
	
	

}