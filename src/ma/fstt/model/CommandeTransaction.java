package ma.fstt.model;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommandeTransaction extends BaseTranscation<Commande> {

	public CommandeTransaction() throws SQLException {
		super();
		  
	}

	@Override
	public void save(Commande object) throws SQLException {
		
		String request = "INSERT INTO commande( numero, date , id_client ) VALUES (?,? , ?) ";
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		// mapping objet relation
		
		this.preparedStatement.setString(1, object.getNumero());
		this.preparedStatement.setString(2, object.getDate());
		this.preparedStatement.setInt(3, object.getClient().getId_client());
		

		
		this.preparedStatement.execute();
		
	}

	@Override
	public void update(Commande object) throws SQLException {
		String request= "update  commande  set numero =? , date=?, id_client=? where id_commande=? " ;
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		// maooing relatipon et objet table client et objet client 
		this.preparedStatement.setString(1, object.getNumero());
		this.preparedStatement.setString(2, object.getDate());
		this.preparedStatement.setInt(3, object.getClient().getId_client());
		this.preparedStatement.setInt(4, object.getId_commande());
		
		this.preparedStatement.execute();
		
	}

	@Override
	public void delete(Commande object) throws SQLException {
		
		String request= "delete  from  commande where id_commande =?" ;
		
		this.preparedStatement = this.cnx.prepareStatement(request);
		
		this.preparedStatement.setInt(1, object.getId_commande());
		
	    this.preparedStatement.execute();
	   
	   
	}

	@Override
	public List<Commande> getAll() throws SQLException {
		
    List<Commande> liste = new ArrayList<Commande>();
		
		
		String request = "select * from  commande  ";
		
		this.statement = this.cnx.createStatement();

		 this.resultSet = this.statement.executeQuery(request);
		 
		 List <Client> clients = new ClientTransaction().getAll();
		
		 
		 while ( this.resultSet.next()) {
			
			Client tempClient = new Client();
			List<Lign_cmd>templLign_cmds=new ArrayList<Lign_cmd>() ;
			tempClient.setId_client(this.resultSet.getInt(4));
			for (Client client : clients) {
				if (tempClient.getId_client()==client.getId_client()) {
					tempClient.setEmail(client.getEmail());
					tempClient.setNom(client.getNom());
					tempClient.setGenre(client.getGenre());
				}
			}
		


			 liste.add(new Commande(this.resultSet.getInt(1) ,this.resultSet.getString(2) , this.resultSet.getString(3) , tempClient ));
			
			 templLign_cmds.clear();

		}
		
		
		
		this.cnx.close();
		return liste;
	}

	@Override
	public Commande getOne(Integer id) throws SQLException {
	
	
		
		
		
		return null;
	}
	
	
	
	public List<Commande> getByClient(Client client) throws SQLException {
		
		
		
		
		return null;
	}

}
