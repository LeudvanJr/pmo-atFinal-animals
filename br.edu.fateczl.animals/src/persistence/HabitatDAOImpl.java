package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Habitat;
import exception.DAOException;

public class HabitatDAOImpl implements HabitatDAO {
	
	private Connection conn;
	
	public HabitatDAOImpl() throws DAOException {
		try {
			conn = ResourceManager.getConnection();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void insert(Habitat habitat) throws DAOException {
		try {
			String sql = """
					INSERT INTO habitat
						(id, nome, tipo, descricao, clima, altitude)
					VALUES
						(?, ?, ?, ?, ?, ?)
					""";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, habitat.getId());
			stm.setString(2, habitat.getNome());
			stm.setString(3, habitat.getTipo());
			stm.setString(4, habitat.getDescricao());
			stm.setString(5, habitat.getClima());
			stm.setFloat(6, habitat.getAltitude());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Habitat habitat) throws DAOException {
		try {
			String sql = """
					UPDATE
						habitat 
					SET
						nome = ?,
						tipo = ?,
						descricao = ?,
						clima = ?,
						altitude = ?
					WHERE
						id = ?
					""";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, habitat.getNome());
			stm.setString(2, habitat.getTipo());
			stm.setString(3, habitat.getDescricao());
			stm.setString(4, habitat.getClima());
			stm.setFloat(5, habitat.getAltitude());
			stm.setInt(6, habitat.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public void delete(Habitat habitat) throws DAOException {
		try {
			String sql = """
					DELETE FROM
						habitat
					WHERE
						id = ?
					""";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, habitat.getId());
			stm.executeUpdate();
		}catch (SQLException e) {
			throw new DAOException(e);
		}
		
	}

	@Override
	public Habitat findOne(Habitat habitat) throws DAOException {
		try {
			String sql = """
					SELECT *
					FROM 
						habitat
					WHERE
						nome LIKE ?
					""";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, "%"+habitat.getNome()+"%");
			ResultSet res = stm.executeQuery();
			
			habitat = new Habitat();
			if(res.next()) {
				habitat.setId(res.getInt("id"));
				habitat.setNome(res.getString("nome"));
				habitat.setTipo(res.getString("tipo"));
				habitat.setDescricao(res.getString("descricao"));
				habitat.setClima(res.getString("clima"));
				habitat.setAltitude(res.getFloat("altitude"));
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		return habitat;
	}

	@Override
	public List<Habitat> findAll() throws DAOException {
		List<Habitat> lista = new ArrayList<>();
		try {
			String sql = """
					SELECT *
					FROM 
						habitat
					""";
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet res = stm.executeQuery();
			
			while(res.next()) {
				Habitat habitat = new Habitat();
				habitat.setId(res.getInt("id"));
				habitat.setNome(res.getString("nome"));
				habitat.setTipo(res.getString("tipo"));
				habitat.setDescricao(res.getString("descricao"));
				habitat.setClima(res.getString("clima"));
				habitat.setAltitude(res.getFloat("altitude"));
				
				lista.add(habitat);
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}
	
	
}
