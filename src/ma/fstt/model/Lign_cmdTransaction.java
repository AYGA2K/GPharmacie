package ma.fstt.model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Lign_cmdTransaction extends  BaseTranscation<Lign_cmd> {
	

		public Lign_cmdTransaction() throws SQLException {
			super();
		
			
		}
		public  void save(Lign_cmd object)  throws SQLException {
			
			String request="insert into produit(qte,cmd,pr) values( ?,?,?)";
			this.preparedStatement = this.cnx.prepareStatement(request);
			this.preparedStatement.setInt(1, object.getQte());
			this.preparedStatement.setInt(2, object.getCmd().getId_commande());
			this.preparedStatement.setInt(3, object. getPr().getId_Produit());
			

			
			this.preparedStatement.execute();
			
			
			
			
		}
		@Override
		public void update(Lign_cmd object) throws SQLException {
		
            String request= "update  ligne_cmd  set qte =? , Id_Cmd=? , Id_Produit where id=? " ;
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		 
		this.preparedStatement.setInt(1, object.getQte());
		this.preparedStatement.setInt(2, object.getCmd().getId_commande());
		this.preparedStatement.setInt(3, object.getPr().getId_Produit());
		
		
		this.preparedStatement.execute();
			
		}
		@Override
		public void delete(Lign_cmd object) throws SQLException {
			
			String request= "delete  from  ligne_cmd where 	id =?" ;
			
			this.preparedStatement = this.cnx.prepareStatement(request);
			
			this.preparedStatement.setInt(1, object.getId_ligncmd());
			
		  this.preparedStatement.execute();
		   
		   
			

			
			
		}
		@Override
		public List<Lign_cmd> getAll() throws SQLException {
			
            try {
                
            } catch (Exception e) {
                
            }
			
			List<Lign_cmd> malist = new ArrayList<Lign_cmd>();

			String request= "select * from  ligne_cmd " ;
			
			this.statement = this.cnx.createStatement();
			
		   this.resultSet = 	this.statement.executeQuery(request);
		   
           List <Commande> commandes = new CommandeTransaction().getAll();
           List <Produit> produits = new ProduitTransaction().getAll();
		   while (this.resultSet.next()) {
            Commande tempCommande = new Commande();
			tempCommande.setId_commande(this.resultSet.getInt(3));
            Produit tempProduit= new Produit();
			tempProduit.setId_Produit(this.resultSet.getInt(4));
            
			for (Commande commande : commandes) {
				if (tempCommande.getId_commande()==commande.getId_commande()) {
					tempCommande.setNumero(commande.getNumero());
					tempCommande.setDate(commande.getDate());
					tempCommande.setClient(commande.getClient());
				}
			}

            for (Produit produit : produits) {
				if (tempProduit.getId_Produit()==produit.getId_Produit()) {
					tempProduit.setLibelle(produit.getLibelle());
					tempProduit.setPull(produit.getPull());
					tempProduit.setrayon(produit.getrayon());
				}
			}

			
			  malist.add( new Lign_cmd(this.resultSet.getInt(1), this.resultSet.getInt(2), tempCommande, tempProduit));
			   
			
		}
        this.cnx.close();
		return malist;
		
    }
        @Override
        public Lign_cmd getOne(Integer id) throws SQLException {
            
            return null;
        }

}