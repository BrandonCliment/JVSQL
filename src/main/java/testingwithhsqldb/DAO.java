package testingwithhsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;

public class DAO {
	private final DataSource myDataSource;
	
	public DAO(DataSource dataSource) {
		myDataSource = dataSource;
	}

	/**
	 * Renvoie le nom d'un client à partir de son ID
	 * @param id la clé du client à chercher
	 * @return le nom du client (LastName) ou null si pas trouvé
	 * @throws SQLException 
	 */
	public String nameOfCustomer(int id) throws SQLException {
		String result = null;
		
		String sql = "SELECT LastName FROM Customer WHERE ID = ?";
		try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement statement = myConnection.prepareStatement(sql)) {
			statement.setInt(1, id); // On fixe le 1° paramètre de la requête
			try ( ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// est-ce qu'il y a un résultat ? (pas besoin de "while", 
                                        // il y a au plus un enregistrement)
					// On récupère les champs de l'enregistrement courant
					result = resultSet.getString("LastName");
				}
			}
		}
		// dernière ligne : on renvoie le résultat
		return result;
	}
	
        public int addProduct(int ID,String nom,int prix) throws SQLException{
            
            //String sql = "INSERT INTO PRODUCT(ID,Name,Price) VALUES(?,?,?)";
            String sql2 = "INSERT INTO PRODUCT VALUES(?,?,?)"; //Possible comme ça vue qu'on remplit tous les champs lors de l'insertion
            
            try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement statement = myConnection.prepareStatement(sql2)) {
                        if (prix >=0) {
                            statement.setInt(1, ID); // On fixe le 1° paramètre de la requête
                            statement.setString(2, nom);
                            statement.setInt(3, prix);
                            return statement.executeUpdate();
                        }
                        else{
                            throw new SQLException("Prix invalide");
                        }

			
		}
            
        }
        
        
	public ProductEntity findProduct(int ID){
        ProductEntity result = null;
        String sql= "SELECT * FROM Product WHERE ID = ?";
        		try (Connection connection = myDataSource.getConnection(); // On crée un statement pour exécuter une requête
			PreparedStatement stmt = connection.prepareStatement(sql)) {
			stmt.setInt(1, ID);
			ResultSet rs = stmt.executeQuery(); 
                        if (rs.next()) { // On a trouvé
                                // On crée l'objet "entity"
                                result = new ProductEntity(ID,rs.getString("Name"),rs.getInt("Price"));
                        } // else on n'a pas trouvé, on renverra null
			
		}  catch (SQLException ex) {
			Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
			
		}

		return result;
        }
}
