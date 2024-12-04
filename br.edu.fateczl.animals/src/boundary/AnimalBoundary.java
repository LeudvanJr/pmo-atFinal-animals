package boundary;

import control.AnimalControl;
import control.HabitatControl;
import exception.AnimalsException;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class AnimalBoundary implements BoundaryScreen {
	
	private Label labelId = new Label("000");
	private TextField tfNomePopular = new TextField();
	private TextField tfGenero = new TextField();
	private TextField tfEspecie = new TextField();
	private TextField tfClasse = new TextField();
	private ComboBox<Object> cbHabitat = new ComboBox<>();
	private TextField tfAltura = new TextField();
	private TextField tfPeso = new TextField();
	private TableView<Object> tabela = new TableView<>();
	
	private AnimalControl control;
	private HabitatControl hControl;
	
	@Override
	public Pane render() {
		try {
			control = new AnimalControl();
			hControl = new HabitatControl();
		} catch (AnimalsException e) {
			e.printStackTrace();
			new Alert(Alert.AlertType.ERROR,
					"Erro ao iniciar o Sistema!",
					ButtonType.OK).showAndWait();
		}
		GridPane gridPane = new GridPane();
		BorderPane  borderPane = new BorderPane();
		FlowPane buttonsPane = new FlowPane();
		
		Button btnGravar = new Button("Gravar");
		btnGravar.setOnAction(op -> {
			try {
				control.insert();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Gravar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnPesquisar = new Button("Pesquisar");
		btnPesquisar.setOnAction(op -> {
			try {
				control.search();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Pesquisar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnExcluir = new Button("Excluir");
		btnExcluir.setOnAction(op -> {
			try {
				control.delete();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Gravar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnAtualizar = new Button("Atualizar");
		btnAtualizar.setOnAction(op -> {
			try {
				control.update();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Atualizar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnListar = new Button("Listar");
		btnListar.setOnAction(op -> {
			try {
				control.listAll();
			} catch (AnimalsException e) {
				e.printStackTrace();
				new Alert(AlertType.ERROR,
						"Não foi possível Listar",
						ButtonType.OK).showAndWait();
			}
		});
		
		Button btnLimpar = new Button("Limpar");
		btnLimpar.setOnAction(op -> {
			control.clearBoundary();
		});
		
		gridPane.add(new Label("ID"), 0, 0);
		gridPane.add(labelId, 1, 0);
		gridPane.add(new Label("Nome Popular"), 0, 1);
		gridPane.add(tfNomePopular, 1, 1);
		gridPane.add(new Label("Gênero"), 0, 2);
		gridPane.add(tfGenero, 1, 2);
		gridPane.add(new Label("Espécie"), 0, 3);
		gridPane.add(tfEspecie, 1, 3);
		gridPane.add(new Label("Classe"), 0, 4);
		gridPane.add(tfClasse, 1, 4);
		gridPane.add(new Label("Altura"), 0, 5);
		gridPane.add(tfAltura, 1, 5);
		gridPane.add(new Label("Peso"), 0, 6);
		gridPane.add(tfPeso, 1, 6);
		gridPane.add(new Label("Habitat"), 0, 7);
		gridPane.add(cbHabitat, 1, 7);
		
		buttonsPane.getChildren().add(btnGravar);
		buttonsPane.getChildren().add(btnAtualizar);
		buttonsPane.getChildren().add(btnExcluir);
		buttonsPane.getChildren().add(btnPesquisar);
		buttonsPane.getChildren().add(btnListar);
		buttonsPane.getChildren().add(btnLimpar);
		buttonsPane.setHgap(5);
		
		gridPane.add(buttonsPane, 1, 8);
		
		gridPane.setHgap(20);
		gridPane.setVgap(5);
		gridPane.setPadding(new Insets(5));
		
		bindings();
		preencherTabela();
		cbHabitat.getItems().addAll(hControl.getListaHabitat());
		cbHabitat.setValue(cbHabitat.getItems().get(0));
		
		borderPane.setCenter(tabela);
		borderPane.setTop(gridPane);
		borderPane.setMinWidth(820);
		return borderPane;
	}

	private void preencherTabela() {
		TableColumn<Object, Integer> col1 = new TableColumn<>("ID");
		col1.setCellValueFactory(new PropertyValueFactory<Object, Integer>("id"));
		
		TableColumn<Object, String> col2 = new TableColumn<>("Nome Popular");
		col2.setCellValueFactory(new PropertyValueFactory<Object, String>("nomePopular"));
		
		TableColumn<Object, String> col3 = new TableColumn<>("Gênero");
		col3.setCellValueFactory(new PropertyValueFactory<Object, String>("genero"));
		
		TableColumn<Object, String> col4 = new TableColumn<>("Espécie");
		col4.setCellValueFactory(new PropertyValueFactory<Object, String>("especie"));

		TableColumn<Object, String> col5 = new TableColumn<>("Classe");
		col5.setCellValueFactory(new PropertyValueFactory<Object, String>("classe"));

		TableColumn<Object, String> col6 = new TableColumn<>("Habitat");
		col6.setCellValueFactory(new PropertyValueFactory<Object, String>("habitat"));

		TableColumn<Object, Float> col7 = new TableColumn<>("Altura");
		col7.setCellValueFactory(new PropertyValueFactory<Object, Float>("altura"));

		TableColumn<Object, Float> col8 = new TableColumn<>("Peso");
		col8.setCellValueFactory(new PropertyValueFactory<Object, Float>("peso"));
		
		tabela.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) -> {
			if(novo != null)
				control.toBoundary(novo);
		});
		
		tabela.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7,col8);
		tabela.setItems(control.getListaAnimal());
	}

	private void bindings() {
		Bindings.bindBidirectional(labelId.textProperty(), control.getId(), new NumberStringConverter());
		Bindings.bindBidirectional(tfNomePopular.textProperty(), control.getNomePopular());
		Bindings.bindBidirectional(tfGenero.textProperty(), control.getGenero());
		Bindings.bindBidirectional(tfEspecie.textProperty(), control.getEspecie());
		Bindings.bindBidirectional(tfClasse.textProperty(), control.getClasse());
		Bindings.bindBidirectional(tfAltura.textProperty(), control.getAltura(), new NumberStringConverter());
		Bindings.bindBidirectional(tfPeso.textProperty(), control.getPeso(), new NumberStringConverter());
		Bindings.bindBidirectional(cbHabitat.valueProperty(), control.getHabitatSelecionado());
	}

}
