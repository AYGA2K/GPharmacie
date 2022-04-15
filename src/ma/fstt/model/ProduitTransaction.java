package ma.fstt.model;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProduitTransaction extends  BaseTranscation<Produit> {
	

		public ProduitTransaction() throws SQLException {
			super();
			
			
		}
		public  void save(Produit object)  throws SQLException {
			
			String request="insert into Produit (Libelle,Pull,Id_Rayon) values( ?,?,?)";
			this.preparedStatement = this.cnx.prepareStatement(request);
			this.preparedStatement.setString(1, object.getLibelle());
			this.preparedStatement.setInt(2, object.getPull());
			this.preparedStatement.setInt(3, object.getrayon().getId_rayon() );
			

			
			this.preparedStatement.execute();
			
			
			
			
		}
		@Override
		public void update(Produit object) throws SQLException {
			
			String request= "update  Produit  set Libelle =? , Pull=?, Id_Rayon=? where Id_Produit=? " ;
            
            this.preparedStatement = this.cnx.prepareStatement(request);
            
             
            this.preparedStatement.setString(1, object.getLibelle());
            this.preparedStatement.setInt(2, object.getPull());
            this.preparedStatement.setInt(3, object.getrayon().getId_rayon());
            this.preparedStatement.setInt(4, object.getId_Produit());
            
            
            this.preparedStatement.execute();
		}
		@Override
		public void delete(Produit object) throws SQLException {
			
			String request= "delete  from  Produit where Id_Produit =?" ;
			
			this.preparedStatement = this.cnx.prepareStatement(request);
			
			this.preparedStatement.setInt(1, object.getId_Produit());
			
		  this.preparedStatement.execute();
		   
		   
			

			
			
		}
		@Override
		public List<Produit> getAll() throws SQLException {
			
		
			List<Produit> malist = new ArrayList<Produit>();

			String request= "select * from  Produit " ;
			
			this.statement = this.cnx.createStatement();
			
		   this.resultSet = 	this.statement.executeQuery(request);
		   
		   List <Rayon> rayons = new RayonTransaction().getAll();
		   while (this.resultSet.next()) {
            Rayon tempRayon = new Rayon();
			tempRayon.setId_rayon(this.resultSet.getInt(4));
			for (Rayon rayon : rayons) {
				if (tempRayon.getId_rayon()==rayon.getId_rayon()) {
					tempRayon.setNumero(rayon.getNumero());
					tempRayon.setNmbr_etage(rayon.getNmbr_etage());
					tempRayon.setType(rayon.getType());
                    
				}
			}
			
			   malist.add(new Produit( this.resultSet.getInt(1),  this.resultSet.getString(2), this.resultSet.getInt(3),tempRayon));   
			   
			
		}
        return malist;
    }
		@Override
		public Produit getOne(Integer id) throws SQLException {
			
			return null;
		}
		
		
		
		
		

		

}