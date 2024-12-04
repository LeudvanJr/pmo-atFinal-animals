package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Animal;
import entity.Habitat;
import exception.DAOException;

public class AnimalDAOImpl implements AnimalDAO {
	
	private Connection conn;
	
	public AnimalDAOImpl() throws DAOException {
		try {
			conn = ResourceManager.getConnection();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void insert(Animal animal) throws DAOException {
		try {
			String sql = """
					INSERT INTO animal
						(id, nome_popular, genero, especie, classe, id_habitat, altura, peso)
					VALUES
						(?, ?, ?, ?, ?, ?, ?, ?)
					""";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, animal.getId());
			stm.setString(2, animal.getNomePopular());
			stm.setString(3, animal.getGenero());
			stm.setString(4, animal.getEspecie());
			stm.setString(5, animal.getClasse());
			stm.setFloat(6, animal.getHabitat().getId());
			stm.setFloat(7, animal.getAltura());
			stm.setFloat(8, animal.getPeso());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Animal animal) throws DAOException {
		try {
			String sql = """
					UPDATE 
						animal
					SET
						nome_popular = ?,
						genero = ?,
						especie = ?,
						classe = ?,
						id_habitat = ?,
						altura = ?,
						peso = ?
					WHERE
						id = ?
					""";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, animal.getNomePopular());
			stm.setString(2, animal.getGenero());
			stm.setString(3, animal.getEspecie());
			stm.setString(4, animal.getClasse());
			stm.setFloat(5, animal.getHabitat().getId());
			stm.setFloat(6, animal.getAltura());
			stm.setFloat(7, animal.getPeso());
			stm.setInt(8, animal.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void delete(Animal animal) throws DAOException {
		try {
			String sql = """
					DELETE FROM
						animal
					WHERE
						id = ?
					""";
			
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, animal.getId());
			stm.executeUpdate();
		}catch (SQLException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Animal findOne(Animal animal) throws DAOException {
		try {
			String sql = """
					SELECT
						a.id AS id_animal,
						a.nome_popular,
						a.genero,
						a.especie,
						a.classe,
						a.altura,
						a.peso,
						h.id AS id_habitat,
						h.nome,
						h.tipo,
						h.descricao,
						h.clima,
						h.altitude
					FROM 
						animal a, habitat h
					WHERE
						a.nome_popular LIKE ? AND
						a.id_habitat = h.id
					""";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, "%"+animal.getNomePopular()+"%");
			ResultSet res = stm.executeQuery();
			
			animal = new Animal();
			if(res.next()) {
				Habitat habitat = new Habitat();
				habitat.setId(res.getInt("id_habitat"));
				habitat.setNome(res.getString("nome"));
				habitat.setTipo(res.getString("tipo"));
				habitat.setDescricao(res.getString("descricao"));
				habitat.setClima(res.getString("clima"));
				habitat.setAltitude(res.getFloat("altitude"));
				
				animal.setId(res.getInt("id_animal"));
				animal.setNomePopular(res.getString("nome_popular"));
				animal.setGenero(res.getString("genero"));
				animal.setEspecie(res.getString("especie"));
				animal.setClasse(res.getString("classe"));
				animal.setHabitat(habitat);
				animal.setAltura(res.getFloat("altura"));
				animal.setPeso(res.getFloat("peso"));
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		return animal;
	}

	@Override
	public List<Animal> findAll() throws DAOException {
		List<Animal> lista = new ArrayList<>();
		try {
			String sql = """
					SELECT
					a.id AS id_animal,
					a.nome_popular,
					a.genero,
					a.especie,
					a.classe,
					a.altura,
					a.peso,
					h.id AS id_habitat,
					h.nome,
					h.tipo,
					h.descricao,
					h.clima,
					h.altitude
				FROM 
					animal a, habitat h
				WHERE
					a.id_habitat = h.id
				""";
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet res = stm.executeQuery();
			
			while(res.next()) {
				Habitat habitat = new Habitat();
				habitat.setId(res.getInt("id_habitat"));
				habitat.setNome(res.getString("nome"));
				habitat.setTipo(res.getString("tipo"));
				habitat.setDescricao(res.getString("descricao"));
				habitat.setClima(res.getString("clima"));
				habitat.setAltitude(res.getFloat("altitude"));
				
				Animal animal = new Animal();
				animal.setId(res.getInt("id_animal"));
				animal.setNomePopular(res.getString("nome_popular"));
				animal.setGenero(res.getString("genero"));
				animal.setEspecie(res.getString("especie"));
				animal.setClasse(res.getString("classe"));
				animal.setHabitat(habitat);
				animal.setAltura(res.getFloat("altura"));
				animal.setPeso(res.getFloat("peso"));
				
				lista.add(animal);
			}
		}catch(SQLException e) {
			throw new DAOException(e);
		}
		return lista;
	}
}
