package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ClientTransaction extends BaseTranscation<Client>{
	
	private CommandeTransaction commandeTransaction ;

	public ClientTransaction() throws SQLException {
		super();
		
		
		commandeTransaction = new CommandeTransaction();
	}

	@Override
	public void save(Client object) throws SQLException {
		
		
		
		String request= "insert into client (nom , email, genre) values (? ,? , ? )" ;
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		// maooing relatipon et objet table client et objet client 
		this.preparedStatement.setString(1, object.getNom());
		this.preparedStatement.setString(2, object.getEmail());
		this.preparedStatement.setString(3, object.getGenre());
		
		this.preparedStatement.execute();
		
	}

	@Override
	public void update(Client object ) throws SQLException {
	
		String request= "update  client  set nom =? , email=?, genre=? where id_client=? " ;
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		// maooing relatipon et objet table client et objet client 
		this.preparedStatement.setString(1, object.getNom());
		this.preparedStatement.setString(2, object.getEmail());
		this.preparedStatement.setString(3, object.getGenre());
		this.preparedStatement.setInt(4, object.getId_client());
		
		this.preparedStatement.execute();

	}

	@Override
	public void delete(Client object) throws SQLException {
		
		String request= "delete  from  client where id_client =?" ;
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, object.getId_client());
		
	  this.preparedStatement.execute();
	   
	   
		

		
	}

	@Override
	public List<Client> getAll() throws SQLException {
		
		
		List<Client> malist = new ArrayList<Client>();

		String request= "select * from  client " ;
		
		this.statement = this.cnx.createStatement();
		
	   this.resultSet = 	this.statement.executeQuery(request);
	   
	   
	   while (this.resultSet.next()) {
		
		   malist.add(new Client( this.resultSet.getInt(1),  this.resultSet.getString(2), this.resultSet.getString(3), this.resultSet.getString(4)));   
		   
		 
		
	}
		

	this.cnx.close();
		return malist;
	}
        
     

	@Override
	public Client getOne(Integer id) throws SQLException {
	
				String request= "select * from  client where id_client =?" ;
				
				this.preparedStatement = this.cnx.prepareStatement(request);
				
				this.preparedStatement.setInt(1, id);
				
			   this.resultSet = 	this.preparedStatement.executeQuery();
			   
			   
			   while (this.resultSet.next()) {
				   
				   
				   
				   Client cli = new Client( this.resultSet.getInt(1),  this.resultSet.getString(2), this.resultSet.getString(3), this.resultSet.getString(4));   
				   
				   cli.setLsitcmds(commandeTransaction.getByClient(cli));
				  
				   return cli ;
				   
				 
				
			}
				

				return null;
	}

}
