package tests;

import java.sql.Connection;
import java.sql.SQLException;

import entity.Animal;
import exception.DAOException;
import persistence.AnimalDAOImpl;
import persistence.ResourceManager;

public class MainTest {

	public static void main(String[] args) {
		
		try {
			Connection conn = ResourceManager.getConnection();
			AnimalDAOImpl aniDAO = new AnimalDAOImpl();
			
//			Animal a = new Animal();
//			a.setNomePopular("TESTE");;
//			
//			aniDAO.insert(a);
		} catch (SQLException | DAOException e) {
			e.printStackTrace();
		}
		
	}

}
