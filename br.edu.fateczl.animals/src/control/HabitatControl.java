package control;

import entity.Habitat;
import exception.AnimalsException;
import exception.DAOException;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.HabitatDAO;
import persistence.HabitatDAOImpl;

public class HabitatControl {
	
	private IntegerProperty id = new SimpleIntegerProperty(0);
	private StringProperty nome = new SimpleStringProperty("");
	private StringProperty tipo = new SimpleStringProperty("");
	private StringProperty descricao = new SimpleStringProperty("");
	private StringProperty clima = new SimpleStringProperty("");
	private SimpleFloatProperty altitude = new SimpleFloatProperty(0);
	private ObservableList<Object> listaHabitat = FXCollections.observableArrayList();
	
	private HabitatDAO habitatDAO;
	
	public HabitatControl() throws AnimalsException {
		try {
			habitatDAO = new HabitatDAOImpl();
			listAll();
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	

	public void insert() throws AnimalsException {
		try {
			habitatDAO.insert(toEntity());
			listAll();
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	public void update() throws AnimalsException {
		try {
			habitatDAO.update(toEntity());
			clearBoundary();
			listAll();
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	public void delete() throws AnimalsException {
		try {
			habitatDAO.delete(toEntity());
			listAll();
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	public void search() throws AnimalsException {
		try {
			listaHabitat.clear();
			Habitat habitat = habitatDAO.findOne(toEntity());
			if(habitat == null)
				return;
			listaHabitat.add(habitat);
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	public void listAll() throws AnimalsException {
		try {
			listaHabitat.clear();
			listaHabitat.addAll(habitatDAO.findAll());
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	private Habitat toEntity() {
		Habitat habitat = new Habitat();
		habitat.setId(id.get());
		habitat.setNome(nome.get());
		habitat.setTipo(tipo.get());
		habitat.setDescricao(descricao.get());
		habitat.setClima(clima.get());
		habitat.setAltitude(altitude.get());
		return habitat;
	}
	
	public void toBoundary(Object object) {
		if(object == null || object.getClass() != Habitat.class)
			return;
		Habitat habitat = (Habitat) object;
		id.set(habitat.getId());
		nome.set(habitat.getNome());
		tipo.set(habitat.getTipo());
		descricao.set(habitat.getDescricao());
		clima.set(habitat.getClima());
		altitude.set(habitat.getAltitude());
	}
	
	public void clearBoundary() {
		id.set(0);
		nome.set("");
		tipo.set("");
		descricao.set("");
		clima.set("");
		altitude.set(0);
	}


	public IntegerProperty getId() {
		return id;
	}
	public StringProperty getNome() {
		return nome;
	}
	public StringProperty getTipo() {
		return tipo;
	}
	public StringProperty getDescricao() {
		return descricao;
	}
	public StringProperty getClima() {
		return clima;
	}
	public SimpleFloatProperty getAltitude() {
		return altitude;
	}
	public ObservableList<Object> getListaHabitat() {
		return listaHabitat;
	}
	
}
