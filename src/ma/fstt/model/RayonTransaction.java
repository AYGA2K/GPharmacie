    package ma.fstt.model;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.List;


    
    public class RayonTransaction extends  BaseTranscation<Rayon> {
        
    
            public RayonTransaction() throws SQLException {
                super();
                
                
            }
            public  void save(Rayon object)  throws SQLException {
                
                String request="insert into Rayon(Numero,nbr_Etage,Type) values( ?,?,?)";
                this.preparedStatement = this.cnx.prepareStatement(request);
               
                this.preparedStatement.setInt(1, object.getNumero());
                this.preparedStatement.setInt(2, object. getNmbr_etage());
                this.preparedStatement.setInt(3, object.getType());
    
                
                this.preparedStatement.execute();
                
                
                
                
            }
            @Override
            public void update(Rayon object) throws SQLException {
                
                String request= "update  Rayon  set Numero =? , nbr_Etage=?, Type=? where Id_Rayon=? " ;
            
            this.preparedStatement = this.cnx.prepareStatement(request);
            
             
            this.preparedStatement.setInt(1, object.getNumero());
            this.preparedStatement.setInt(2, object.getNmbr_etage());
            this.preparedStatement.setInt(3, object.getType());
            this.preparedStatement.setInt(4, object.getId_rayon());
            
            
            this.preparedStatement.execute();
                
            }
            @Override
            public void delete(Rayon object) throws SQLException {
              
                String request= "delete  from  Rayon where Id_Rayon =?" ;
                
                this.preparedStatement = this.cnx.prepareStatement(request);
                
                this.preparedStatement.setInt(1, object.getId_rayon());
                
              this.preparedStatement.execute();
               
               
                
    
                
                
            }
            @Override
            public List<Rayon> getAll() throws SQLException {
                
               
                List<Rayon> malist = new ArrayList<Rayon>();
    
                String request= "select * from  Rayon " ;
                
                this.statement = this.cnx.createStatement();
                
               this.resultSet = 	this.statement.executeQuery(request);
               
               
               while (this.resultSet.next()) {
                
                   malist.add(new Rayon( this.resultSet.getInt(1),  this.resultSet.getInt(2), this.resultSet.getInt(3), this.resultSet.getInt(4)));   
                   
                
            }
            return malist;
        }
            @Override
            public Rayon getOne(Integer id) throws SQLException {
            
                 
    
                return null;
            }
            
            
            
            
            
    
            
    
    }