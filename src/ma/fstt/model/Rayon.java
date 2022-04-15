package ma.fstt.model;

import java.util.List;

public class Rayon {
private  int id_rayon;
private int  Numero;
private int nmbr_etage;
private int type;

private List<Produit> listProduit ;
 public Rayon () {}
Rayon(int id_rayon,int Numero,int nmbr_etage,int type){
    super();
  this.id_rayon=id_rayon;
  this.Numero=Numero;
  this.nmbr_etage=nmbr_etage;
  this.type=type;
}
public int getId_rayon() {
    return id_rayon;
}
public void setId_rayon(int id_rayon) {
    this.id_rayon = id_rayon;
}

public void setNmbr_etage(int nmbr_etage) {
    this.nmbr_etage = nmbr_etage;
}
public int getNmbr_etage() {
    return nmbr_etage;
}
public void setNumero(int numero) {
    Numero = numero;
}
public int getNumero() {
    return Numero;
}
public void setType(int type) {
    this.type = type;
}
public int getType() {
    return type;
}

public void setListProduit(List<Produit> listProduit) {
    this.listProduit = listProduit;
}
public List<Produit> getListProduit() {
    return listProduit;
}


}