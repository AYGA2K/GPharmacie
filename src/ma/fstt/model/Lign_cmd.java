package ma.fstt.model;

public class Lign_cmd {
    private int id_ligncmd;
    private int qte;
    private Commande cmd;
    private Produit pr;
    Lign_cmd(int id, int qte,Commande cmd, Produit pr){
    super();
    this.id_ligncmd=id;
    this.qte=qte;
    this.cmd=cmd;
    this.pr=pr;
    }
    public void setCmd(Commande cmd) {
        this.cmd = cmd;
    }
    public Commande getCmd() {
        return cmd;
    }
    public void setPr(Produit pr) {
        this.pr = pr;
    }
    public Produit getPr() {
        return pr;
    }
    
    public void setQte(int qte) {
        this.qte = qte;
    }
    public int getQte() {
        return qte;
    }
    public void setId_ligncmd(int id_ligncmd) {
        this.id_ligncmd = id_ligncmd;
    }
    public int getId_ligncmd() {
        return id_ligncmd;
    }
    
	@Override
	public String toString() {
		return "Lign_cmd [id=" + id_ligncmd + ", Commande=" +cmd + ", produit=" + pr + ", qte="+qte+ "]";
	}
	
	
}