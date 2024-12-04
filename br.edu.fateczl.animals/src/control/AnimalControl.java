package control;


import entity.Animal;
import entity.Habitat;
import exception.AnimalsException;
import exception.DAOException;
import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import persistence.AnimalDAO;
import persistence.AnimalDAOImpl;

public class AnimalControl {
	
	private IntegerProperty id = new SimpleIntegerProperty();
	private StringProperty nomePopular = new SimpleStringProperty("");
	private StringProperty genero = new SimpleStringProperty("");
	private FloatProperty altura = new SimpleFloatProperty();
	private StringProperty especie = new SimpleStringProperty("");
	private StringProperty classe = new SimpleStringProperty("");
	private FloatProperty peso = new SimpleFloatProperty();
	private ObservableList<Object> listaAnimal = FXCollections.observableArrayList();
	private ObjectProperty<Object> habitatSelecionado = new SimpleObjectProperty<>();
	
	private AnimalDAO animalDAO = null; 
	
	public AnimalControl() throws AnimalsException {
		try {
			animalDAO = new AnimalDAOImpl();
			listAll();
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	public void insert() throws AnimalsException {
		try {
			animalDAO.insert(toEntity());
			clearBoundary();
			listAll();
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	public void update() throws AnimalsException {
		try {
			animalDAO.update(toEntity());
			listAll();
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	public void delete() throws AnimalsException {
		try {
			animalDAO.delete(toEntity());
			listAll();
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	public void search() throws AnimalsException {
		try {
			listaAnimal.clear();
			Animal animal = animalDAO.findOne(toEntity());
			if(animal == null)
				return;
			listaAnimal.add(animal);
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	public void listAll() throws AnimalsException {
		try {
			listaAnimal.clear();
			listaAnimal.addAll(animalDAO.findAll());
		} catch (DAOException e) {
			throw new AnimalsException(e);
		}
	}
	
	private Animal toEntity() {
		Animal animal = new Animal();
		animal.setId(id.get());
		animal.setNomePopular(nomePopular.get());
		animal.setGenero(genero.get());
		animal.setAltura(altura.get());
		animal.setEspecie(especie.get());
		animal.setClasse(classe.get());
		animal.setPeso(peso.get());
		animal.setHabitat((Habitat) habitatSelecionado.get());
		return animal;
	}
	
	public void toBoundary(Object object) {
		if(object == null || object.getClass() != Animal.class)
			return;
		Animal animal = (Animal) object;
		id.set(animal.getId());
		nomePopular.set(animal.getNomePopular());
		genero.set(animal.getGenero());
		altura.set(animal.getAltura());
		especie.set(animal.getEspecie());
		classe.set(animal.getClasse());
		peso.set(animal.getPeso());
		habitatSelecionado.set(animal.getHabitat());
	}
	
	public void clearBoundary() {
		id.set(0);
		nomePopular.set("");
		genero.set("");
		altura.set(0);
		especie.set("");
		classe.set("");
		peso.set(0);
		habitatSelecionado.set(null);
	}

	public IntegerProperty getId() {
		return id;
	}
	public StringProperty getNomePopular() {
		return nomePopular;
	}
	public StringProperty getGenero() {
		return genero;
	}
	public FloatProperty getAltura() {
		return altura;
	}
	public StringProperty getEspecie() {
		return especie;
	}
	public StringProperty getClasse() {
		return classe;
	}
	public FloatProperty getPeso() {
		return peso;
	}
	public ObjectProperty<Object> getHabitatSelecionado() {
		return habitatSelecionado;
	}

	public ObservableList<Object> getListaAnimal() {
		return listaAnimal;
	}
	
}
